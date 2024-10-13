package org.example.model;

import java.util.Date;

public class HabitacionReserva {
    private int id;
    private Habitacion habitacion;
    private Date fechaInicio;
    private Date fechaFin;
    private String observaciones;
    private Reserva reserva;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public HabitacionReserva(int id, Habitacion habitacion, Date fechaInicio, Date fechaFin, String observaciones, Reserva reserva) {
        this.id = id;
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
        this.reserva = reserva;
    }

    public HabitacionReserva(Habitacion habitacion, Date fechaInicio, Date fechaFin, String observaciones, Reserva reserva) {
        this.habitacion = habitacion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.observaciones = observaciones;
        this.reserva = reserva;
    }
}
