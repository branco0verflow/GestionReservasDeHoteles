package org.example.view;

import org.example.DAO.ConnectionDAO;
import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.TipoHabit;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HabitacionView {

    private HabitacionController habitController;
    private Scanner scanner;
    public HabitacionView(Scanner scanner) { this.habitController = new HabitacionController(); this.scanner = scanner;}



    public void manageHabitacion() {
        int option = -1;
        do {
            System.out.println("\n\nGESTIÓN DE HABITACIONES\n");
            System.out.println("1. Crear nueva Habitación");
            System.out.println("2. Listar habitaciones según hotel");
            System.out.println("3. Modificar una habitación");
            System.out.println("4. Eliminar una habitación");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    addHabitacion();
                    break;
                case 2:
                    listarHabitacionesPorHotel();
                    break;
                case 3:
                    updateHabitacion();
                    break;
                case 4:
                    deleteHabitacion();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }


    // CRUD

    public void addHabitacion() {

        // Listar hoteles
        List<Hotel> listHoteles = habitController.listHotels();
        System.out.println("\n\n   Hoteles Disponibles ");
        System.out.println("---------------------------------------------");
        for (Hotel hotel : listHoteles) {
            System.out.println(hotel.getId() + " - " + hotel.getName());
            System.out.println("---------------------------------------------");
        }
        System.out.print("Seleccione el hotel al que pertenece la habitación: ");
        int idHotel = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Hotel hotel = listHoteles.stream()
                .filter(h -> h.getId() == idHotel)
                .findFirst()
                .orElse(null);

        if (hotel == null) {
            System.out.println("Hotel no encontrado.");
            return;
        }


        System.out.print("Ingrese la cantidad de camas: ");
        int cantCama = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        boolean camaDoble = leerBoolean("¿La cama es doble?");
        boolean ocupado = leerBoolean("¿Está ocupado?");
        boolean aireAcon = leerBoolean("¿Tiene aire acondicionado?");
        boolean balcon = leerBoolean("¿Tiene balcón?");
        boolean amenities = leerBoolean("¿Tiene amenities?");

        System.out.print("Ingrese la vista: ");
        String vista = scanner.nextLine();

        // Listar tipos de habitación
        List<TipoHabit> listTipos = habitController.listTipoHabit();
        System.out.println("\n\n   Tipos de Habitación ");
        System.out.println("---------------------------------------------");
        for (TipoHabit tipo : listTipos) {
            System.out.println(tipo.getId() + " - " + tipo.getNombre());
            System.out.println("---------------------------------------------");
        }
        System.out.print("Seleccione el tipo de habitación: ");
        int idTipoHabit = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        TipoHabit tipoHabitacion = listTipos.stream()
                .filter(tipo -> tipo.getId() == idTipoHabit)
                .findFirst()
                .orElse(null);

        if (tipoHabitacion == null) {
            System.out.println("Tipo de habitación no encontrado.");
            return;
        }



        // Crear y agregar habitación
        Habitacion habitacion = new Habitacion(cantCama, camaDoble, ocupado, aireAcon, balcon, amenities, vista, tipoHabitacion, hotel);
        if (habitController.addHabit(habitacion)) {
            System.out.println("Habitación agregada exitosamente.");
        } else {
            System.out.println("Error al agregar la habitación.");
        }
    }


    public void updateHabitacion() {
        System.out.print("Ingrese el ID de la habitación a modificar: ");
        int idHabitacion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        List<Habitacion> habitacionesList = habitController.getAllHabit();
        boolean habitacionEncontrada = false;

        for (Habitacion habitacion : habitacionesList) {
            if (habitacion.getId() == idHabitacion) {
                habitacionEncontrada = true;

                // Listar hoteles
                List<Hotel> hotelesList = habitController.listHotels();
                System.out.println("\n----- Hoteles Disponibles -----");
                for (Hotel hotel : hotelesList) {
                    System.out.println(hotel.getId() + " - " + hotel.getName());
                }
                System.out.print("Seleccione el nuevo hotel (Actual: "+ habitacion.getId()+ "- " + habitacion.getHotel().getName() + "): ");
                int idHotel = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                Hotel hotel = hotelesList.stream()
                        .filter(h -> h.getId() == idHotel)
                        .findFirst()
                        .orElse(null);

                if (hotel == null) {
                    System.out.println("Hotel no encontrado.");
                    return;
                }


                System.out.print("Ingrese la nueva cantidad de camas (actual: " + habitacion.getCantCama() + "): ");
                int cantCama = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                boolean camaDoble = leerBoolean("¿La cama es doble? (actual: " + habitacion.isCamaDoble() + ")");
                boolean ocupado = leerBoolean("¿Está ocupado? (actual: " + habitacion.isOcupado() + ")");
                boolean aireAcon = leerBoolean("¿Tiene aire acondicionado? (actual: " + habitacion.isAireAcon() + ")");
                boolean balcon = leerBoolean("¿Tiene balcón? (actual: " + habitacion.isBalcon() + ")");
                boolean amenities = leerBoolean("¿Tiene amenities? (actual: " + habitacion.isAmenities() + ")");

                System.out.print("Ingrese la nueva vista (actual: " + habitacion.getVista() + "): ");
                String vista = scanner.nextLine();

                // Listar tipos de habitación
                List<TipoHabit> tiposList = habitController.listTipoHabit();
                System.out.println("\n----- Tipos de Habitación -----");
                for (TipoHabit tipo : tiposList) {
                    System.out.println(tipo.getId() + " - " + tipo.getNombre());
                }
                System.out.print("Seleccione el nuevo tipo de habitación (actual: " + habitacion.getTipoHabit().getNombre() + "): ");
                int idTipoHabit = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                TipoHabit tipoHabitacion = tiposList.stream()
                        .filter(tipo -> tipo.getId() == idTipoHabit)
                        .findFirst()
                        .orElse(null);

                if (tipoHabitacion == null) {
                    System.out.println("Tipo de habitación no encontrado.");
                    return;
                }



                // Crear la nueva habitación con los valores actualizados
                Habitacion habitacionFinal = new Habitacion(
                        idHabitacion, cantCama, camaDoble, ocupado, aireAcon,
                        balcon, amenities, vista, tipoHabitacion, hotel
                );

                // Actualizar la habitación
                if (habitController.updateHabit(habitacionFinal)) {
                    System.out.println("Habitación actualizada exitosamente.");
                } else {
                    System.out.println("Error al actualizar la habitación.");
                }
                break;
            }
        }

        if (!habitacionEncontrada) {
            System.out.println("Habitación no encontrada.");
        }
    }

    public void listarHabitacionesPorHotel() {
        System.out.println("\n\nTODOS LOS HOTELES\n");
        List<Hotel> listHotels = habitController.listHotels();
        for(Hotel hotel : listHotels){
            System.out.println(hotel.getId() + "- " + hotel.getName());
        }


        System.out.print("\nSelecciona un hotel para ver sus habitaciones: ");
        int idHotelSeleccionado = scanner.nextInt();

        // Obtener el hotel correspondiente por su ID

        Hotel hotelSeleccionado = listHotels.stream()
                .filter(h -> h.getId() == idHotelSeleccionado)
                .findFirst()
                .orElse(null);

        if (hotelSeleccionado == null) {
            System.out.println("No se encontró un hotel con el ID proporcionado.");
            return;
        }

        System.out.println("\n\n---------------------------------------------");
        System.out.println("\nHOTEL: " + hotelSeleccionado.getName() + "\n");


        // Filtrar habitaciones del hotel seleccionado
        List<Habitacion> habitacionesList = habitController.getAllHabit();
        List<Habitacion> habitacionesDelHotel = habitacionesList.stream()
                .filter(h -> h.getHotel().getId() == idHotelSeleccionado)
                .toList();

        if (habitacionesDelHotel.isEmpty()) {
            System.out.println("No hay habitaciones registradas para este hotel.");
        } else {
            habitacionesDelHotel.forEach(habitacion -> {
                System.out.println("NÚMERO DE HABITACIÓN: " + habitacion.getId() +
                        "\nTIPO: " + habitacion.getTipoHabit().getNombre());
                System.out.println("Cantidad de camas: " + habitacion.getCantCama());
                System.out.println("Cama Doble: " + (habitacion.isCamaDoble() ? "Sí" : "No"));
                System.out.println("Estado actual: " + (habitacion.isOcupado() ? "Ocupado" : "Disponible"));
                System.out.println("Tiene aire acondicionado: " + (habitacion.isAireAcon() ? "Sí" : "No"));
                System.out.println("Tiene balcón: " + (habitacion.isBalcon() ? "Sí" : "No"));
                System.out.println("Cuenta con amenities: " + (habitacion.isAmenities() ? "Sí" : "No"));
                System.out.println("Tiene vista a: " + habitacion.getVista());
                System.out.println("---------------------------------------------");
            });
        }
    }



    public void deleteHabitacion() {
        System.out.print("Ingrese el ID de la habitación a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        if (habitController.deleteHabit(id)) {
            System.out.println("Habitación eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la habitación.");
        }
    }


    // Método convertir Bools en consola
    private boolean leerBoolean(String mensaje) {
        String input;
        do {
            System.out.print(mensaje + " (s/n): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("s")) return true;
            if (input.equals("n")) return false;
            System.out.println("Entrada no válida. Intente nuevamente.");
        } while (true);
    }


}
