package org.example.controlador.BBDD;

import org.example.conexion.ConexionDB;
import org.example.controlador.Pedido;
import org.example.ui.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAO extends JTable {

    public static void create(Pedido pedido) throws SQLException {
        String pedidoSQL = """
                INSERT INTO pedido(direccion, tipo, estado)
                VALUES (?,?,?)""";

        try (Connection connection = ConexionDB.conectar();
             PreparedStatement stmt = connection.prepareStatement(pedidoSQL)) {

            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getTipoPedido());
            stmt.setString(3, pedido.getEstadoPedido());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al ingresar el pedido al sistema");
            e.printStackTrace();
        } finally {
            System.out.println("Pedido ingresado con éxito");
            ConexionDB.conectar().close();
        }
    }
}