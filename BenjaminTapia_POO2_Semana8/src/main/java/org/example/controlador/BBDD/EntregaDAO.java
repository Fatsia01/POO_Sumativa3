package org.example.controlador.BBDD;

import org.example.conexion.ConexionDB;
import org.example.controlador.Entrega;
import org.example.ui.VentanaEntregaPedido;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class EntregaDAO {

    LocalDate fechaPedido = LocalDate.now();
    LocalTime horaPedido = LocalTime.now();

    int idPedido;
    int idRepartidor;


    public void guardarEntrega(Entrega entrega) throws SQLException {
        String entregaSQL = """
                INSERT INTO entrega (id_pedido, id_repartidor, fecha, hora)
                VALUES (?,?,?,?)""";

        String pedidosSistemaSQL = """
                SELECT id
                FROM pedido
                WHERE id = """ + entrega.getIdPedido();

        String repartosSistemaSQL = """
                SELECT *
                FROM speedfast_db.repartidor
                WHERE nombre =\s""" + "'" + entrega.getNombreRepartidor() + "'";

        String pedidosSistemaUpdate = """
                UPDATE speedfast_db.pedido
                SET estadoPedido = 'Entregado'
                WHERE id = """ + idPedido;

        try (Connection connection = ConexionDB.conectar()) {
            PreparedStatement stmt = connection.prepareStatement(pedidosSistemaSQL);
            {
                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    idPedido = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos de la tabla de pedidos");
        }

        try (Connection connection = ConexionDB.conectar()) {
            PreparedStatement stmt = connection.prepareStatement(repartosSistemaSQL);
            {
                ResultSet resultSet = stmt.executeQuery();

                while (resultSet.next()) {
                    idRepartidor = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los datos de la tabla de repartos");
        }

        try (Connection connection = ConexionDB.conectar();
             PreparedStatement stmt = connection.prepareStatement(entregaSQL)) {

            stmt.setInt(1, idPedido);
            stmt.setInt(2, idRepartidor);
            stmt.setDate(3, Date.valueOf(fechaPedido));
            stmt.setTime(4, Time.valueOf(horaPedido));

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al ingresar los datos");;
        }

        try (Connection connection = ConexionDB.conectar();
             PreparedStatement stmt = connection.prepareStatement(pedidosSistemaUpdate)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al ingresar los datos");
        }
    }
}
