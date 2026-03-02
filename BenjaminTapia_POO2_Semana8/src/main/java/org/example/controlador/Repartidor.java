package org.example.controlador;

import org.example.ZonaDeCarga;

public class Repartidor implements Runnable {

    String nombreRepartidor;
    ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombreRepartidor, ZonaDeCarga zonaDeCarga) {
        this.nombreRepartidor = nombreRepartidor;
        this.zonaDeCarga = zonaDeCarga;
    }

    public Repartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    @Override
    public void run() {

        ZonaDeCarga.pedidosPendientes.get(zonaDeCarga.getIdPedido()).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.EN_REPARTO);
            try {
                System.out.println("Pedido n° " + (zonaDeCarga.getIdPedido() + 1) + " cambiado a 'En Reparto' por el repartidor " + nombreRepartidor);
                Thread.sleep(2000);

                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.println();


                System.out.println("Pedido n° " + (zonaDeCarga.getIdPedido() + 1) + " entregado, cambiando el estado a 'Entregado'");
                ZonaDeCarga.pedidosPendientes.get(zonaDeCarga.getIdPedido()).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.ENTREGADO);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

    @Override
    public String toString() {
        return "Repartidor: " + nombreRepartidor;

    }
}
