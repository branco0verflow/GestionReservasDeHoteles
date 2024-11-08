package org.example.DAO;

import org.example.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {



    private ConnectionDAO connectionDAO;

    public HotelDAO() {this.connectionDAO = new ConnectionDAO();}

    public List<Habitacion> encontrarHabitacionesOcupadasSinReserva(int idHotel) {
        String query = "SELECT " +
                "    h.idHabitaciones AS habitacionID, " +
                "    h.vista AS vistaHabitacion, " +
                "    th.idTipoHab, th.nombre AS tipoHabitacion, " +
                "    ho.idHotel, ho.nombre AS nombreHotel " +
                "FROM " +
                "    Habitaciones h " +
                "INNER JOIN Hoteles ho ON h.idHotel = ho.idHotel " +
                "INNER JOIN tipoHabitacion th ON h.idTipoHab = th.idTipoHab " +
                "WHERE " +
                "    h.idHotel = ? " +
                "    AND h.ocupado = TRUE " +
                "    AND NOT EXISTS ( " +
                "        SELECT 1 FROM habitacionReserva hr " +
                "        WHERE hr.idHabitacion = h.idHabitaciones " +
                "    );";

        List<Habitacion> habitaciones = new ArrayList<>();

        try (ResultSet rs = connectionDAO.executeQuery(query, idHotel)) {
            while (rs.next()) {
                Hotel hotel = new Hotel(rs.getInt("idHotel"), rs.getString("nombreHotel"));
                TipoHabit tipo = new TipoHabit(rs.getInt("idTipoHab"), rs.getString("tipoHabitacion"));

                Habitacion habitacion = new Habitacion(
                        rs.getInt("habitacionID"),
                        true,
                        rs.getString("vistaHabitacion"),
                        tipo,
                        hotel
                );

                habitaciones.add(habitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return habitaciones;
    }


    public List<HabitacionReserva> EncontrarHabitacionesOcupadas(int idHotel) {
        String query = "SELECT \n" +
                "    h.idHabitaciones AS habitacionID,\n" +
                "    h.vista AS vistaHabitacion,\n" +
                "    th.idTipoHab, \n" +
                "    th.nombre AS tipoHabitacion,\n" +
                "    ho.idHotel, \n" +
                "    ho.nombre AS nombreHotel,\n" +
                "    r.idReserva, \n" +
                "    r.fechaReserva,\n" +
                "    hu.idHuesped, \n" +
                "    hu.nombre AS nombreHuesped,\n" +
                "    hu.apaterno AS apellidoPaterno, \n" +
                "    hu.amaterno AS apellidoMaterno\n" +
                "FROM \n" +
                "    Habitaciones h\n" +
                "INNER JOIN habitacionReserva hr ON h.idHabitaciones = hr.idHabitacion\n" +
                "INNER JOIN Reservas r ON hr.idReserva = r.idReserva\n" +
                "INNER JOIN Huespedes hu ON r.idHuesped = hu.idHuesped\n" +
                "INNER JOIN Hoteles ho ON h.idHotel = ho.idHotel\n" +
                "INNER JOIN tipoHabitacion th ON h.idTipoHab = th.idTipoHab\n" +
                "WHERE \n" +
                "    h.idHotel = ? \n" +
                "    AND h.ocupado = TRUE\n" +
                "    AND r.fechaReserva = (\n" +
                "        SELECT MAX(r2.fechaReserva)\n" +
                "        FROM habitacionReserva hr2\n" +
                "        INNER JOIN Reservas r2 ON hr2.idReserva = r2.idReserva\n" +
                "        WHERE hr2.idHabitacion = h.idHabitaciones\n" +
                "    );\n";

        List<HabitacionReserva> habitaciones = new ArrayList<>();

        try (ResultSet rs = connectionDAO.executeQuery(query, idHotel)) {
            while (rs.next()) {
                Hotel hotel = new Hotel(rs.getInt("idHotel"), rs.getString("nombreHotel"));
                TipoHabit tipo = new TipoHabit(rs.getInt("idTipoHab"), rs.getString("tipoHabitacion"));

                Habitacion habitacion = new Habitacion(
                        rs.getInt("habitacionID"),
                        true,
                        rs.getString("vistaHabitacion"),
                        tipo,
                        hotel
                );

                Huesped huesped = new Huesped(
                        rs.getInt("idHuesped"),
                        rs.getString("nombreHuesped"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno")
                );

                Reserva reserva = new Reserva(
                        rs.getInt("idReserva"),
                        huesped,
                        rs.getDate("fechaReserva")
                );

                habitaciones.add(new HabitacionReserva(habitacion, reserva));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return habitaciones;
    }

    public List<HabitacionReserva> EncontrarHabitacionesSegunIdHotel(int idHotel) {
        String query = "SELECT " +
                "    h.idHabitaciones AS habitacionID, " +
                "    h.vista AS vistaHabitacion, " +
                "    th.idTipoHab, th.nombre AS tipoHabitacion, " +
                "    ho.idHotel, ho.nombre AS nombreHotel, " +
                "    r.idReserva, r.fechaReserva, " +
                "    hu.idHuesped, hu.nombre AS nombreHuesped, " +
                "    hu.apaterno AS apellidoPaterno, hu.amaterno AS apellidoMaterno " +
                "FROM " +
                "    Habitaciones h " +
                "LEFT JOIN habitacionReserva hr ON h.idHabitaciones = hr.idHabitacion " +
                "LEFT JOIN Reservas r ON hr.idReserva = r.idReserva " +
                "LEFT JOIN Huespedes hu ON r.idHuesped = hu.idHuesped " +
                "INNER JOIN Hoteles ho ON h.idHotel = ho.idHotel " +
                "INNER JOIN tipoHabitacion th ON h.idTipoHab = th.idTipoHab " +
                "WHERE h.idHotel = ?;";

        List<HabitacionReserva> habitaciones = new ArrayList<>();

        try (ResultSet rs = connectionDAO.executeQuery(query, idHotel)) {
            while (rs.next()) {
                // Crear objetos necesarios
                Hotel hotel = new Hotel(rs.getInt("idHotel"), rs.getString("nombreHotel"));
                TipoHabit tipo = new TipoHabit(rs.getInt("idTipoHab"), rs.getString("tipoHabitacion"));

                Habitacion habitacion = new Habitacion(
                        rs.getInt("habitacionID"),
                        rs.getString("vistaHabitacion"),
                        tipo,
                        hotel
                );

                Huesped huesped = (rs.getInt("idHuesped") != 0) ? new Huesped(
                        rs.getInt("idHuesped"),
                        rs.getString("nombreHuesped"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno")
                ) : null;

                Reserva reserva = (rs.getInt("idReserva") != 0) ? new Reserva(
                        rs.getInt("idReserva"),
                        huesped,
                        rs.getDate("fechaReserva")
                ) : null;

                habitaciones.add(new HabitacionReserva(habitacion, reserva));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habitaciones;
    }


    // Crear Hotel
    public boolean addHotel(Hotel hotel) {
        String query = "INSERT INTO hoteles (nombre, idPais, idCiudad, cantEstrella, direccion) VALUES (?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query, hotel.getName(), hotel.getPais().getId(), hotel.getCiudad().getId(), hotel.getCantEstrella(), hotel.getDireccion());
    }

    //Modificar Hotel
    public boolean updateHotel(Hotel hotel) {
        String query = "UPDATE hoteles SET nombre = ?, idPais = ?, idCiudad = ?, cantEstrella = ?, direccion = ? WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, hotel.getName(), hotel.getPais().getId(), hotel.getCiudad().getId(), hotel.getCantEstrella(), hotel.getDireccion(), hotel.getId());
    }

    // Eliminar Hoteles
    public boolean deleteHotel(int idHotel) {
        String query = "DELETE FROM hoteles WHERE idHotel = ?";
        return connectionDAO.executeUpdate(query, idHotel);
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

    // Llamada a paises existentes
    public List<Pais> listPaises(){
        String query = "SELECT p.idPais, p.nombreP FROM paises p;";
        List<Pais> countryList = new ArrayList<>();

        try{
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                Pais pais = new Pais(id, name);


                countryList.add(pais);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return countryList;
    }

    // Llamada a ciudades en base a IdPais
    public List<Ciudad> listCiudades(int idCiudad) {
        String query = "select c.idCiudad, c.nombreC, c.idPais from ciudades c where c.idPais = ?";
        List<Ciudad> cityList = new ArrayList<>();

        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idCiudad);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);


                Ciudad ciudad = new Ciudad(id, name);

                cityList.add(ciudad);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return cityList;
    }



}

