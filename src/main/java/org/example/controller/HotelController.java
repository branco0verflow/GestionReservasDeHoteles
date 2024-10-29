package org.example.controller;
import org.example.DAO.HotelDAO;
import org.example.model.*;
import java.util.List;

public class HotelController {



    private HotelDAO hotelDAO;


    public HotelController() {
        this.hotelDAO = new HotelDAO();
    }

    public List<Habitacion> EncontrarHabitacionOcupadaSinReservaSegunIdHotel(int idHotel){
        return hotelDAO.encontrarHabitacionesOcupadasSinReserva(idHotel);
    }

    public List<HabitacionReserva> EncontrarHabitacionOcupadaSegunIdHotel(int idHotel){
        return hotelDAO.EncontrarHabitacionesOcupadas(idHotel);
    }

    public List<HabitacionReserva> EncontrarHabitacionesSegunIdHotel(int idHotel){
        return hotelDAO.EncontrarHabitacionesSegunIdHotel(idHotel);
    }

    public boolean addHotel(Hotel hotel) {
        return hotelDAO.addHotel(hotel);
    }

    public boolean updateHotel(Hotel hotel) {
        return hotelDAO.updateHotel(hotel);
    }

    public boolean deleteHotel(int idHotel) {
        return hotelDAO.deleteHotel(idHotel);
    }


    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }

    public List<Pais> listPaises(){
        return this.hotelDAO.listPaises();
    }

    public List<Ciudad> listCiudades(int ciudad){
        return this.hotelDAO.listCiudades(ciudad);
    }


}

