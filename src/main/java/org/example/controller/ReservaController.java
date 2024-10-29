package org.example.controller;

import org.example.DAO.ReservaDAO;
import org.example.model.*;

import java.util.List;

public class ReservaController {

    private ReservaDAO reservaDAO;

    public ReservaController() { this.reservaDAO = new ReservaDAO();}

    public List<Hotel> listAllHotels(){
        return reservaDAO.getAllHotels();
    }

    public List<Habitacion> listAllHabitaciones(){
        return reservaDAO.getAllHabitaciones();
    }

    public boolean UpdateHabitacionToOcupada(int idHabit){
        return reservaDAO.updateHabitacionToOcupada(idHabit);
    }

    public boolean UpdateHabitacionToDESOcupada(int idHabit){
        return reservaDAO.updateHabitacionToDESOcupada(idHabit);
    }

    public List<HabitacionReserva> listAllHabitReserva(int idHuesped){
        return  reservaDAO.listAllHabitResReserva(idHuesped);
    }

    public List<HabitacionReserva> EncontrarReservasEntreFechas(String fechaIn, String fechaFin){
        return  reservaDAO.EncontrarReservasEntreFechas(fechaIn, fechaFin);
    }

    public List<Reserva> listAllReservas(int idHuesped){
        return  reservaDAO.listAllReserva(idHuesped);
    }

    public boolean updateHabitacionReserva(int idReserva, String fechaInicio, String fecgaFin, String observa){
        return reservaDAO.updateHabitacionReserva(idReserva, fechaInicio, fecgaFin, observa);
    }

    public List<HabitacionReserva> HabitacionReservadaSegunIdHuesped(int idHuesped){
        return  reservaDAO.HabitacionSegunIDHuesped(idHuesped);
    }

    public int addReserva(Reserva reserva){
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

    public List<Habitacion> habitacionesDisponiblesParams(int idCiudad, int tipoHab, String inicio, String fin){
        return reservaDAO.habitacionesDisponiblesParams(idCiudad, tipoHab, inicio, fin);
    }

    // Huespedes

    public List<Huesped> listHuesp(){
        return reservaDAO.listHuespedes();
    }

    public boolean insertHuesped(Huesped huesp){
        return this.reservaDAO.insertHuesped(huesp);
    }


}
