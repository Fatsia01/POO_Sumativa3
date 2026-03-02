package org.example.ui.repartidores;

import org.example.conexion.ConexionDB;
import org.example.ui.VentanaPrincipal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaListaRepartidores {

    private final String[] nombresColumnasString = {"ID Pedido", "Direccion Entrega", "Tipo de Pedido", "Estado de Pedido"};
    private final JFrame ventanaListaPedidos = new JFrame();
    private final JButton menuPrincipal = new JButton("VOLVER");
    public static DefaultTableModel modelo;

    String listaPedidosSQL = """
            SELECT *
            FROM speedfast_db.repartidor""";

    public void VentanaRepartidorUI()  {

        try (Connection connection = ConexionDB.conectar()) {
            PreparedStatement stmt = connection.prepareStatement(listaPedidosSQL);
            {
                ResultSet resultSet = stmt.executeQuery();

                modelo = new DefaultTableModel();
                modelo.addColumn("id");
                modelo.addColumn("nombre");

                modelo.setRowCount(0);

                while (resultSet.next()) {
                    int idRepartidor = resultSet.getInt("id");
                    String nombreRepartidor = resultSet.getString("nombre");

                    modelo.addRow(new Object[]{
                            idRepartidor, nombreRepartidor
                    });


                }

                JTable jTable = new JTable(modelo);
                JScrollPane jScrollPane1 = new JScrollPane(jTable);

                ventanaListaPedidos.setSize(600, 400);
                ventanaListaPedidos.setLayout(null);
                ventanaListaPedidos.setVisible(true);

                ventanaListaPedidos.add(menuPrincipal);
                ventanaListaPedidos.add(jScrollPane1);

                jScrollPane1.setBounds(10, 10, 350, 350);
                jScrollPane1.setVisible(true);

                menuPrincipal.setBounds(400, 300, 100, 100);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        menuPrincipal.addActionListener(e -> {
            ventanaListaPedidos.dispose();
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.VentanaPrincipalUI();
        });

    }
}

