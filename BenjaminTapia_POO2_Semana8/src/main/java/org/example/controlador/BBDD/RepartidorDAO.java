package org.example.controlador.BBDD;

import org.example.conexion.ConexionDB;
import org.example.controlador.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {

    List<Repartidor> repartidores = new ArrayList<>();

    public void listarTodos() {
        String repartidorSQL = """
                SELECT *
                FROM speedfast_db.repartidor""";

        try (Connection connection = ConexionDB.conectar();
             PreparedStatement stmt = connection.prepareStatement(repartidorSQL)) {

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String nombreRepartidor = resultSet.getString("nombre");

                repartidores.add(new Repartidor(nombreRepartidor));
                }

            for (Repartidor repartidor1: repartidores) {
                System.out.println(repartidor1);
            }

        } catch (SQLException e) {
            System.out.println("Error al extraer la información de la base de datos");
        }
    }

}
