package org.example.DAO;

import org.example.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private ConnectionDAO connectionDAO;

    public ReservaDAO(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }

    public ReservaDAO() {this.connectionDAO = new ConnectionDAO();}



    //Crear una funcion para recuperar de la base de datos las habitaciones (idHabitaciones, )








    public int addReserva(Reserva reserva) {
        String query = "INSERT INTO reservas (cantPersonas, idHuesped) VALUES (?, ?)";
        return connectionDAO.executeUpdateAndReturnGeneratedKey(query,
                reserva.getCantPersonas(),
                reserva.getHuesped().getId()
        );
    }

    // **Actualizar Reserva**
    public boolean updateReserva(Reserva reserva) {
        String query = "UPDATE reservas SET cantPersonas = ?, idHuesped = ?, fechaReserva = ? WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query,
                reserva.getCantPersonas(),
                reserva.getHuesped().getId(),
                new java.sql.Date(reserva.getFechaReserva().getTime()),
                reserva.getId()
        );
    }

    // **Eliminar Reserva**
    public boolean deleteReserva(int idReserva) {
        String query = "DELETE FROM reservas WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, idReserva);
    }

    // **Listar todas las Reservas**
    public List<Reserva> getAllReservas() {
        String query = "SELECT * FROM reservas";
        List<Reserva> reservas = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query);
            while (resultSet.next()) {
                int idReserva = resultSet.getInt(1);
                int cantPersonas = resultSet.getInt(2);
                int idHuesped = resultSet.getInt(3);
                Date fechaReserva = resultSet.getDate(4);

                Huesped huesped = new Huesped(idHuesped);
                Reserva reserva = new Reserva(idReserva, cantPersonas, huesped, fechaReserva);
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    // Listar huespedes
    public List<Huesped> listHuespedes(){
        String query = "select h.idHuesped, h.nombre, h.apaterno, h.amaterno, h.idTipoDoc, t.nombreTipo, h.numDoc, h.fechaNac, h.idPais, p.nombreP, h.telefono  from huespedes h, tipoDoc t, paises p Where h.idTipoDoc = t.idTipoDoc AND h.idPais = p.idPais ORDER BY h.idHuesped ASC";
        List<Huesped> huesplist = new ArrayList<>();

        try{
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                String paterno = resultSet.getString(3);
                String materno = resultSet.getString(4);
                TipoDoc tipoDoc = new TipoDoc(resultSet.getInt(5), resultSet.getString(6));
                int numDoc = resultSet.getInt(7);
                String fechaNac = resultSet.getString(8);
                Pais pais = new Pais(resultSet.getInt(9), resultSet.getString(10));
                String telefono = resultSet.getString(11);

                // Crear el objeto Huesped y agregarlo a la lista
                Huesped huesped = new Huesped(id, nombre, paterno, materno, tipoDoc, numDoc,fechaNac, pais, telefono);
                huesplist.add(huesped);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return huesplist;
    }

    //Insertar huesped
    public boolean insertHuesped(Huesped huesped){
        String query = "INSERT INTO huespedes (nombre, apaterno, amaterno, idTipoDoc, numDoc, fechaNac, idPais) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query, huesped.getName(), huesped.getApaterno(), huesped.getAmaterno(), huesped.getTipoDoc().getId(), huesped.getNumDoc(), huesped.getFechaNac(), huesped.getPais().getId());
    }

    // Consultar Disponibilidad segun Fecha, Ciudad, tipo habitacion
    public List<Habitacion> habitacionesDisponiblesParams(int idCiudad, int tipoHab, String inicio, String fin){
        String query = "SELECT hab.idHabitaciones, hab.cantCama, hab.camaDoble, hab.ocupado, " +
                "       hab.aireAcon, hab.balcon, hab.amenities, hab.vista, hab.idTipoHab, " +
                "       th.nombre, h.idHotel, h.nombre " +
                "FROM hoteles h " +
                "JOIN habitaciones hab ON hab.idHotel = h.idHotel " +
                "JOIN tipohabitacion th ON th.idTipoHab = hab.idTipoHab " +
                "WHERE h.idCiudad = ? " +
                "  AND th.idTipoHab = ? " +
                "  AND hab.ocupado = false " +
                "  AND NOT EXISTS (" +
                "      SELECT 1 " +
                "      FROM habitacionreserva hr " +
                "      WHERE hr.idHabitacion = hab.idHabitaciones " +
                "        AND ? < hr.fechaFin " +
                "        AND ? > hr.fechaInicio);";
        List<Habitacion> habitaciones = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idCiudad, tipoHab, inicio, fin);

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

}

