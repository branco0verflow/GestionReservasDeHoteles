package org.example.model;

public class TipoDoc {
    private int id;
    private String name;

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

    public TipoDoc(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
