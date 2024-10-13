package org.example.DAO;

import org.example.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    private ConnectionDAO connectionDAO;

    public HabitacionDAO() {
        this.connectionDAO = new ConnectionDAO();
    }


    // Crear Habitacion
    public boolean addHotel(Habitacion habit) {
        String query = "INSERT INTO habitaciones (cantCama, camaDoble, ocupado, aireAcon, balcon, amenities, vista, idTipoHab, idHotel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query, habit.getCantCama(), habit.isCamaDoble(), habit.isOcupado(), habit.isAireAcon(), habit.isBalcon(), habit.isAmenities(), habit.getVista(), habit.getTipoHabit().getId(), habit.getHotel().getId());
    }


    // Modificar Habitación
    public boolean updateHabitacion(Habitacion habitacion) {
        String query = "UPDATE habitaciones SET cantCama = ?, camaDoble = ?, ocupado = ?, aireAcon = ?, balcon = ?, amenities = ?, vista = ?, idTipoHab = ?, idHotel = ? WHERE idHabitaciones = ?";
        return connectionDAO.executeUpdate(query, habitacion.getCantCama(), habitacion.isCamaDoble(), habitacion.isOcupado(), habitacion.isAireAcon(), habitacion.isBalcon(), habitacion.isAmenities(), habitacion.getVista(), habitacion.getTipoHabit().getId(), habitacion.getHotel().getId(), habitacion.getId()
        );
    }

    // Eliminar Habitacion
    public boolean deleteHabitacion(int idHabit) {
        String query = "DELETE FROM habitaciones WHERE idHabitaciones = ?";

        return connectionDAO.executeUpdate(query, idHabit);
    }


    // LISTAR Habitaciones
    public List<Habitacion> getAllHabitaciones() {
        String query = "SELECT h.idHabitaciones, h.cantCama, h.camaDoble, h.ocupado, h.aireAcon, h.balcon, h.amenities, h.vista, " +
                "th.idTipoHab, th.nombre AS tipoNombre, ho.idHotel, ho.nombre AS hotelNombre " +
                "FROM habitaciones h " +
                "JOIN tipoHabitacion th ON h.idTipoHab = th.idTipoHab " +
                "JOIN hoteles ho ON h.idHotel = ho.idHotel " +
                "ORDER BY h.idHabitaciones ASC";

        List<Habitacion> habitaciones = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int cantCama = resultSet.getInt(2);
                boolean camaDoble = resultSet.getBoolean(3);
                boolean ocupado = resultSet.getBoolean(4);
                boolean aireAcon = resultSet.getBoolean(5);
                boolean balcon = resultSet.getBoolean(6);
                boolean amenities = resultSet.getBoolean(7);
                String vista = resultSet.getString(8);

                TipoHabit tipoHabitacion = new TipoHabit(resultSet.getInt(9), resultSet.getString(10));
                Hotel hotel = new Hotel(resultSet.getInt(11), resultSet.getString(12));

                Habitacion habitacion = new Habitacion(id, cantCama, camaDoble, ocupado, aireAcon, balcon, amenities, vista, tipoHabitacion, hotel);

                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return habitaciones;
    }


    // LISTAR Hoteles
    public List<Hotel> getAllHotels() {
        String query = "SELECT o.idHotel, o.nombre, o.idPais, p.nombreP, o.idCiudad, c.nombreC, o.cantEstrella, o.direccion FROM hoteles o, paises p, ciudades c WHERE o.idPais = p.idPais AND o.idCiudad = c.idCiudad ORDER BY o.idHotel ASC";
        List<Hotel> hotels = new ArrayList<>();
        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                Pais pais = new Pais(resultSet.getInt(3), resultSet.getString(4));
                Ciudad ciudad = new Ciudad(resultSet.getInt(5), resultSet.getString(6), pais);
                int cantEstrella = resultSet.getInt(7);
                String direccion = resultSet.getString(8);

                Hotel hotel = new Hotel(id, nombre, pais, ciudad, cantEstrella, direccion);
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    // Llamada a tipo-Habitación existentes
    public List<TipoHabit> listTiposHabit() {
        String query = "SELECT th.idTipoHab, th.nombre, th.idTarifa, t.nombre, t.precio FROM tipoHabitacion th, tarifas t where th.idTarifa = t.idTarifa";
        List<TipoHabit> tipoHabList = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                int idTarifa = resultSet.getInt(3);
                String tarifaName = resultSet.getString(4);
                double tarifaPrecio = resultSet.getDouble(5);

                Tarifa tarifaObject = new Tarifa(idTarifa, tarifaName, tarifaPrecio);

                TipoHabit tipoHab = new TipoHabit(id, nombre, tarifaObject);
                tipoHabList.add(tipoHab);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tipoHabList;
    }

    // Listar Tarifas
    public List<Tarifa> listTarifas() {

        String query = "Select * from Tarifas";
        List<Tarifa> tarifaslist = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int idTarifa = resultSet.getInt(1);
                String tarifaName = resultSet.getString(2);
                double tarifaPrecio = resultSet.getDouble(3);

                Tarifa tarifaObject = new Tarifa(idTarifa, tarifaName, tarifaPrecio);


                tarifaslist.add(tarifaObject);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tarifaslist;

    }

}