package org.example.controlador;

import java.time.LocalDate;
import java.time.LocalTime;

public class Entrega {
    int idEntrega;
    int idPedido;
    String nombreRepartidor;
    LocalDate fechaEntrega;
    LocalTime horaEntrega;

    public Entrega(int idEntrega, int idPedido, String nombreRepartidor, LocalDate fechaEntrega, LocalTime horaEntrega) {
        this.idEntrega = idEntrega;
        this.idPedido = idPedido;
        this.nombreRepartidor = nombreRepartidor;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
    }

    public Entrega(int idPedido, String nombreRepartidor, LocalDate fechaEntrega, LocalTime horaEntrega) {
        this.idPedido = idPedido;
        this.nombreRepartidor = nombreRepartidor;
        this.fechaEntrega = fechaEntrega;
        this.horaEntrega = horaEntrega;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
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

    public void setNombreRepartidor(String idRepartidor) {
        this.nombreRepartidor = idRepartidor;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public LocalTime getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(LocalTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }
}
