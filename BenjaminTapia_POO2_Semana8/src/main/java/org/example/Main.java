package org.example;

import org.example.controlador.*;
import org.example.controlador.BBDD.RepartidorDAO;
import org.example.controlador.Pedido;
import org.example.controlador.Repartidor;
import org.example.ui.VentanaPrincipal;

import java.sql.SQLException;
import java.util.*;

public class Main {

    static int pedidosComida = 0;
    static int pedidosEncomienda = 0;
    static int pedidosExpress = 0;
    static int pedidosTotales = 0; //Para asignar el número del pedido
    static String[] menuMainOptions = {"Ingresar pedido", "Despachar pedido", "Cancelar pedido", "Visualizar historial de pedidos","Visualizar entrega por hilos", "Salir del sistema"};
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        int menuMainNumber = 0;

        List<PedidoComida> pedidoComidas = new ArrayList<>();
        List<PedidoEncomienda> pedidoEncomiendas = new ArrayList<>();
        List<PedidoExpress> pedidoExpress = new ArrayList<>();
        List<Pedido> historialPedidos = new ArrayList<>();

        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();

        ventanaPrincipal.VentanaPrincipalUI();

        RepartidorDAO repartidorDAO = new RepartidorDAO();
        repartidorDAO.listarTodos();

        do {
            try {
                menuMain();

                System.out.print("Elija el número de su opción: ");
                menuMainNumber = input.nextInt();

                while (menuMainNumber <= 0 || menuMainNumber > 5) {
                    System.out.println("Por favor elija una opción valida");
                    menuMainNumber = input.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un valor válido");
                input.nextLine();
            }

            try {
                switch (menuMainNumber) {
                    case 1:
                        System.out.println("Por favor ingrese la siguiente información:");
                        System.out.print("NOMBRE DEL REPARTIDOR (en caso de querer asignarlo automaticamente escribir el símbolo '-'): ");

                        String nombreRepartidor = input.next();

                        if (nombreRepartidor.equals("-")) {
                            System.out.println("Asignado automaticamente al repartidor Carlos Perez");
                            nombreRepartidor = "Carlos Perez";
                        }

                        System.out.print("DIRECCIÓN DE LA ENTREGA: ");
                        String direccionEntrega = input.next();

                        System.out.print("TIPO DE PEDIDO (Comida, Encomienoda o Express): ");
                        String tipoPedido = input.next();
                        tipoPedido = tipoPedido.toLowerCase();

                        while (!tipoPedido.equals("comida") && !tipoPedido.equals("encomienda") && !tipoPedido.equals("express")) {
                            System.out.println("Por favor elegir una de los tipos permitidos");
                            tipoPedido = input.next();
                            tipoPedido = tipoPedido.toLowerCase();
                        }

                        System.out.print("KILOMETROS RECORRIDOS: ");
                        double distanciaKm = input.nextDouble();

                        historialPedidos.add(new Pedido(pedidosTotales, nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)){});


                        ZonaDeCarga.agregarPedido(new ZonaDeCarga(pedidosTotales, nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)) {
                        });

                        pedidosTotales++;

                        if (tipoPedido.equals("comida")) {
                            System.out.println("El repartidor tiene mochila térmica"); //Simulación de bases de datos de repartidores

                            pedidoComidas.add(new PedidoComida(pedidosComida, nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE), true));
                            pedidosComida++;
                    }
                        if (tipoPedido.equals("encomienda")) {
                            System.out.print("PESO (en kilos): ");
                            double pesoEncomienda = input.nextDouble();

                            System.out.println("El repartidor verificó el embalaje"); //Simulación de validación

                            pedidoEncomiendas.add(new PedidoEncomienda(pedidosEncomienda, nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE), pesoEncomienda, true));
                            pedidosEncomienda++;
                        }

