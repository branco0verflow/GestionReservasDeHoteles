package org.example.view;

import org.example.controller.HotelController;
import org.example.model.Pais;

import java.util.Scanner;
import java.util.List;

public class HotelView {

    /*

    private HotelController hotelController;
    private Scanner scanner = new Scanner(System.in);

    public HotelView(HotelController hotelController) {
        this.hotelController = hotelController;
    }

    public void addHotel() {
        System.out.print("Ingrese el nombre del hotel: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el ID del país: ");
        int idPais = scanner.nextInt();
        System.out.print("Ingrese el ID de la ciudad: ");
        int idCiudad = scanner.nextInt();
        System.out.print("Ingrese la cantidad de estrellas: ");
        int cantEstrella = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea
        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine();

        Pais pais = new PaisDAO(new ConnectionDAO()).getPaisById(idPais);
        Ciudad ciudad = new CiudadDAO(new ConnectionDAO()).getCiudadById(idCiudad);
        Hotel hotel = new Hotel(0, name, pais, ciudad, cantEstrella, direccion);
        if (hotelController.addHotel(hotel)) {
            System.out.println("Hotel agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el hotel.");
        }
    }

    public void updateHotel() {
        System.out.print("Ingrese el ID del hotel a modificar: ");
        int idHotel = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        Hotel hotel = hotelController.getHotelById(idHotel);
        if (hotel == null) {
            System.out.println("Hotel no encontrado.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre (actual: " + hotel.getName() + "): ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el nuevo ID del país (actual: " + hotel.getPais().getId() + "): ");
        int idPais = scanner.nextInt();
        System.out.print("Ingrese el nuevo ID de la ciudad (actual: " + hotel.getCiudad().getId() + "): ");
        int idCiudad = scanner.nextInt();
        System.out.print("Ingrese la nueva cantidad de estrellas (actual: " + hotel.getCantEstrella() + "): ");
        int cantEstrella = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea
        System.out.print("Ingrese la nueva dirección (actual: " + hotel.getDireccion() + "): ");
        String direccion = scanner.nextLine();

        Pais pais = new PaisDAO(new ConnectionDAO()).getPaisById(idPais);
        Ciudad ciudad = new CiudadDAO(new ConnectionDAO()).getCiudadById(idCiudad);

        hotel.setName(name);
        hotel.setPais(pais);
        hotel.setCiudad(ciudad);
        hotel.setCantEstrella(cantEstrella);
        hotel.setDireccion(direccion);

        if (hotelController.updateHotel(hotel)) {
            System.out.println("Hotel actualizado exitosamente.");
        } else {
            System.out.println("Error al actualizar el hotel.");
        }
    }

    public void deleteHotel() {
        System.out.print("Ingrese el ID del hotel a eliminar: ");
        int idHotel = scanner.nextInt();
        if (hotelController.deleteHotel(idHotel)) {
            System.out.println("Hotel eliminado exitosamente.");
        } else {
            System.out.println("Error al eliminar el hotel.");
        }
    }

    public void viewAllHotels() {
        List<Hotel> hotels = hotelController.getAllHotels();
        if (hotels.isEmpty()) {
            System.out.println("No hay hoteles disponibles.");
        } else {
            for (Hotel hotel : hotels) {
                System.out.println("ID: " + hotel.getId() + ", Nombre: " + hotel.getName() + ", Estrellas: " + hotel.getCantEstrella());
            }
        }
    }


     */
}
