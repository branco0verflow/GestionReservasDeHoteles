package org.example.view;

import org.example.controller.*;
import org.example.model.*;

import java.text.DateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReservaView {
    private ReservaController reservaController;
    private HuespedController huespedController;
    private HotelController hotelController;
    private HabitacionController habitacionController;
    private HabitacionReservaController habitReservController;
    private Scanner scanner;

    public ReservaView(Scanner scanner) {
        this.reservaController = new ReservaController();
        this.huespedController = new HuespedController();
        this.hotelController = new HotelController();
        this.habitacionController = new HabitacionController();
        this.habitReservController = new HabitacionReservaController();
        this.scanner = scanner;
    }

    public void manageReserva() {
        int option = -1;
        do {
            System.out.println("\n\n\n----- GESTIÓN DE RESERVAS -----");
            System.out.println("1. Crear nueva reserva");
            System.out.println("2. Listar reservas");
            System.out.println("3. Modificar reserva");
            System.out.println("4. Eliminar reserva según ID");
            System.out.println("5. Marcar reserva como ocupada");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    CreateReserva();
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
                case 5:
                    MarcarOcupadas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }



    private void MarcarOcupadas(){
        System.out.println("\n\nREGISTRAR HABITACIÓN COMO OCUPADA\n");
        Huesped responsable = null;

        while (responsable == null) {
            System.out.print("Ingrese Número de Documento de huesped: ");
            int numDoc = scanner.nextInt();
            scanner.nextLine();
            List<Huesped> listaHuesp = reservaController.listHuesp();
            responsable = listaHuesp.stream().filter(h -> h.getNumDoc() == numDoc).findFirst().orElse(null);
            if (responsable == null) {
                System.out.println("NO EXISTE un usuario registrado con ese número de documento.");
                return;
            }
        }






    }







    private void CreateReserva() {

        Ciudad ciudadObject = null;

        System.out.println("TIEMPO DE ESTADÍA\n");

        System.out.print("RESERVAR DESDE (YYYY-MM-DD): ");
        String inicio = scanner.nextLine();
        System.out.print("RESERVAR HASTA (YYYY-MM-DD): ");
        String fin = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaInicio;
        Date fechaFin;

        try {
            fechaInicio = formatter.parse(inicio);
            fechaFin = formatter.parse(fin);

            if (fechaInicio.after(fechaFin)) {
                System.out.println("La fecha de inicio no puede ser mayor o igual a la fecha de fin.");
                return;
            }
        } catch (ParseException e) {
            System.out.println("Error al interpretar las fechas. Asegúrate de usar el formato 'YYYY-MM-DD'.");
            return;
        }

         long diasRestantes = (fechaFin.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24);

        // Listar tipos de habitación
        List<TipoHabit> listTipos = habitacionController.listTipoHabit();
        System.out.println("\n\n   TIPOS DE HABITACIÓN ");
        System.out.println("---------------------------------------------");
        for (TipoHabit tipo : listTipos) {
            System.out.println(tipo.getId() + "- " + tipo.getNombre());
            System.out.println("---------------------------------------------");
        }
        System.out.print("Seleccione el tipo de habitación: ");
        int idTipoHabit = scanner.nextInt();
        scanner.nextLine();

        TipoHabit tipoHabitacion = listTipos.stream().filter(tipo -> tipo.getId() == idTipoHabit).findFirst().orElse(null);

        if (tipoHabitacion == null) {
            System.out.println("Tipo de habitación no encontrado.");
            return;
        }

        // Selección de país y ciudad
        System.out.println("SELECCIONA DESTINO\n");
        List<Pais> listPaisess = hotelController.listPaises();
        System.out.println("---------------------------------------------");
        for (Pais pais : listPaisess) {
            System.out.println(pais.getId() + " -" + " Nombre: " + pais.getName());
            System.out.println("---------------------------------------------");
        }
        System.out.print("Seleccione el país: ");
        int idPais = scanner.nextInt();
        scanner.nextLine();

        List<Ciudad> listCiudadess = hotelController.listCiudades(idPais);
        System.out.println("\n\n");
        System.out.println("---------------------------------------------");
        for (Ciudad ciu : listCiudadess) {
            System.out.println(ciu.getId() + " -" + " Nombre: " + ciu.getName());
            System.out.println("---------------------------------------------");
        }
        System.out.print("Seleccione la ciudad: ");
        int idCiudad = scanner.nextInt();
        scanner.nextLine();

        for (Ciudad ciudades : listCiudadess) {
            if (ciudades.getId() == idCiudad) {
                ciudadObject = ciudades;
            }
        }
        List<Hotel> hotels = hotelController.getAllHotels();
        List<Hotel> hotelsFiltrados = hotels.stream().filter(h -> h.getCiudad().getId() == idCiudad).toList();

        if (hotelsFiltrados.isEmpty()) {
            System.out.println("No hay hoteles disponibles.");
        } else {

            System.out.println("\n\n");
            System.out.printf("%-5s | %-50s | %-10s | %-30s%n", " -", "Nombre", "Estrellas", "Dirección");
            System.out.println("-----------------------------------------------------------------------------------------------------");

            for (Hotel hotel : hotelsFiltrados) {
                System.out.printf("%-5d | %-50s | %-10d | %-30s%n",
                        hotel.getId(),
                        hotel.getName(),
                        hotel.getCantEstrella(),
                        hotel.getDireccion()
                );
            }
        }

        System.out.print("Selecciona un hotel para conocer su disponibilidad: ");
        int idHotel = scanner.nextInt();
        scanner.nextLine();


        List<Habitacion> habitDisponiblesParams = reservaController.habitacionesDisponiblesParams(ciudadObject.getId(), tipoHabitacion.getId(), inicio, fin);
        // Ahora filtramos el hotel seleccionado
        List<Habitacion> habitacionesFiltradas = habitDisponiblesParams.stream().filter(h -> h.getHotel().getId() == idHotel).toList();

        if (habitDisponiblesParams.isEmpty()) {
            System.out.println("No hay habitaciones disponibles que cumplan los requisitos.");
            return;
        }

        // Mostrar habitaciones disponibles

        List<Tarifa> tarifaList = habitacionController.tarifaList();
        Tarifa tarifa = tarifaList.stream().filter(t -> t.getId() == idTipoHabit).findFirst().orElse(null);


        if (habitacionesFiltradas.isEmpty()) {
            System.out.println("\n\nNO HAY HABITACIONES DISPONIBLES PARA ESTA FECHA\n\n");
        } else {
            // Listar habitaciones disponibles
            System.out.println("\n\nHABITACIONES DISPONIBLES ENCONTRADAS\n");
            habitacionesFiltradas.forEach(habitacion -> {
                System.out.println("ID: " + habitacion.getId());
                System.out.println("TIPO: " + habitacion.getTipoHabit().getNombre());
                System.out.println("CAMA DOBLE: " + (habitacion.isCamaDoble() ? "Sí" : "No"));
                System.out.println("AIRE ACONDICIONADO: " + (habitacion.isAireAcon() ? "Sí" : "No"));
                System.out.println("TIENE BALCÓN: " + (habitacion.isBalcon() ? "Sí" : "No"));
                System.out.println("AMENITIES: " + (habitacion.isAmenities() ? "Sí" : "No"));
                System.out.println("CON VISTA A: " + habitacion.getVista());
                System.out.println("HOTEL: " + habitacion.getHotel().getName());
                System.out.println("\nESTADÍA POR " + diasRestantes + " DÍAS");
                System.out.println("PRECIO TOTAL: U$D " + (diasRestantes * tarifa.getPrecio()));
                System.out.println("---------------------------------------------");
            });
        }






        int cantidadHabitaciones = 0;
        boolean reservaValida = false;

        while (!reservaValida) {
            try {
                System.out.println("\nHay " + habitacionesFiltradas.size() + " disponibles\n");
                System.out.print("¿Cuántas habitaciones de tipo " + tipoHabitacion.getNombre() + " quieres reservar?: ");
                cantidadHabitaciones = scanner.nextInt();

                if (cantidadHabitaciones <= 0) {
                    System.out.println("Número no válido. Ingrese un número mayor a 0.");
                    continue;
                }

                if (habitacionesFiltradas.size() < cantidadHabitaciones) {
                    System.out.println("Solo existen " + habitacionesFiltradas.size() + " habitaciones disponibles.");

                    System.out.print("¿Deseas ingresar otra cantidad? (S/N): ");
                    String respuesta = scanner.next().trim();

                    if (respuesta.equalsIgnoreCase("N")) {
                        System.out.println("\nReserva cancelada.\n");
                        return;  // Salir del proceso de reserva
                    }
                } else {
                    reservaValida = true;
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor ingrese un número.");
                scanner.nextLine();
            }
        }


        Huesped responsable = null;

        while (responsable == null) {
            System.out.print("Ingrese Número de Documento de Responsable: ");
            int numDoc = scanner.nextInt();
            scanner.nextLine();

            List<Huesped> listaHuesp = reservaController.listHuesp();
            responsable = listaHuesp.stream()
                    .filter(h -> h.getNumDoc() == numDoc)
                    .findFirst()
                    .orElse(null);

            if (responsable == null) {
                System.out.println("NO EXISTE un usuario registrado con ese número de documento.");
                System.out.print("¿Desea crear un nuevo huésped? (S/N): ");
                String respuesta = scanner.nextLine().trim().toLowerCase();

                if (respuesta.equals("s")) {
                    insertHuesped();

                    listaHuesp = reservaController.listHuesp();
                    responsable = listaHuesp.stream()
                            .filter(h -> h.getNumDoc() == numDoc)
                            .findFirst()
                            .orElse(null);
                } else {
                    System.out.println("Reserva cancelada. Volver al menú principal.");
                    return;
                }
            }
        }

        System.out.println("Huésped responsable encontrado: " + responsable.getName());


        System.out.print("Ingresa la cantidad de personas: ");
        int cantPersonas = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nAgrega una observación: ");
        String observacion = scanner.nextLine();


        System.out.println("\n\nCONFIRMA LOS DATOS");
        System.out.println();


        // Crear la reserva
        Reserva reserva = new Reserva(cantPersonas, responsable);
        int idReserva = reservaController.addReserva(reserva); // Obtiene el ID generado

        if (idReserva != -1) {
            reserva.setId(idReserva); // Asigna el ID al objeto reserva
            System.out.println("\nReserva creada correctamente con ID: " + idReserva + "\n");

            // Asignar las habitaciones a la reserva
            for (int i = 0; i < cantidadHabitaciones; i++) {
                Habitacion habitacion = habitacionesFiltradas.get(i);


                HabitacionReserva habitacionReserva = new HabitacionReserva(habitacion, fechaInicio, fechaFin, observacion, reserva);

                habitReservController.addHabitacionReserva(habitacionReserva);  // Agregar la habitación a la reserva
            }

            System.out.println("\nReserva completada con " + cantidadHabitaciones + " habitaciones.\n");


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


    private void insertHuesped() {
        System.out.print("Ingrese el nombre: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el apellido paterno: ");
        String apaterno = scanner.nextLine();
        System.out.print("Ingrese el apellido materno: ");
        String amaterno = scanner.nextLine();

        List<TipoDoc> listTipoDocs = huespedController.listTipoDocs();

        System.out.println("---------------------------------------------");

        for (TipoDoc tipos : listTipoDocs) {
            System.out.println(tipos.getId() + " - " + "\nNombre: " + tipos.getName());
            System.out.println("---------------------------------------------");
        }

        System.out.println("Seleccione un tipo de documento:");
        int tipoInt = scanner.nextInt();

        TipoDoc TipoObject = null;
        for (TipoDoc tipo : listTipoDocs) {
            if (tipo.getId() == tipoInt) {
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

        for (Pais pais : listPaisess) {
            System.out.println("Id:" + pais.getId() + "\nNombre: " + pais.getName());
            System.out.println("---------------------------------------------");
        }

        System.out.print("Seleccione el pais de nacimiento: ");
        int paisInt = scanner.nextInt();
        scanner.nextLine();

        Pais PaisObject = null;
        for (Pais paises : listPaisess) {
            if (paises.getId() == paisInt) {
                PaisObject = paises;
            }
        }

        System.out.print("Ingrese el número de contacto: ");
        String telefono = scanner.nextLine();


        Huesped huesped = new Huesped(name, apaterno, amaterno, TipoObject, numDoc, fechaNac, PaisObject, telefono);
        boolean userInserted = this.huespedController.insertHuesped(huesped);

        if (userInserted) {
            System.out.println("Usuario insertado! ");
        } else {
            System.out.println("Ocurrió un error al insertar el usuario");
        }

    }


}

