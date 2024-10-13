package org.example.controller;

import org.example.DAO.HotelDAO;
import org.example.DAO.TarifaDAO;
import org.example.model.Hotel;
import org.example.model.Tarifa;

import java.util.List;

public class TarifaController {

    private TarifaDAO tarifaDAO;


    public TarifaController() {
        this.tarifaDAO = new TarifaDAO();
    }

    public boolean addTarifa(Tarifa tarifa) {
        return tarifaDAO.addTarifa(tarifa);
    }

    public boolean updateTarifa(Tarifa tarifa) {
        return tarifaDAO.updateTarifa(tarifa);
    }

    public boolean deleteTarifa(int idTarifa) {
        return tarifaDAO.deleteTarifa(idTarifa);
    }

    public List<Tarifa> getAllTarifas(){
          return tarifaDAO.getAllTarifas();
    }

}
