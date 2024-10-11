package org.example.DAO;


import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.TipoDoc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAO {
    private ConnectionDAO connectionDAO;


    public HuespedDAO() {this.connectionDAO = new ConnectionDAO();}



    // Eliminar Huesped
    public boolean deleteHuesped(int idHuesped) {
        String query = "DELETE FROM huespedes WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, idHuesped);
    }

    // Modificar huesped
    public boolean updateHuesped(Huesped huesped) {
        String query = "UPDATE huespedes SET nombre = ?, apaterno = ?, amaterno = ?, idTipoDoc = ?, numDoc = ?, fechaNac = ?, idPais = ?, telefono = ? WHERE idHuesped = ?";
        return connectionDAO.executeUpdate(query, huesped.getName(), huesped.getApaterno(), huesped.getAmaterno(),
                huesped.getTipoDoc().getId(), huesped.getNumDoc(), huesped.getFechaNac(), huesped.getPais().getId(),
                huesped.getTelefono(), huesped.getId());
    }

    //Insertar huesped
    public boolean insertHuesped(Huesped huesped){
        String query = "INSERT INTO huespedes (nombre, apaterno, amaterno, idTipoDoc, numDoc, fechaNac, idPais) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query, huesped.getName(), huesped.getApaterno(), huesped.getAmaterno(), huesped.getTipoDoc().getId(), huesped.getNumDoc(), huesped.getFechaNac(), huesped.getPais().getId());
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

    // Llamada a Tipos de documentos
    public List<TipoDoc> listTipoDoc(){
        String query = "SELECT idTipoDoc, nombreTipo FROM tipoDoc;";
        List<TipoDoc> TiposList = new ArrayList<>();

        try{
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);

                TipoDoc tipos = new TipoDoc(id, name);


                TiposList.add(tipos);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return TiposList;
    }

    // Listar huespedes
    public List<Huesped> listHuespedes(){
        String query = "select h.idHuesped, h.nombre, h.apaterno, h.amaterno, h.idTipoDoc, t.nombreTipo, h.numDoc, h.fechaNac, h.idPais, p.nombreP, h.telefono  from huespedes h, tipoDoc t, paises p Where h.idTipoDoc = t.idTipoDoc AND h.idPais = p.idPais;";
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


}
