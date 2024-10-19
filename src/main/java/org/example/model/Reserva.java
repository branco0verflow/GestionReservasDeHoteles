package org.example.model;

import java.util.Date;

public class Reserva {
    private int id;
    private int cantPersonas;
    private Huesped huesped;
    private Date fechaReserva;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Reserva(int id, int cantPersonas, Huesped huesped, Date fechaReserva) {
        this.id = id;
        this.cantPersonas = cantPersonas;
        this.huesped = huesped;
        this.fechaReserva = fechaReserva;
    }

    public Reserva(int id, int cantPersonas, Date fechaReserva, Huesped huesped) {
        this.id = id;
        this.cantPersonas = cantPersonas;
        this.fechaReserva = fechaReserva;
        this.huesped = huesped;
    }

    public Reserva(int id, Huesped huesped, Date fechaReserva) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.huesped = huesped;
    }

    public Reserva(int id, int cantPersonas, Date fechaReserva) {
        this.id = id;
        this.cantPersonas = cantPersonas;
        this.fechaReserva = fechaReserva;
    }

    public Reserva(int cantPersonas, Huesped huesped) {
        this.cantPersonas = cantPersonas;
        this.huesped = huesped;;
    }

    public Reserva(int id) {
        this.id = id;
    }




}
