package org.example.DAO;

import org.example.model.Ciudad;
import org.example.model.Hotel;
import org.example.model.Pais;
import org.example.model.TipoDoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {



    private ConnectionDAO connectionDAO;

    public HotelDAO() {this.connectionDAO = new ConnectionDAO();}


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

