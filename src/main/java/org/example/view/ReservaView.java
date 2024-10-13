package org.example.view;

import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.controller.HuespedController;
import org.example.controller.ReservaController;
import org.example.model.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReservaView {
    private ReservaController reservaController;
    private HuespedController huespedController;
    private HotelController hotelController;
    private HabitacionController habitacionController;
    private Scanner scanner;

    public ReservaView(Scanner scanner) { this.reservaController = new ReservaController(); this.huespedController = new HuespedController(); this.hotelController = new HotelController(); this.habitacionController = new HabitacionController(); this.scanner = scanner;}

    public void manageReserva() {
        int option = -1;
        do {
            System.out.println("\n\n\n----- GESTIÓN DE RESERVAS -----");
            System.out.println("1. Crear nueva reserva");
            System.out.println("2. Listar reservas");
            System.out.println("3. Modificar reserva");
            System.out.println("4. Eliminar reserva según ID");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addReserva();
                    break;
                case 2:
                    //viewAllReservas();
                    break;
                case 3:
                    //updateReserva();
                    break;
                case 4:
                    //deleteReserva();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }

    private void addReserva() {
        System.out.println("\n\nCREAR RESERVA\n");

        System.out.print("Ingrese Número de Documento de Responsable: ");
        int numDoc = scanner.nextInt();
        scanner.nextLine();

        List<Huesped> listaHuesp = reservaController.listHuesp();
        Huesped responsable = listaHuesp.stream().filter(h -> h.getNumDoc() == numDoc).findFirst().orElse(null);

        if (responsable == null) {
            System.out.println("NO EXISTE un usuario registrado con ese número de documento");
            System.out.println("¿Desea crear un nuevo huésped? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();

            if (respuesta.equals("s")) {
                insertHuesped();
            } else {
                System.out.println("Volver al menú principal.");
                return;
            }
        }

        System.out.println("Responsable: " + responsable.getName() + " " + responsable.getApaterno() + "\n");
        System.out.print("Ingresa la cantidad de personas: ");
        int cantPersonas = scanner.nextInt();
        scanner.nextLine();

         Reserva reserva = new Reserva(cantPersonas, responsable);
        if (reservaController.addReserva(reserva)) {

            List<Pais> listPaisess = hotelController.listPaises();

            System.out.println("\n\n");
            System.out.println("---------------------------------------------");
            for (Pais pais : listPaisess){
                System.out.println(pais.getId() + " -"+ " Nombre: "+ pais.getName());
                System.out.println("---------------------------------------------");
            }
            System.out.print("Seleccione el país: ");
            int idPais = scanner.nextInt();
            scanner.nextLine();

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
            scanner.nextLine();

            Ciudad ciudadObject = null;
            for(Ciudad ciudades : listCiudadess){
                if(ciudades.getId() == idCiudad){
                    ciudadObject = ciudades;
                }
            }

            List<Hotel> hotels = hotelController.getAllHotels();
            List<Hotel> hotelsFiltrados = hotels.stream().filter(h -> h.getCiudad().getId() == idCiudad).toList();

            if (hotelsFiltrados.isEmpty()) {
                System.out.println("No hay hoteles disponibles.");
            } else {
                // Imprime el encabezado de la tabla
                System.out.println("\n\n");
                System.out.printf("%-5s | %-50s | %-10s | %-30s%n", " -", "Nombre", "Estrellas", "Dirección");
                System.out.println("-----------------------------------------------------------------------------------------------------");

                // Imprime cada hotel en formato de tabla
                for (Hotel hotel : hotelsFiltrados) {
                    System.out.printf("%-5d | %-50s | %-10d | %-30s%n",
                            hotel.getId(),
                            hotel.getName(),
                            hotel.getCantEstrella(),
                            hotel.getDireccion()
                    );
                }

                System.out.print("Selecciona un hotel para conocer su disponibilidad: ");
                int idHotel = scanner.nextInt();
                Hotel hotelSeleccionado = hotelsFiltrados.stream().filter(hotel -> hotel.getId() == idHotel).findFirst().orElse(null);
                scanner.nextLine();
            }






            System.out.print("RESERVAR DESDE (YYYY-MM-DD): ");
            String fechaInicioStr = scanner.nextLine();
            System.out.print("RESERVAR HASTA (YYYY-MM-DD): ");
            String fechaFinStr = scanner.nextLine();


            // Define el formato de la fecha
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date inicio = null;
            Date fin = null;

            try {
                fin = (Date) sdf.parse(fechaFinStr);
                inicio = (Date) sdf.parse(fechaInicioStr);
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido. Por favor, usa el formato YYYY-MM-DD.");
            }
        } else {
             System.out.println("Ocurrió un error al crear la reserva.");
        }
    }

    private void viewAllReservas() {
        // Aquí puedes implementar la lógica para listar todas las reservas
        // List<Reserva> reservas = reservaController.getAllReservas();
        // for (Reserva reserva : reservas) {
        //     System.out.println(reserva); // Personaliza la forma en que deseas mostrar la reserva
        // }
    }

    private void updateReserva() {
        // Aquí puedes implementar la lógica para actualizar una reserva
        // System.out.print("Ingresa el ID de la reserva a modificar: ");
        // int idReserva = scanner.nextInt();
        // Aquí debes obtener los nuevos datos y luego actualizar
        // if (reservaController.updateReserva(reserva)) {
        //     System.out.println("Reserva actualizada correctamente.");
        // } else {
        //     System.out.println("Ocurrió un error al actualizar la reserva.");
        // }
    }

    private void deleteReserva() {
        // Aquí puedes implementar la lógica para eliminar una reserva
        System.out.print("Ingresa el ID de la reserva a eliminar: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        // if (reservaController.deleteReserva(idReserva)) {
        //     System.out.println("Reserva eliminada correctamente.");
        // } else {
        //     System.out.println("Ocurrió un error al eliminar la reserva.");
        // }
    }

    private void insertHuesped(){
        System.out.print("Ingrese el nombre: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el apellido paterno: ");
        String apaterno = scanner.nextLine();
        System.out.print("Ingrese el apellido materno: ");
        String amaterno = scanner.nextLine();

        List<TipoDoc> listTipoDocs = huespedController.listTipoDocs();

        System.out.println("---------------------------------------------");

        for (TipoDoc tipos : listTipoDocs){
            System.out.println(tipos.getId() + " - "+ "\nNombre: "+ tipos.getName());
            System.out.println("---------------------------------------------");
        }

        System.out.println("Seleccione un tipo de documento:");
        int tipoInt = scanner.nextInt();

        TipoDoc TipoObject = null;
        for(TipoDoc tipo : listTipoDocs){
            if(tipo.getId() == tipoInt){
                TipoObject = tipo;
            }
        }

        System.out.print("Ingrese su número de documento: ");
        int numDoc = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese su fecha de nacimiento YYYY-MM--DD: ");
        String fechaNac = scanner.nextLine();


        List<Pais> listPaisess = huespedController.listPaises();
        System.out.println("\n\n");
        System.out.println("---------------------------------------------");

        for (Pais pais : listPaisess){
            System.out.println("Id:"+ pais.getId() + "\nNombre: "+ pais.getName());
            System.out.println("---------------------------------------------");
        }

        System.out.print("Seleccione el pais de nacimiento: ");
        int paisInt = scanner.nextInt();

        Pais PaisObject = null;
        for(Pais paises : listPaisess){
            if(paises.getId() == paisInt){
                PaisObject = paises;
            }
        }

        System.out.print("Ingrese el número de contacto: ");
        String telefono = scanner.nextLine();


        Huesped huesped = new Huesped(name, apaterno, amaterno, TipoObject, numDoc, fechaNac, PaisObject, telefono);
        boolean userInserted = this.huespedController.insertHuesped(huesped);

        if (userInserted){
            System.out.println("Usuario insertado! ");
        } else {
            System.out.println("Ocurrió un error al insertar el usuario");
        }
    }



}

