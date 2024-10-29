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


    public List<HabitacionReserva> EncontrarReservasEntreFechas(String inicio, String fin){
        String query = "SELECT r.idReserva, r.fechaReserva, r.cantPersonas, hr.fechaInicio, hr.fechaFin, " +
                "hab.idHabitaciones, hab.ocupado, hab.vista, th.idTipoHab, th.nombre AS tipoNombre, " +
                "ho.idHotel, ho.nombre AS hotelNombre " +
                "FROM Reservas r " +
                "INNER JOIN habitacionReserva hr ON r.idReserva = hr.idReserva " +
                "INNER JOIN Habitaciones hab ON hr.idHabitacion = hab.idHabitaciones " +
                "INNER JOIN tipoHabitacion th ON hab.idTipoHab = th.idTipoHab " +
                "INNER JOIN Hoteles ho ON hab.idHotel = ho.idHotel " +
                "WHERE hr.fechaInicio BETWEEN ? AND ?";

        List<HabitacionReserva> habitaciones = new ArrayList<>();
        try (ResultSet rs = connectionDAO.executeQuery(query, inicio, fin)) {
            while (rs.next()) {
                // Crear objetos necesarios
                Hotel hotel = new Hotel(rs.getInt("idHotel"), rs.getString("hotelNombre"));
                TipoHabit tipo = new TipoHabit(rs.getInt("idTipoHab"), rs.getString("tipoNombre"));
                Habitacion habitacion = new Habitacion(rs.getInt("idHabitaciones"), rs.getBoolean("ocupado"),
                        rs.getString("vista"), tipo, hotel);
                Date fecahIn = rs.getDate("fechaInicio");
                Date fechaFi = rs.getDate("fechaFin");
                Reserva reserva = new Reserva(rs.getInt("idReserva"), rs.getInt("cantPersonas"), rs.getDate("fechaReserva"));
                habitaciones.add(new HabitacionReserva(habitacion, fecahIn, fechaFi, reserva));
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

    public boolean updateHabitacionReserva(int idReserva, String fechaInicio, String fechaFin, String observacion) {
        String query = "UPDATE habitacionReserva SET fechaInicio = ?, fechaFin = ?, observaciones = ? " +
                "WHERE idReserva = ?";
        return connectionDAO.executeUpdate(query, fechaInicio, fechaFin, observacion, idReserva);
    }

    public List<Reserva> listAllReserva(int numDoc) {
        String query = "SELECT r.idReserva, r.cantPersonas, r.fechaReserva, h.idHuesped, h.nombre " +
                "FROM Reservas r " +
                "INNER JOIN Huespedes h ON r.idHuesped = h.idHuesped " +
                "WHERE h.numDoc = ?";

        List<Reserva> reservas = new ArrayList<>();
        try (ResultSet rs = connectionDAO.executeQuery(query, numDoc)) {

            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("idReserva"),
                        rs.getInt("cantPersonas"),
                        rs.getDate("fechaReserva"),
                        new Huesped(rs.getInt("idHuesped"), rs.getString("nombre"))
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public List<HabitacionReserva> listAllHabitResReserva(int numDoc) {
        String query = "SELECT r.idReserva, r.cantPersonas, r.fechaReserva, " +
                "h.idHuesped, h.nombre, hab.idHabitaciones, hab.ocupado, hab.vista, hr.fechaInicio, hr.fechaFin, " +
                "th.idTipoHab, th.nombre AS tipoNombre, ho.idHotel, ho.nombre AS hotelNombre " +
                "FROM Reservas r " +
                "INNER JOIN Huespedes h ON r.idHuesped = h.idHuesped " +
                "INNER JOIN habitacionReserva hr ON r.idReserva = hr.idReserva " +
                "INNER JOIN Habitaciones hab ON hr.idHabitacion = hab.idHabitaciones " +
                "INNER JOIN tipoHabitacion th ON hab.idTipoHab = th.idTipoHab " +
                "INNER JOIN Hoteles ho ON hab.idHotel = ho.idHotel " +
                "WHERE h.numDoc = ?";

        List<HabitacionReserva> reservas = new ArrayList<>();
        try (ResultSet rs = connectionDAO.executeQuery(query, numDoc)) {
            while (rs.next()) {
                // Crear objetos necesarios
                Hotel hotel = new Hotel(rs.getInt("idHotel"), rs.getString("hotelNombre"));
                TipoHabit tipo = new TipoHabit(rs.getInt("idTipoHab"), rs.getString("tipoNombre"));
                Habitacion habitacion = new Habitacion(rs.getInt("idHabitaciones"), rs.getBoolean("ocupado"),
                        rs.getString("vista"), tipo, hotel);
                Date fecahIn = rs.getDate("fechaInicio");
                Date fechaFi = rs.getDate("fechaFin");
                Reserva reserva = new Reserva(rs.getInt("idReserva"), rs.getInt("cantPersonas"), rs.getDate("fechaReserva"));
                reservas.add(new HabitacionReserva(habitacion, fecahIn, fechaFi, reserva));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public List<HabitacionReserva> HabitacionSegunIDHuesped(int idHuesped) {
        String query = "SELECT hab.idHabitaciones, hab.ocupado, hab.vista, " +
                "th.idTipoHab, th.nombre, hab.idHotel, h.nombre as nombreHotel, " +
                "r.idReserva, r.cantPersonas, r.fechaReserva " +
                "FROM habitaciones hab " +
                "INNER JOIN tipoHabitacion th ON hab.idTipoHab = th.idTipoHab " +
                "INNER JOIN hoteles h ON hab.idHotel = h.idHotel " +
                "INNER JOIN habitacionReserva hr ON hab.idHabitaciones = hr.idHabitacion " +
                "INNER JOIN Reservas r ON r.idReserva = hr.idReserva " +
                "WHERE r.idHuesped = ?";

        List<HabitacionReserva> resultado = new ArrayList<>();

        try (ResultSet rs = connectionDAO.executeQuery(query, idHuesped)) {
            while (rs.next()) {
                Habitacion habitacion = new Habitacion(rs.getInt("idHabitaciones"), rs.getBoolean("ocupado"), rs.getString("vista"),
                        new TipoHabit(rs.getInt("idTipoHab"), rs.getString("nombre")),
                        new Hotel(rs.getInt("idHotel"), rs.getString("nombreHotel")));
                Reserva reserva = new Reserva(rs.getInt("idReserva"), rs.getInt("cantPersonas"), rs.getDate("fechaReserva"));


                resultado.add(new HabitacionReserva(habitacion, reserva));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    public boolean updateHabitacionToOcupada(int idHabitacion) {
        String query = "UPDATE Habitaciones SET ocupado = 1 WHERE idHabitaciones = ?";
            return connectionDAO.executeUpdate(query, idHabitacion);
    }

    public boolean updateHabitacionToDESOcupada(int idHabitacion) {
        String query = "UPDATE Habitaciones SET ocupado = 0 WHERE idHabitaciones = ?";
        return connectionDAO.executeUpdate(query, idHabitacion);
    }

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

