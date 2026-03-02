package org.example.ui;
import org.example.conexion.ConexionDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaListaPedidos extends JTable {
    private final String[] nombresColumnasString = {"ID Pedido", "Direccion Entrega", "Tipo de Pedido", "Estado de Pedido"};
    private final JFrame ventanaListaPedidos = new JFrame();
    private final JButton menuPrincipal = new JButton("VOLVER");
    public static DefaultTableModel modelo;

    String listaPedidosSQL = """
            SELECT *
            FROM speedfast_db.pedido""";

    public void VentanaListaUI () throws SQLException {

        try (Connection connection = ConexionDB.conectar()) {
            PreparedStatement stmt = connection.prepareStatement(listaPedidosSQL);
            {
                ResultSet resultSet = stmt.executeQuery();

                modelo = new DefaultTableModel();
                modelo.addColumn("id");
                modelo.addColumn("direccion");
                modelo.addColumn("tipo");
                modelo.addColumn("estado");

                modelo.setRowCount(0);

                while (resultSet.next()) {
                    int idPedido = resultSet.getInt("id");
                    String direccionPedido = resultSet.getString("direccion");
                    String tipoPedido = resultSet.getString("tipo");
                    String estadoPedido = resultSet.getString("estado");

                    modelo.addRow(new Object[] {
                            idPedido, direccionPedido, tipoPedido, estadoPedido
                    });


                }
                JTable jTable = new JTable(modelo);
                JScrollPane jScrollPane1 = new JScrollPane(jTable);

                ventanaListaPedidos.setSize(600,400);
                ventanaListaPedidos.setLayout(null);
                ventanaListaPedidos.setVisible(true);

                ventanaListaPedidos.add(menuPrincipal);
                ventanaListaPedidos.add(jScrollPane1);

                jScrollPane1.setBounds(10,10,350,350);
                jScrollPane1.setVisible(true);

                menuPrincipal.setBounds(400,300,100,100);
            }
        }


        menuPrincipal.addActionListener(e -> {
            ventanaListaPedidos.dispose();
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.VentanaPrincipalUI();
        });

        }

        @Override
        public String getColumnName(int index) {
            return nombresColumnasString[index];
        }

        @Override
        public int getColumnCount() {
            return nombresColumnasString.length;
        }
    }

