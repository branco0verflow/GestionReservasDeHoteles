package org.example.DAO;

import org.example.model.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDAO {

    /*

    private ConnectionDAO connectionDAO;

    public HotelDAO(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }


    public boolean addHotel(Hotel hotel) {
        String query = "INSERT INTO hotel (name, idPais, idCiudad, cantEstrella, direccion) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connectionDAO.getConnection().prepareStatement(query);
            statement.setString(1, hotel.getName());
            statement.setInt(2, hotel.getPais().getId());
            statement.setInt(3, hotel.getCiudad().getId());
            statement.setInt(4, hotel.getCantEstrella());
            statement.setString(5, hotel.getDireccion());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para modificar un hotel
    public boolean updateHotel(Hotel hotel) {
        String query = "UPDATE hotel SET name = ?, idPais = ?, idCiudad = ?, cantEstrella = ?, direccion = ? WHERE idHotel = ?";
        try {
            PreparedStatement statement = connectionDAO.getConnection().prepareStatement(query);
            statement.setString(1, hotel.getName());
            statement.setInt(2, hotel.getPais().getId());
            statement.setInt(3, hotel.getCiudad().getId());
            statement.setInt(4, hotel.getCantEstrella());
            statement.setString(5, hotel.getDireccion());
            statement.setInt(6, hotel.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para eliminar un hotel
    public boolean deleteHotel(int idHotel) {
        String query = "DELETE FROM hotel WHERE idHotel = ?";
        try {
            PreparedStatement statement = connectionDAO.getConnection().prepareStatement(query);
            statement.setInt(1, idHotel);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método para buscar un hotel por ID
    public Hotel getHotelById(int idHotel) {
        String query = "SELECT * FROM hotel WHERE idHotel = ?";
        try {
            PreparedStatement statement = connectionDAO.getConnection().prepareStatement(query);
            statement.setInt(1, idHotel);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Pais pais = new PaisDAO(connectionDAO).getPaisById(resultSet.getInt("idPais"));
                Ciudad ciudad = new CiudadDAO(connectionDAO).getCiudadById(resultSet.getInt("idCiudad"));
                Hotel hotel = new Hotel(
                        resultSet.getInt("idHotel"),
                        resultSet.getString("name"),
                        pais,
                        ciudad,
                        resultSet.getInt("cantEstrella"),
                        resultSet.getString("direccion")
                );
                return hotel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener todos los hoteles
    public List<Hotel> getAllHotels() {
        String query = "SELECT * FROM hotel";
        List<Hotel> hotels = new ArrayList<>();
        try {
            PreparedStatement statement = connectionDAO.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pais pais = new PaisDAO(connectionDAO).getPaisById(resultSet.getInt("idPais"));
                Ciudad ciudad = new CiudadDAO(connectionDAO).getCiudadById(resultSet.getInt("idCiudad"));
                Hotel hotel = new Hotel(
                        resultSet.getInt("idHotel"),
                        resultSet.getString("name"),
                        pais,
                        ciudad,
                        resultSet.getInt("cantEstrella"),
                        resultSet.getString("direccion")
                );
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    */
}

