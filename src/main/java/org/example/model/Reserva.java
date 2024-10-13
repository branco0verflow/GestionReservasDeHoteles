package org.example.model;

import java.util.Date;

public class Reserva {
    private int id;
    private int cantPersonas;
    private Habitacion habitacion;
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

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Reserva(int id, int cantPersonas, Habitacion habitacion, Date fechaReserva) {
        this.id = id;
        this.cantPersonas = cantPersonas;
        this.habitacion = habitacion;
        this.fechaReserva = fechaReserva;
    }

    public Reserva(int cantPersonas, Habitacion habitacion, Date fechaReserva) {
        this.cantPersonas = cantPersonas;
        this.habitacion = habitacion;
        this.fechaReserva = fechaReserva;
    }

}
