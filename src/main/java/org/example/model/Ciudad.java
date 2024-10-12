package org.example.model;

public class Ciudad {
    private int id;
    private String name;
    private Pais pais;


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


    public Ciudad(int id, String name, Pais pais) {
        this.id = id;
        this.name = name;
        this.pais = pais;
    }

    public Ciudad(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
