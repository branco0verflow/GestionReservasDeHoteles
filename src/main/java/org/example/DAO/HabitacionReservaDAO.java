package org.example.DAO;

import org.example.model.Habitacion;
import org.example.model.HabitacionReserva;
import org.example.model.Reserva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HabitacionReservaDAO {
    private ConnectionDAO connectionDAO;

    public HabitacionReservaDAO(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }

    // Crear HabitacionReserva
    public boolean addHabitacionReserva(HabitacionReserva habitacionReserva) {
        String query = "INSERT INTO HabitacionReserva (idHabitacion, fechaInicio, fechaFin, observaciones, idReserva) " +
                "VALUES (?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query,
                habitacionReserva.getHabitacion().getId(),
                habitacionReserva.getFechaInicio(),
                habitacionReserva.getFechaFin(),
                habitacionReserva.getObservaciones(),
                habitacionReserva.getReserva().getId());
    }

    // Modificar HabitacionReserva
    public boolean updateHabitacionReserva(HabitacionReserva habitacionReserva) {
        String query = "UPDATE HabitacionReserva SET idHabitacion = ?, idReserva = ?, fechaInicio = ?, fechaFin = ?, observaciones = ? " +
                "WHERE idHabitacionReserva = ?";
        return connectionDAO.executeUpdate(query,
                habitacionReserva.getHabitacion().getId(),
                habitacionReserva.getReserva().getId(),
                habitacionReserva.getFechaInicio(),
                habitacionReserva.getFechaFin(),
                habitacionReserva.getObservaciones(),
                habitacionReserva.getId());
    }

    // Eliminar HabitacionReserva
    public boolean deleteHabitacionReserva(int idHabitacionReserva) {
        String query = "DELETE FROM HabitacionReserva WHERE idHabitacionReserva = ?";
        return connectionDAO.executeUpdate(query, idHabitacionReserva);
    }

    // Listar todas las HabitacionReserva
    public List<HabitacionReserva> getAllHabitacionReserva() {
        String query = "SELECT * FROM HabitacionReserva";
        List<HabitacionReserva> habitacionReservas = new ArrayList<>();
        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                Habitacion habitacion = new Habitacion(resultSet.getInt(2));
                Date fechaInicio = resultSet.getDate(3);
                Date fechaFin = resultSet.getDate(4);
                String observaciones = resultSet.getString(5);
                Reserva reserva = new Reserva(resultSet.getInt(6));

                HabitacionReserva habitacionReserva = new HabitacionReserva(id, habitacion, fechaInicio, fechaFin, observaciones, reserva);
                habitacionReservas.add(habitacionReserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitacionReservas;
    }
}

