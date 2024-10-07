package org.example.DAO;


import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.TipoDoc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HuespedDAO {
    private ConnectionDAO connectionDAO;


    public HuespedDAO() {this.connectionDAO = new ConnectionDAO();}

    public boolean insertHuesped(Huesped huesped){
        String query = "INSERT INTO huespedes (nombre, apaterno, amaterno, idTipoDoc, numDoc, fechaNac, idPais) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        return connectionDAO.executeUpdate(query, huesped.getName(), huesped.getApaterno(), huesped.getAmaterno(), huesped.getTipoDoc().getId(), huesped.getNumDoc(), huesped.getFechaNac(), huesped.getPais().getId());
    }

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


    public List<Huesped> listHuespedes(){
        String query = "select h.idHuesped, h.nombre, h.apaterno, h.amaterno, h.idTipoDoc, t.nombreTipo, h.numDoc, h.fechaNac, h.idPais, p.nombreP  from huespedes h, tipoDoc t, paises p Where h.idTipoDoc = t.idTipoDoc AND h.idPais = p.idPais;";
        List<Huesped> huesplist = new ArrayList<>();

        try{
            ResultSet resultSet = connectionDAO.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                String paterno = resultSet.getString(3);
                String materno = resultSet.getString(3);
                TipoDoc tipoDoc = new TipoDoc(resultSet.getInt(4), resultSet.getString(5));
                int numDoc = resultSet.getInt(6);
                String fechaNac = resultSet.getString(6);
                Pais pais = new Pais(resultSet.getInt(7), resultSet.getString(8));

                // Crear el objeto Huesped y agregarlo a la lista
                Huesped huesped = new Huesped(id, nombre, paterno, materno, tipoDoc, numDoc,fechaNac, pais);
                huesplist.add(huesped);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return huesplist;
    }


}
