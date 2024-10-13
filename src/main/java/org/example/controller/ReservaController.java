package org.example.controller;

import org.example.DAO.ReservaDAO;
import org.example.model.Huesped;
import org.example.model.Reserva;

import java.util.List;

public class ReservaController {

    private ReservaDAO reservaDAO;

    public ReservaController() { this.reservaDAO = new ReservaDAO();}

    public boolean addReserva(Reserva reserva){
        return reservaDAO.addReserva(reserva);
    }

    public boolean updateReserva(Reserva reserva){
        return reservaDAO.updateReserva(reserva);
    }

    public boolean deleteReserva(int idReserva){
        return reservaDAO.deleteReserva(idReserva);
    }

    public List<Reserva> getAllReservas(){
        return reservaDAO.getAllReservas();
    }

    // Huespedes

    public List<Huesped> listHuesp(){
        return reservaDAO.listHuespedes();
    }

    public boolean insertHuesped(Huesped huesp){
        return this.reservaDAO.insertHuesped(huesp);
    }


}