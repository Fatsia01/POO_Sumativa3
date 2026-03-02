package org.example.controlador;

public class Pedido {

    int idPedido;
    String nombreRepartidor;
    String direccionEntrega;
    String tipoPedido;
    double distanciaKm;
    public String estadoPedido; //Ingresado (dentro del sistema pero sin repartidor), Cancelado, Entregado

    public Pedido(int idPedido, String nombreRepartidor, String direccionEntrega, String tipoPedido, double distanciaKm, String estadoPedido) {
        this.idPedido = idPedido;
        this.nombreRepartidor = nombreRepartidor;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
        this.distanciaKm = distanciaKm;
        this.estadoPedido = estadoPedido;
    }

    public Pedido(int idPedido, String direccionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    public Pedido(String direccionEntrega, String tipoPedido, String estadoPedido) { //Tipo de Pedido para el UI
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
        this.estadoPedido = estadoPedido;
    }

    public Pedido() {

    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombreRepartidor() {
        return nombreRepartidor;
    }

    public void setNombreRepartidor(String nombreRepartidor) {
        this.nombreRepartidor = nombreRepartidor;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(String tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public void asignarRepartidor() {
        System.out.println("Asignado repartidor");
    }

    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Asignado repartidor " + nombreRepartidor);
        System.out.println("-SOBRECARGA-");
    }

    public void asignarRepartidor(String nombreRepartidor, double peso, boolean validacionEmbalaje) { //Validación de Encomiendas por Sobrecarga
        if (peso <= 20 && validacionEmbalaje) {
            System.out.println("Asignado el repartidor " + nombreRepartidor + " con la validación de peso y embalaje realizadas para el pedido de tipo Embalaje");
            System.out.println("-SOBRECARGA-");
        }
    }

    public void asignarRepartidor(String nombreRepartidor, boolean tieneMochilaTerm) {
        if (tieneMochilaTerm) {
            System.out.println("Asignado el repartidor " + nombreRepartidor + " con mochila térmica verificada");
            System.out.println("-SOBRECARGA-");
        }
    }

    public void mostrarResumen() {
        System.out.printf("""
                ------PEDIDO N° %d------
                NOMBRE REPARTIDOR: %s
                DIRECCION: %s
                TIPO PEDIDO: %s
                DISTANCIA EN KM: %s
                ESTADO PEDIDO: %s
                """, idPedido + 1, nombreRepartidor, direccionEntrega, tipoPedido, distanciaKm, estadoPedido);
    }



    @Override
    public String toString() { //Para ser usado por el UI
        return """
                ID del pedido: %d
                Direccion de pedido: %s
                Tipo de pedido: %s
                """.formatted(idPedido, direccionEntrega, tipoPedido);
    }
}


