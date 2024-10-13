package org.example.controller;

import org.example.DAO.HabitacionDAO;
import org.example.DAO.HotelDAO;
import org.example.model.*;

import java.util.List;

public class HabitacionController {

    private HabitacionDAO habitlDAO;

    public HabitacionController() {
        this.habitlDAO = new HabitacionDAO();
    }


    //CRUD

    public boolean addHabit(Habitacion habit) {
        return habitlDAO.addHotel(habit);
    }

    public boolean updateHabit(Habitacion habit) {
        return habitlDAO.updateHabitacion(habit);
    }

    public boolean deleteHabit(int idhabit) {
        return habitlDAO.deleteHabitacion(idhabit);
    }


    public List<Habitacion> getAllHabit() {
        return habitlDAO.getAllHabitaciones();
    }


    // LISTADOS

    public List<Hotel> listHotels(){
        return this.habitlDAO.getAllHotels();
    }

    public List<TipoHabit> listTipoHabit(){
        return this.habitlDAO.listTiposHabit();
    }


    public List<Tarifa> tarifaList(){
        return this.habitlDAO.listTarifas();
    }


}
