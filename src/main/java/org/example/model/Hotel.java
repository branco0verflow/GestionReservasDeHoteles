package org.example.model;

public class Hotel {
    private int id;
    private String name;
    private Pais pais;
    private Ciudad ciudad;
    private int cantEstrella;
    private String direccion;


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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public int getCantEstrella() {
        return cantEstrella;
    }

    public void setCantEstrella(int cantEstrella) {
        this.cantEstrella = cantEstrella;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Hotel(int id, String name, Pais pais, Ciudad ciudad, int cantEstrella, String direccion) {
        this.id = id;
        this.name = name;
        this.pais = pais;
        this.ciudad = ciudad;
        this.cantEstrella = cantEstrella;
        this.direccion = direccion;
    }

    public Hotel(String name, Pais pais, Ciudad ciudad, int cantEstrella, String direccion) {
        this.name = name;
        this.pais = pais;
        this.ciudad = ciudad;
        this.cantEstrella = cantEstrella;
        this.direccion = direccion;
    }
}
