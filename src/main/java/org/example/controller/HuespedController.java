package org.example.controller;

import org.example.DAO.HuespedDAO;
import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.TipoDoc;

import java.util.List;

public class HuespedController {

    private HuespedDAO huespedDAO;

    public HuespedController() {
        this.huespedDAO = new HuespedDAO();
    }

    public boolean insertHuesped(Huesped huesp){
        return this.huespedDAO.insertHuesped(huesp);
    }

    public List<Huesped> listHuespedes(){
        return this.huespedDAO.listHuespedes();
    }

    public List<Pais> listPaises(){
        return this.huespedDAO.listPaises();
    }

    public List<TipoDoc> listTipoDocs(){
        return this.huespedDAO.listTipoDoc();
    }

}
