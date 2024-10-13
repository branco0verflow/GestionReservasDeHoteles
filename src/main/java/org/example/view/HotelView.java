package org.example.view;

import org.example.controller.HotelController;
import org.example.model.Ciudad;
import org.example.model.Hotel;
import org.example.model.Pais;

import java.util.Scanner;
import java.util.List;

public class HotelView {

    private HotelController hotelController;
    private Scanner scanner;


    public HotelView(Scanner scanner) { this.hotelController = new HotelController(); this.scanner = scanner;}

    public void manageHotel() {
        int option = -1;
        do {
            System.out.println("\n\n\n----- Gestión de Hoteles -----");
            System.out.println("1. Crear nuevo Hotel");
            System.out.println("2. Listar hoteles");
            System.out.println("3. Modificar un hoteles");
            System.out.println("4. Eliminar un hotel");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addHotel();
                    break;
                case 2:
                    viewAllHotels();
                    break;
                case 3:
                    updateHotel();
                    break;
                case 4:
                    deleteHotel();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    public void addHotel() {
        System.out.print("Ingrese el nombre del hotel: ");
        String name = scanner.nextLine();


        List<Pais> listPaisess = hotelController.listPaises();

        System.out.println("\n\n");
        System.out.println("---------------------------------------------");
        for (Pais pais : listPaisess){
            System.out.println(pais.getId() + " -"+ " Nombre: "+ pais.getName());
            System.out.println("---------------------------------------------");
        }
        System.out.print("Seleccione el país: ");
        int idPais = scanner.nextInt();

        Pais PaisObject = null;
        for(Pais paises : listPaisess){
            if(paises.getId() == idPais){
                PaisObject = paises;
            }
        }

        List<Ciudad> listCiudadess = hotelController.listCiudades(idPais);
        System.out.println("\n\n");
        System.out.println("---------------------------------------------");
        for (Ciudad pais : listCiudadess){
            System.out.println(pais.getId() + " -"+ " Nombre: "+ pais.getName());
            System.out.println("---------------------------------------------");
        }
        System.out.print("Seleccione la ciuadad: ");
        int idCiudad = scanner.nextInt();

        Ciudad ciudadObject = null;
        for(Ciudad ciudades : listCiudadess){
            if(ciudades.getId() == idCiudad){
                ciudadObject = ciudades;
            }
        }


        System.out.print("Ingrese la cantidad de estrellas: ");
        int cantEstrella = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea
        System.out.print("Ingrese la dirección: ");
        String direccion = scanner.nextLine();


        Hotel hotel = new Hotel(name, PaisObject, ciudadObject, cantEstrella, direccion);
        if (hotelController.addHotel(hotel)) {
            System.out.println("Hotel agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el hotel.");
        }
    }






    public void updateHotel() {
        System.out.print("Ingrese el ID del hotel a modificar: ");
        int idHotel = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        List<Hotel> hotelesList = hotelController.getAllHotels();
        boolean hotelEncontrado = false;

        for (Hotel hotel : hotelesList) {
            if (hotel.getId() == idHotel) {
                hotelEncontrado = true;

                System.out.print("Ingrese el nuevo nombre (actual: " + hotel.getName() + "): ");
                String name = scanner.nextLine();

                List<Pais> listPaises = hotelController.listPaises();
                System.out.println("\n---------------------------------------------");
                for (Pais pais : listPaises) {
                    System.out.println(pais.getId() + " - Nombre: " + pais.getName());
                    System.out.println("---------------------------------------------");
                }
                System.out.print("Ingrese el nuevo ID del país (actual: " + hotel.getPais().getName() + "): ");
                int idPais = scanner.nextInt();

                Pais paisObject = null;
                for (Pais pais : listPaises) {
                    if (pais.getId() == idPais) {
                        paisObject = pais;
                        break;
                    }
                }

                List<Ciudad> listCiudades = hotelController.listCiudades(idPais);
                System.out.println("\n---------------------------------------------");
                for (Ciudad ciudad : listCiudades) {
                    System.out.println(ciudad.getId() + " - Nombre: " + ciudad.getName());
                    System.out.println("---------------------------------------------");
                }
                System.out.print("Ingrese el nuevo ID de la ciudad (actual: " + hotel.getCiudad().getName() + "): ");
                int idCiudad = scanner.nextInt();

                Ciudad ciudadObject = null;
                for (Ciudad ciudad : listCiudades) {
                    if (ciudad.getId() == idCiudad) {
                        ciudadObject = ciudad;
                        break;
                    }
                }

                System.out.print("Ingrese la nueva cantidad de estrellas (actual: " + hotel.getCantEstrella() + "): ");
                int cantEstrella = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
                System.out.print("Ingrese la nueva dirección (actual: " + hotel.getDireccion() + "): ");
                String direccion = scanner.nextLine();

                Hotel hotelFinal = new Hotel(idHotel, name, paisObject, ciudadObject, cantEstrella, direccion);

                if (hotelController.updateHotel(hotelFinal)) {
                    System.out.println("Hotel actualizado exitosamente.");
                } else {
                    System.out.println("Error al actualizar el hotel.");
                }
                break;
            }
        }

        if (!hotelEncontrado) {
            System.out.println("Hotel no encontrado.");
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
            // Imprime el encabezado de la tabla
            System.out.println("\n\n");
            System.out.printf("%-5s | %-50s | %-10s | %-30s%n", "ID", "Nombre", "Estrellas", "Dirección");
            System.out.println("-----------------------------------------------------------------------------------------------------");

            // Imprime cada hotel en formato de tabla
            for (Hotel hotel : hotels) {
                System.out.printf("%-5d | %-50s | %-10d | %-30s%n",
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getCantEstrella(),
                        hotel.getDireccion()
                );
            }
        }
    }




}
