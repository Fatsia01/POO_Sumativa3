package org.example.ui;

import org.example.ZonaDeCarga;
import org.example.controlador.BBDD.PedidoDAO;
import org.example.controlador.EstadoPedido;
import org.example.controlador.Pedido;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

import static org.example.ui.VentanaListaPedidos.modelo;

public class VentanaRegistroPedido {
    private JTextField direccionPedido;
    private JComboBox<String> tipoPedido;
    static boolean verificarPedido = false;

    private JButton guardaPedido;
    private JLabel direccionPedidoLabel;
    private JLabel tipoPedidoLabel;

    private final JFrame ventanaRegistroPedido = new JFrame();

    public void VentanaRegistroUI () {

        ventanaRegistroPedido.setLayout(new BorderLayout());

        guardaPedido = new JButton("GUARDAR");

        ventanaRegistroPedido.setSize(600,400);
        ventanaRegistroPedido.setLayout(null);
        ventanaRegistroPedido.setVisible(true);

        ventanaRegistroPedido.add(direccionPedido);
        ventanaRegistroPedido.add(tipoPedido);

        ventanaRegistroPedido.add(direccionPedidoLabel);
        ventanaRegistroPedido.add(tipoPedidoLabel);
        ventanaRegistroPedido.add(guardaPedido);

        direccionPedido.setBounds(150,150,400,50);
        tipoPedido.setBounds(150,200,400,50);
        guardaPedido.setBounds(250,300,100,50);

        direccionPedidoLabel.setBounds(20,150,400,50);
        tipoPedidoLabel.setBounds(20,200,400,50);

        guardaPedido.addActionListener(e -> {
            ventanaRegistroPedido.dispose();

            try {
                PedidoDAO.create(new Pedido(direccionPedido.getText(), Objects.requireNonNull(tipoPedido.getSelectedItem()).toString(), String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)));
                verificarPedido = true;

            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null,"Error de tipo al ingresar los datos, por favor intentelo nuevamente");
                VentanaRegistroUI();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            if (verificarPedido) {
                JOptionPane.showMessageDialog(null, "Agregado con éxito", "Confirmación de pedido", JOptionPane.INFORMATION_MESSAGE);
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.VentanaPrincipalUI();
            }

        });

    }

}
