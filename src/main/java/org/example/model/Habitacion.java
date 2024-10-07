package org.example.model;

public class Habitacion {

    private int id;
    private int cantCama;
    private boolean camaMatrimonial;
    private boolean reservado;
    private boolean ocupado;
    private boolean aireAcon;
    private boolean balcon;
    private boolean amenities;
    private String vista;
    private String tipoHabit;
    private Tarifa tarifa;
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

    public boolean isCamaMatrimonial() {
        return camaMatrimonial;
    }

    public void setCamaMatrimonial(boolean camaMatrimonial) {
        this.camaMatrimonial = camaMatrimonial;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
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

    public String getTipoHabit() {
        return tipoHabit;
    }

    public void setTipoHabit(String tipoHabit) {
        this.tipoHabit = tipoHabit;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Habitacion(int id, int cantCama, boolean camaMatrimonial, boolean reservado, boolean ocupado, boolean aireAcon, boolean balcon, boolean amenities, String vista, String tipoHabit, Tarifa tarifa, Hotel hotel) {
        this.id = id;
        this.cantCama = cantCama;
        this.camaMatrimonial = camaMatrimonial;
        this.reservado = reservado;
        this.ocupado = ocupado;
        this.aireAcon = aireAcon;
        this.balcon = balcon;
        this.amenities = amenities;
        this.vista = vista;
        this.tipoHabit = tipoHabit;
        this.tarifa = tarifa;
        this.hotel = hotel;
    }
}
