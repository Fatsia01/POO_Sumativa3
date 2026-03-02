package org.example.ui.repartidores;

import org.example.conexion.ConexionDB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.UIManager.getInt;


public class VentanaCrearRepartidor {

    public void crearRepartidorUI() throws SQLException {

       String nombreRepartidor = JOptionPane.showInputDialog(null, "Ingrese el nombre del repartidor a crear");

        String crearRepartidorSQL = """
                INSERT INTO speedfast_db.repartidor(nombre)
                VALUES (?);""";

        String idRepartidorSQL = """
                SELECT id
                FROM repartidor
                WHERE nombre = """ + "'" + nombreRepartidor + "'";

        try (Connection connection = ConexionDB.conectar()) {
            PreparedStatement stmt = connection.prepareStatement(crearRepartidorSQL);
            {
                stmt.setString(1,nombreRepartidor);

                stmt.executeUpdate();
            }
        }

        try (Connection connection = ConexionDB.conectar()) {
            PreparedStatement stmt = connection.prepareStatement(idRepartidorSQL);
            int idRepartidor;
            {
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                idRepartidor = resultSet.getInt("id");
                JOptionPane.showMessageDialog(null, "Repartidor creado con el ID: " + idRepartidor);

            }
            }



        }
        }
    }

