package org.example.DAO;

import org.example.model.Tarifa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TarifaDAO {

    private ConnectionDAO connectionDAO;

    public TarifaDAO() {this.connectionDAO = new ConnectionDAO();}


    // Crear Tarifa
    public boolean addTarifa(Tarifa tarifa) {
        String query = "INSERT INTO tarifas (nombre, precio) VALUES (?, ?)";
        return connectionDAO.executeUpdate(query, tarifa.getNombre(), tarifa.getPrecio());
    }

    // Modificar Tarifa
    public boolean updateTarifa(Tarifa tarifa) {
        String query = "UPDATE tarifas SET nombre = ?, precio = ? WHERE idTarifa = ?";
        return connectionDAO.executeUpdate(query, tarifa.getNombre(), tarifa.getPrecio(), tarifa.getId());
    }

    // Eliminar Tarifa
    public boolean deleteTarifa(int id) {
        String query = "DELETE FROM tarifas WHERE idTarifa = ?";
        return connectionDAO.executeUpdate(query, id);
    }

    // Obtener todas las tarifas
    public List<Tarifa> getAllTarifas() {
        String query = "SELECT idTarifa, nombre, precio FROM tarifas";
        List<Tarifa> tarifas = new ArrayList<>();
        try (ResultSet resultSet = connectionDAO.executeQuery(query)) {
            while (resultSet.next()) {
                Tarifa tarifa = new Tarifa(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                );
                tarifas.add(tarifa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarifas;
    }



}
