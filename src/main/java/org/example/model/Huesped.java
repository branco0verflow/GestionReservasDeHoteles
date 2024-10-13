package org.example.model;

import java.util.Date;

public class Huesped {
    private int id;
    private String name;
    private String apaterno;
    private String amaterno;
    private TipoDoc tipoDoc;
    private int numDoc;
    private String fechaNac;
    private Pais pais;
    private String telefono;



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

    public String getApaterno() {
        return apaterno;
    }

    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public TipoDoc getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(TipoDoc tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public int getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(int numDoc) {
        this.numDoc = numDoc;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public Huesped(int id, String name, String apaterno, String amaterno, TipoDoc tipoDoc, int numDoc, String fechaNac, Pais pais, String telefono) {
        this.id = id;
        this.name = name;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.fechaNac = fechaNac;
        this.pais = pais;
        this.telefono = telefono;
    }

    public Huesped(String name, String apaterno, String amaterno, TipoDoc tipoDoc, int numDoc, String fechaNac, Pais pais, String telefono) {
        this.name = name;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.tipoDoc = tipoDoc;
        this.numDoc = numDoc;
        this.fechaNac = fechaNac;
        this.pais = pais;
        this.telefono = telefono;
    }

    public Huesped(int id) {
        this.id = id;
    }
}
