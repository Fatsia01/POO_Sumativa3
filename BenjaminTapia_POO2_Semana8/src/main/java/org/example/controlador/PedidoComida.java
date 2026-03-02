package org.example.controlador;

import org.example.interfaces.Cancelable;
import org.example.interfaces.Despachable;
import org.example.interfaces.Rastreable;

public class PedidoComida extends Pedido implements Despachable, Cancelable, Rastreable {

    boolean tieneMochilaTerm;

    public PedidoComida(int idPedido, String nombreRepartidor, String direccionEntrega, String tipoPedido, double distanciaKm, String estadoPedido, boolean tieneMochilaTerm) {
        super(idPedido,nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, estadoPedido);
        this.tieneMochilaTerm = tieneMochilaTerm;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignado repartidor con mochila térmica");
    }


    @Override
    public void despachar() {
        System.out.println("Pedido");

    }

    @Override
    public void cancelar() {

    }

    @Override
    public void verHistorial() {

    }
}

