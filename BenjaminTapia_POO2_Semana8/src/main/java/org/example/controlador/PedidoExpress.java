package org.example.controlador;

import org.example.interfaces.*;


public class PedidoExpress extends Pedido implements Despachable, Cancelable, Rastreable {

    public PedidoExpress(int idPedido, String nombreRepartidor, String direccionEntrega, String tipoPedido, double distanciaKm, String estadoPedido) {
        super(idPedido, nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, estadoPedido);
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignado el repartidor más cercano para le pedido");
    }


    @Override
    public void cancelar() {

    }

    @Override
    public void despachar() {

    }

    @Override
    public void verHistorial() {

    }
}
