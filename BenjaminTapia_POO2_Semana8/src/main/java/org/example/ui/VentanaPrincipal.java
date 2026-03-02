package org.example.ui;

import org.example.ui.repartidores.VentanaCrearRepartidor;
import org.example.ui.repartidores.VentanaListaRepartidores;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class VentanaPrincipal extends JFrame {

    private JButton REGISTRARPEDIDOButton;
    private JButton LISTARPEDIDOButton;
    private JButton ASIGNARREPARTIDORINICIARENTREGAButton;
    private JButton CREARREPARTIDOR;
    private JButton VERREPARTIDORESButton;
    private final JLabel VENTANAPRINCIPAL = new JLabel("¡Bienvenido a SpeedFest!");

    JFrame ventanaPrincipal = new JFrame();
    VentanaRegistroPedido ventanaRegistroPedido = new VentanaRegistroPedido();
    VentanaListaPedidos ventanaListaPedidos = new VentanaListaPedidos();
    VentanaEntregaPedido ventanaEntregaPedido = new VentanaEntregaPedido();
    VentanaCrearRepartidor ventanaCrearRepartidor = new VentanaCrearRepartidor();
    VentanaListaRepartidores ventanaListaRepartidores = new VentanaListaRepartidores();

    public void VentanaPrincipalUI() {

            ventanaPrincipal.setLayout(new BorderLayout());

            ventanaPrincipal.add(REGISTRARPEDIDOButton);
            ventanaPrincipal.add(LISTARPEDIDOButton);
            ventanaPrincipal.add(ASIGNARREPARTIDORINICIARENTREGAButton);
            ventanaPrincipal.add(VENTANAPRINCIPAL);
            ventanaPrincipal.add(CREARREPARTIDOR);
            ventanaPrincipal.add(VERREPARTIDORESButton);

            ventanaPrincipal.setSize(1200, 1080);
            ventanaPrincipal.setLayout(null);
            ventanaPrincipal.setVisible(true);

            REGISTRARPEDIDOButton.setBounds(100, 100, 400, 50);
            LISTARPEDIDOButton.setBounds(100, 150, 400, 50);
            ASIGNARREPARTIDORINICIARENTREGAButton.setBounds(100, 200, 400, 50);
            VENTANAPRINCIPAL.setBounds(250, 0, 400, 100);
            CREARREPARTIDOR.setBounds(100,250,400,50);
            VERREPARTIDORESButton.setBounds(100,300,400,50);

            REGISTRARPEDIDOButton.addActionListener(e -> {

                ventanaPrincipal.dispose();

                ventanaRegistroPedido.VentanaRegistroUI();
            });

            LISTARPEDIDOButton.addActionListener(e -> {
                ventanaPrincipal.dispose();
                try {
                    ventanaListaPedidos.VentanaListaUI();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });

            ASIGNARREPARTIDORINICIARENTREGAButton.addActionListener(e -> {
                ventanaPrincipal.dispose();

                ventanaEntregaPedido.VentanaEntregaUI();
            });

            CREARREPARTIDOR.addActionListener(e -> {
                ventanaPrincipal.dispose();

                try {
                    ventanaCrearRepartidor.crearRepartidorUI();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            });

            VERREPARTIDORESButton.addActionListener(e -> {
                ventanaPrincipal.dispose();

                ventanaListaRepartidores.VentanaRepartidorUI();
            });


        }


    }


