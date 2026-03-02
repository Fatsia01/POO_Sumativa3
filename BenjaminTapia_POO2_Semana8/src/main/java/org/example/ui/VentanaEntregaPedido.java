package org.example.ui;

import org.example.controlador.BBDD.EntregaDAO;
import org.example.controlador.Entrega;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaEntregaPedido {

    EntregaDAO entregaDAO = new EntregaDAO();

    private final JTextField idPedido = new JTextField();
    public static final JTextField nombreRepartidor = new JTextField();

    private final JLabel idPedidoLabel = new JLabel("ID del Pedido a entregar");
    private final JLabel nombreRepartidorLabel = new JLabel("Nombre del Repartidor asignado");

    JFrame ventanaEntregaPedido = new JFrame();

    public void VentanaEntregaUI () {

        JButton guardaEntrega = new JButton("GUARDAR");

        ventanaEntregaPedido.setLayout(new BorderLayout());

        ventanaEntregaPedido.setSize(600,400);
        ventanaEntregaPedido.setLayout(null);
        ventanaEntregaPedido.setVisible(true);

        ventanaEntregaPedido.add(idPedido);
        ventanaEntregaPedido.add(nombreRepartidor);

        ventanaEntregaPedido.add(idPedidoLabel);
        ventanaEntregaPedido.add(nombreRepartidorLabel);
        ventanaEntregaPedido.add(guardaEntrega);

        idPedido.setBounds(200,100,200,50);
        nombreRepartidor.setBounds(200,150,200,50);

        idPedidoLabel.setBounds(20,100,100,50);
        nombreRepartidorLabel.setBounds(20,150,100,50);

        guardaEntrega.setBounds(250,300,100,50);

        guardaEntrega.addActionListener(e -> {
            ventanaEntregaPedido.dispose();

            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.VentanaPrincipalUI();

            try {
                entregaDAO.guardarEntrega(new Entrega(Integer.parseInt(idPedido.getText()), nombreRepartidor.getText(), LocalDate.now(), LocalTime.now()));

            } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, "Error al entregar el pedido","ENTRADA INVALIDA",1);
                ex.printStackTrace();

            }

        });

    }

}
