package org.example.DAO;

import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.Reserva;
import org.example.model.TipoDoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private ConnectionDAO connectionDAO;

    public ReservaDAO(ConnectionDAO connectionDAO) {
        this.connectionDAO = connectionDAO;
    }

    public ReservaDAO() {
        this.connectionDAO = new ConnectionDAO();
    }

    // **Agregar Reserva**
    public boolean addReserva(Reserva reserva) {
        String query = "INSERT INTO reservas (cantPersonas, idHuesped) VALUES (?, ?)";
        return connectionDAO.executeUpdate(query,
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

    /* *Obtener una Reserva por ID**
    public Reserva getReservaById(int idReserva) {
        String query = "SELECT * FROM reservas WHERE idReserva = ?";
        try {
            ResultSet resultSet = connectionDAO.executeQuery(query, idReserva);
            if (resultSet.next()) {
                int cantPersonas = resultSet.getInt("cantPersonas");
                int idHuesped = resultSet.getInt("idHuesped");
                Date fechaReserva = resultSet.getDate("fechaReserva");

                Huesped huesped = new Huesped(idHuesped);
                return new Reserva(idReserva, cantPersonas, huesped, fechaReserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

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

}

