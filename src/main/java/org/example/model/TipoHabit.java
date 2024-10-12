package org.example.model;

public class TipoHabit {
    private int id;
    private String nombre;
    private Tarifa idTarifa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tarifa getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Tarifa idTarifa) {
        this.idTarifa = idTarifa;
    }

    public TipoHabit(int id, String nombre, Tarifa idTarifa) {
        this.id = id;
        this.nombre = nombre;
        this.idTarifa = idTarifa;
    }

    public TipoHabit(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

}
