package org.example.controller;

import org.example.DAO.ConnectionDAO;
import org.example.DAO.HabitacionDAO;
import org.example.DAO.HabitacionReservaDAO;
import org.example.model.HabitacionReserva;

import java.util.List;

public class HabitacionReservaController {

    private HabitacionReservaDAO habitacionReservaDAO;

    public HabitacionReservaController() { this.habitacionReservaDAO = new HabitacionReservaDAO();}

    // Crear nueva HabitacionReserva
    public boolean addHabitacionReserva(HabitacionReserva habitacionReserva) {
        return habitacionReservaDAO.addHabitacionReserva(habitacionReserva);
    }

    // Modificar una HabitacionReserva existente
    public boolean updateHabitacionReserva(HabitacionReserva habitacionReserva) {
        return habitacionReservaDAO.updateHabitacionReserva(habitacionReserva);
    }

    // Eliminar una HabitacionReserva por su ID
    public boolean deleteHabitacionReserva(int idHabitacionReserva) {
        return habitacionReservaDAO.deleteHabitacionReserva(idHabitacionReserva);
    }

    // Obtener una lista de todas las HabitacionReserva
    public List<HabitacionReserva> getAllHabitacionReserva() {
        return habitacionReservaDAO.getAllHabitacionReserva();
    }

}