                        if (tipoPedido.equals("express")) {
                            pedidoExpress.add(new PedidoExpress(pedidosExpress, nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)));
                            pedidosExpress++;
                        }
                        break;

                    case 2:
                        System.out.print("Tipo de pedido que desea despachar (Comida, Encomienda o Express): ");
                        String tipoPedidoDespacho = input.next();
                        tipoPedidoDespacho = tipoPedidoDespacho.toLowerCase();

                        while (!tipoPedidoDespacho.equals("comida") && !tipoPedidoDespacho.equals("encomienda") && !tipoPedidoDespacho.equals("express")) {
                            System.out.println("Por favor elegir una de los tipos permitidos");
                            tipoPedidoDespacho = input.next();
                            tipoPedidoDespacho = tipoPedidoDespacho.toLowerCase();
                        }

                        try {
                            if (tipoPedidoDespacho.equals("comida")) {

                                System.out.print("Ingrese el número de pedido que desee despachar: ");
                                int numeroPedido = input.nextInt();

                                System.out.println("Pedido n° " + numeroPedido + " despachado con éxito");

                                while (Objects.equals(pedidoComidas.get(numeroPedido - 1).estadoPedido, "Despachado") || Objects.equals(pedidoComidas.get(numeroPedido - 1).estadoPedido, "Cancelado")) {
                                    System.out.print("Intento de despachar un pedido invalido (despachado o cancelado), favor de elegir otro pedido: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                pedidoComidas.get(numeroPedido - 1).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.ENTREGADO);
                            }

                            if (tipoPedidoDespacho.equals("encomienda")) {
                                System.out.print("Ingrese el número de pedido que desee despachar: ");
                                int numeroPedido = input.nextInt();

                                System.out.println("Pedido n° " + numeroPedido + " despachado con éxito");

                                while (Objects.equals(pedidoEncomiendas.get(numeroPedido - 1).estadoPedido, "Despachado") || Objects.equals(pedidoEncomiendas.get(numeroPedido - 1).estadoPedido, "Cancelado")) {
                                    System.out.print("Intento de despachar un pedido invalido (despachado o cancelado), favor de elegir otro pedido: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                pedidoEncomiendas.get(numeroPedido - 1).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.ENTREGADO);
                            }

                            if (tipoPedidoDespacho.equals("express")) {
                                System.out.print("Ingrese el número de pedido que desee despachar: ");
                                int numeroPedido = input.nextInt();

                                System.out.println("Pedido n° " + numeroPedido + " despachado con éxito");

                                while (Objects.equals(pedidoExpress.get(numeroPedido - 1).estadoPedido, "Despachado") || Objects.equals(pedidoExpress.get(numeroPedido - 1).estadoPedido, "Cancelado")) {
                                    System.out.print("Intento de despachar un pedido invalido (despachado o cancelado), favor de elegir otro pedido: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                pedidoExpress.get(numeroPedido - 1).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.ENTREGADO   );
                            }
                        }   catch (IndexOutOfBoundsException outOfBoundsException) {
                            System.out.println("Elija un número valido de pedido");
                        }
                        break;

                    case 3:
                        System.out.print("Tipo de pedido que desea cancelar (Comida, Encomienoda o Express): ");
                        String tipoPedidoCancelacion = input.next();
                        tipoPedidoCancelacion = tipoPedidoCancelacion.toLowerCase();

                        while (!tipoPedidoCancelacion.equals("comida") && !tipoPedidoCancelacion.equals("encomienda") && !tipoPedidoCancelacion.equals("express")) {
                            System.out.println("Por favor elegir una de los tipos permitidos");
                            tipoPedidoCancelacion = input.next();
                            tipoPedidoCancelacion = tipoPedidoCancelacion.toLowerCase();
                        }

                        try {
                            if (tipoPedidoCancelacion.equals("comida")) {

                                System.out.print("Ingrese el número de pedido que desee cancelar: ");
                                int numeroPedido = input.nextInt();

                                System.out.println("Pedido n° " + numeroPedido + " despachado con éxito");

                                while (Objects.equals(pedidoComidas.get(numeroPedido - 1).estadoPedido, "Despachado")) {
                                    System.out.print("Intento de despachar un pedido invalido (despachado o cancelado), favor de elegir otro pedido: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                while (Objects.equals(pedidoComidas.get(numeroPedido - 1).estadoPedido, "Cancelado")) {
                                    System.out.print("Pedido ya cancelado, favor de elegir otro pedido: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                pedidoComidas.get(numeroPedido - 1).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.CANCELADO);
                            }

                            if (tipoPedidoCancelacion.equals("encomienda")) {
                                System.out.print("Ingrese el número de pedido que desee despachar: ");
                                int numeroPedido = input.nextInt();

                                System.out.println("Pedido n° " + numeroPedido + " cancelado con éxito");

                                while (Objects.equals(pedidoEncomiendas.get(numeroPedido - 1).estadoPedido, "Despachado")) {
                                    System.out.print("Intento de cancelar un pedido ya despachado, favor de elegir un pedido ingresado: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                while (Objects.equals(pedidoEncomiendas.get(numeroPedido - 1).estadoPedido, "Cancelado")) {
                                    System.out.print("Pedido ya cancelado, favor de elegir otro pedido: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                pedidoEncomiendas.get(numeroPedido - 1).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.CANCELADO);
                            }

                            if (tipoPedidoCancelacion.equals("express")) {
                                System.out.print("Ingrese el número de pedido que desee despachar: ");
                                int numeroPedido = input.nextInt();

                                System.out.println("Pedido n° " + numeroPedido + " cancelado con éxito");

                                while (Objects.equals(pedidoExpress.get(numeroPedido - 1).estadoPedido, "Despachado")) {
                                    System.out.print("Intento de cancelar un pedido ya despachado, favor de elegir un pedido ingresado: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                while (Objects.equals(pedidoExpress.get(numeroPedido - 1).estadoPedido, "Cancelado")) {
                                    System.out.print("Pedido ya cancelado, favor de elegir otro pedido: ");
                                    input.nextLine();
                                    numeroPedido = input.nextInt();
                                }

                                pedidoExpress.get(numeroPedido - 1).estadoPedido = String.valueOf(EstadoPedido.estadoPedido.CANCELADO);
                            }

                        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                            System.out.println("Elija un número valido de pedido");
                        }
                        break;


                    case 4:
                        for (PedidoComida pedidoComida: pedidoComidas) {
                            System.out.println("------PEDIDO COMIDA------");
                            pedidoComida.mostrarResumen();

                        }

                        for (PedidoEncomienda pedidoEncomienda: pedidoEncomiendas) {
                            System.out.println("------PEDIDO ENCOMIENDA------");
                            pedidoEncomienda.mostrarResumen();
                        }

                        for (PedidoExpress pedidoExpress1: pedidoExpress) {
                            System.out.println("------PEDIDO EXPRESS------");
                            pedidoExpress1.mostrarResumen();
                        }

                        break;


                    case 5:
                        ZonaDeCarga.pedidosPendientes.add(new ZonaDeCarga(0, "Esteban Paredes", "Calle Falsa 123", "Comida", 20, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)) {
                        });

                        ZonaDeCarga.pedidosPendientes.add(new ZonaDeCarga(1, "Kim Kitsuragi", "Calle Falsa 1234", "Encomienda", 30, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)) {
                        });

                        ZonaDeCarga.pedidosPendientes.add(new ZonaDeCarga(2, "Alberto Madariaga", "Calle Falsa 125", "Comida", 15, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)) {
                        });

                        ZonaDeCarga.pedidosPendientes.add(new ZonaDeCarga(3, "Oscar Tapia", "Calle Falsa 1513", "Express", 5, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)) {
                        });

                        ZonaDeCarga.pedidosPendientes.add(new ZonaDeCarga(4, "Marco Argento", "Calle Falsa 1612", "Comida", 25, String.valueOf(EstadoPedido.estadoPedido.PENDIENTE)) {
                        });


                        Runnable pedido1 = new Repartidor(ZonaDeCarga.pedidosPendientes.getFirst().getNombreRepartidor(), ZonaDeCarga.pedidosPendientes.getFirst());
                        Runnable pedido2 = new Repartidor(ZonaDeCarga.pedidosPendientes.get(1).getNombreRepartidor(), ZonaDeCarga.pedidosPendientes.get(1));
                        Runnable pedido3 = new Repartidor(ZonaDeCarga.pedidosPendientes.get(2).getNombreRepartidor(), ZonaDeCarga.pedidosPendientes.get(2));


                        Thread hilo1 = new Thread(pedido1);
                        Thread hilo2 = new Thread(pedido2);
                        Thread hilo3 = new Thread(pedido3);

                        hilo1.start();
                        hilo2.start();
                        hilo3.start();
                        hilo1.join();
                        hilo2.join();
                        hilo3.join();


                        if (Objects.equals(ZonaDeCarga.pedidosPendientes.getFirst().estadoPedido, "ENTREGADO") && Objects.equals(ZonaDeCarga.pedidosPendientes.get(1).estadoPedido, "ENTREGADO")
                                && Objects.equals(ZonaDeCarga.pedidosPendientes.get(2).estadoPedido, "ENTREGADO")) {

                            System.out.println("PEDIDOS ENTREGADOS CON ÉXITO");
                        }
                        else
                            System.out.println("Error, revisar en el sistema");
                }


            } catch (InputMismatchException e) {
                System.out.println("Por favor elegir un valor valido");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        } while (menuMainNumber < 7);

        System.out.println("Muchas gracias por su uso!"); //Mensaje de despedida

    }


    private static void menuMain() {
        System.out.println("====MENU SPEEDFAST====");
        for (int i = 0; i <= menuMainOptions.length - 1; i++) {
            System.out.println(i + 1 + ". " + menuMainOptions[i]) ;
        }

    }
}

