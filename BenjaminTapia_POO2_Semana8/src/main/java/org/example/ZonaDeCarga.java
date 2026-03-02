package org.example;

import org.example.controlador.EstadoPedido;
import org.example.controlador.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZonaDeCarga extends Pedido {

    public ZonaDeCarga(int idPedido, String nombreRepartidor, String direccionEntrega, String tipoPedido, double distanciaKm, String estadoPedido) {
        super(idPedido, nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, estadoPedido);
    }

    public ZonaDeCarga(int idPedido, String direccionEntrega, String tipoPedido) {
        super(idPedido, direccionEntrega, tipoPedido);
    }

    public ZonaDeCarga(String direccionEntrega, String tipoPedido, String estadoPedido) {
        super(direccionEntrega, tipoPedido, estadoPedido);
    }

    public ZonaDeCarga() {
    }

    public static List<ZonaDeCarga> pedidosPendientes = Collections.synchronizedList(new ArrayList<>());

    public static synchronized void agregarPedido(ZonaDeCarga p) {
        pedidosPendientes.add(p);
    }

    public synchronized void retirarPedido(int index) {
        pedidosPendientes.get(index).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.EN_REPARTO);
    }


    }
