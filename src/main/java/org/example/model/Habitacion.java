package org.example.model;

public class Habitacion {

    private int id;
    private int cantCama;
    private boolean camaDoble;
    private boolean ocupado;
    private boolean aireAcon;
    private boolean balcon;
    private boolean amenities;
    private String vista;
    private TipoHabit tipoHabit;
    private Hotel hotel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantCama() {
        return cantCama;
    }

    public void setCantCama(int cantCama) {
        this.cantCama = cantCama;
    }

    public boolean isCamaDoble() {
        return camaDoble;
    }

    public void setCamaDoble(boolean camaDoble) {
        this.camaDoble = camaDoble;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isAireAcon() {
        return aireAcon;
    }

    public void setAireAcon(boolean aireAcon) {
        this.aireAcon = aireAcon;
    }

    public boolean isBalcon() {
        return balcon;
    }

    public void setBalcon(boolean balcon) {
        this.balcon = balcon;
    }

    public boolean isAmenities() {
        return amenities;
    }

    public void setAmenities(boolean amenities) {
        this.amenities = amenities;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }

    public TipoHabit getTipoHabit() {
        return tipoHabit;
    }

    public void setTipoHabit(TipoHabit tipoHabit) {
        this.tipoHabit = tipoHabit;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Habitacion(int id, int cantCama, boolean camaDoble, boolean ocupado, boolean aireAcon, boolean balcon, boolean amenities, String vista, TipoHabit tipoHabit, Hotel hotel) {
        this.id = id;
        this.cantCama = cantCama;
        this.camaDoble = camaDoble;
        this.ocupado = ocupado;
        this.aireAcon = aireAcon;
        this.balcon = balcon;
        this.amenities = amenities;
        this.vista = vista;
        this.tipoHabit = tipoHabit;
        this.hotel = hotel;
    }

    public Habitacion(int cantCama, boolean camaDoble, boolean ocupado, boolean aireAcon, boolean balcon, boolean amenities, String vista, TipoHabit tipoHabit, Hotel hotel) {
        this.cantCama = cantCama;
        this.camaDoble = camaDoble;
        this.ocupado = ocupado;
        this.aireAcon = aireAcon;
        this.balcon = balcon;
        this.amenities = amenities;
        this.vista = vista;
        this.tipoHabit = tipoHabit;
        this.hotel = hotel;
    }

    public Habitacion(int id) {
        this.id = id;
    }
}
