package org.example.model;

public class Pais {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pais(int id, String name){
        this.id = id;
        this.name = name;
    }
}

