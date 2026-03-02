package org.example.controlador;

import org.example.interfaces.*;

public class PedidoEncomienda extends Pedido implements Despachable, Cancelable, Rastreable {

    double peso;
    boolean validacionEmbalaje;

    public PedidoEncomienda(int idPedido, String nombreRepartidor, String direccionEntrega, String tipoPedido, double distanciaKm, String estadoPedido, double peso, boolean validacionEmbalaje) {
        super(idPedido, nombreRepartidor,direccionEntrega, tipoPedido, distanciaKm, estadoPedido);
        this.peso = peso;
        this.validacionEmbalaje = validacionEmbalaje;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("Asignado repartidor informado de las validaciones de pesos y embalaje necesarias");
    }


    @Override
    public void verHistorial() {

    }

    @Override
    public void despachar() {

    }

    @Override
    public void cancelar() {

    }
}
