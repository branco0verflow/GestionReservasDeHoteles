package org.example.model;

import java.util.Date;

public class Tarifa {
    private int id;
    private String name;
    private double precio;
    private Date FechInicio;
    private Date FechaFin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechInicio() {
        return FechInicio;
    }

    public void setFechInicio(Date fechInicio) {
        FechInicio = fechInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        FechaFin = fechaFin;
    }


    public Tarifa(int id, String name, double precio, Date fechInicio, Date fechaFin) {
        this.id = id;
        this.name = name;
        this.precio = precio;
        FechInicio = fechInicio;
        FechaFin = fechaFin;
    }
}
