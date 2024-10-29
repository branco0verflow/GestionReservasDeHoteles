package org.example.view;
import org.example.controller.*;
import org.example.model.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
            System.out.println("\n\n\n    GESTIÓN DE RESERVAS\n");
            System.out.println("1. Crear nueva reserva");
            System.out.println("2. Listar reservas");
            System.out.println("3. Modificar reserva");
            System.out.println("4. Eliminar reserva según ID");
            System.out.println("5. Filtrar habitaciones reservadas entre fechas");

            System.out.println("\nCHECK IN - CHECK OUT");
            System.out.println("6. Marcar habitación con reserva como ocupada");
            System.out.println("7. Marcar habitación como ocupada - SIN PREVIA RESERVA -");
            System.out.println("8. Marcar habitación como desocupada");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addReserva();
                    break;
                case 2:
                    ListAllHabitReservas();
                    break;
                case 3:
                    updateHabitacionReserva();
                    break;
                case 4:
                    deleteReserva();
                    break;
                case 5:
                    filtrarHabitacionesReservadasEntreFechas();
                    break;
                case 6:
                    MarcarOcupadasConReserva();
                    break;
                case 7:
                    marcarHabitacionesSinReservasComoOcupadas();
                    break;
                case 8:
                    MarcarDESOcupadas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }


    public void filtrarHabitacionesReservadasEntreFechas(){
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        String fechaInicio = scanner.nextLine();
        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        String fechaFin = scanner.nextLine();

        List<HabitacionReserva> habitacionesEncontradasList = reservaController.EncontrarReservasEntreFechas(fechaInicio, fechaFin);

        if(habitacionesEncontradasList.isEmpty()){
            System.out.println("\nNo se encontraron habitaciones reservadas para la fecha consultada\n");
        }else{
            for (HabitacionReserva reserva : habitacionesEncontradasList) {
                System.out.println("\n----------------------------------");
                System.out.println("Reserva creada el " + reserva.getReserva().getFechaReserva());
                System.out.println("Habitación ID: " + reserva.getHabitacion().getId() + "\nDe tipo: " + reserva.getHabitacion().getTipoHabit().getNombre());
                System.out.println("Con vista a " + reserva.getHabitacion().getVista());
                System.out.println("Para " + reserva.getReserva().getCantPersonas() + " personas");
                System.out.println("Hotel: " + reserva.getHabitacion().getHotel().getName());
                System.out.println("Desde: " + reserva.getFechaInicio() + " Hatsa: " + reserva.getFechaFin());
                System.out.println("----------------------------------");
            }
        }


    }

    public void marcarHabitacionesSinReservasComoOcupadas(){
        System.out.println("\nMARCA COMO OCUPADA UNA HABITACIÓN\n\n");


            System.out.println("Selecciona el hotel\n");
            List<Hotel> listHotels = reservaController.listAllHotels();
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
            List<Habitacion> habitacionesList = reservaController.listAllHabitaciones();
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

        System.out.print("\nIngrese la habitación que quiera marcar como ocupada: ");
        int habOcupada = scanner.nextInt();
        scanner.nextLine();

        boolean UpdateOcupada = reservaController.UpdateHabitacionToOcupada(habOcupada);

        if (UpdateOcupada){
            System.out.println("\nLa habitación "+ habOcupada + " se marcó como ocupada\n");
        }else{
            System.out.println("Ocurrió un error al marcar esta habitación como ocupada");
        }




    }

    private void MarcarDESOcupadas() {
        boolean salir = false;

        System.out.println("\n\nDESOCUPAR HABITACIÓN\n");
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

        while (!salir) {
            List<HabitacionReserva> HabitacionesReservadas = reservaController.HabitacionReservadaSegunIdHuesped(responsable.getId());

            System.out.println("---------------------------------------------");
            System.out.println("RESPONSABLE: " + responsable.getName() + "\n");

            for (HabitacionReserva habRes : HabitacionesReservadas) {
                System.out.println("FECHA DE RESERVA: " + habRes.getReserva().getFechaReserva());
                System.out.println("Habitación Asignada: " + habRes.getHabitacion().getId() +
                        "\nHOTEL: " + habRes.getHabitacion().getHotel().getName());
                System.out.println("Tipo de habitación: " + habRes.getHabitacion().getTipoHabit().getNombre());
                System.out.println("Actualmente: " + (habRes.getHabitacion().isOcupado() ? "Ocupada" : "Desocupada"));
                System.out.println("\n-----------------------------------------");
            }

            System.out.print("\nIngrese la habitación que quiera marcar como desocupada: ");
            int habOcupada = scanner.nextInt();
            scanner.nextLine();

            boolean UpdateDESOcupada = reservaController.UpdateHabitacionToDESOcupada(habOcupada);

            if (UpdateDESOcupada) {
                System.out.println("\nLa habitación se marcó como desocupada\n");
            } else {
                System.out.println("Ocurrió un error al marcar esta habitación como desocupada");
            }

            System.out.println("\n0. Volver a gestión de Reservas");
            System.out.println("1. Realizar otra operación");
            String opcion = scanner.nextLine();

            if (opcion.equals("0")) {
                salir = true;
            }
        }
    }

    private void MarcarOcupadasConReserva(){
        boolean salir = false;

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

        while (!salir){

            List<HabitacionReserva> HabitacionesReservadas = new ArrayList<>();
            HabitacionesReservadas= reservaController.HabitacionReservadaSegunIdHuesped(responsable.getId());

            System.out.println("---------------------------------------------");
            System.out.println("RESPONSABLE: " + responsable.getName() + "\n");

            for(HabitacionReserva habRes : HabitacionesReservadas){

                System.out.println("FECHA DE RESERVA: " + habRes.getReserva().getFechaReserva());
                System.out.println("Habitacion Asignada: " + habRes.getHabitacion().getId() +"\n"+  "HOTEL: " + habRes.getHabitacion().getHotel().getName() + "\n");
                System.out.println("Tipo de habitación: " + habRes.getHabitacion().getTipoHabit().getNombre());
                System.out.println("Actualmente: " + (habRes.getHabitacion().isOcupado() ? "Ocupada" : "Desocupada"));
                System.out.println("\n-----------------------------------------");
            }

            System.out.print("\nIngrese la habitación que quiera marcar como ocupada: ");
            int habOcupada = scanner.nextInt();
            scanner.nextLine();

            boolean UpdateOcupada = reservaController.UpdateHabitacionToOcupada(habOcupada);

            if (UpdateOcupada){
                System.out.println("\nLa habitación se marcó como ocupada\n");
            }else{
                System.out.println("Ocurrió un error al marcar esta habitación como ocupada");
            }

            System.out.println("\n0. Volver a gestión de Reservas");
            System.out.println("1. Realizar otra operación");
            String opcion = scanner.nextLine();

            if (opcion.equals("0")){
                salir = true;
            }

        }






    }

    private void addReserva() {
        Ciudad ciudadObject = null;
        System.out.println("TIEMPO DE ESTADÍA\n");

        Date fechaInicio = null;
        Date fechaFin = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);

        String inicio;
        String fin;

        while (true) {
            try {
                System.out.print("RESERVAR DESDE (YYYY-MM-DD): ");
                inicio = scanner.nextLine();
                fechaInicio = formatter.parse(inicio);

                if (fechaInicio.before(new Date())) {
                    System.out.println("La fecha de inicio no puede ser en el pasado. Intente de nuevo.");
                    continue;
                }

                System.out.print("RESERVAR HASTA (YYYY-MM-DD): ");
                fin = scanner.nextLine();
                fechaFin = formatter.parse(fin);

                if (!fechaInicio.before(fechaFin)) {
                    System.out.println("La fecha de inicio debe ser menor a la fecha de fin. Intente de nuevo.");
                    continue;
                }
                break; // Fechas válidas, salir del bucle
            } catch (ParseException e) {
                System.out.println("Formato de fecha inválido. Use 'YYYY-MM-DD'.");
            }
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


        System.out.println("\n\nDATOS DE LA RESERVA\n");
        System.out.println("Responsable: " + responsable.getName() + " " + responsable.getAmaterno());
        System.out.println("Para " + cantPersonas + " personas");
        System.out.println(cantidadHabitaciones + " habitaciones de " + "tipo " + tipoHabitacion.getNombre());
        System.out.println("\nESTADÍA POR " + diasRestantes + " DÍAS");
        System.out.println("Desde " + inicio + " Hasta " + fin);
        System.out.println("PRECIO TOTAL: U$D " + (diasRestantes * tarifa.getPrecio()));


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

    private void ListAllHabitReservas() {
        Huesped responsable = null;
        System.out.println("\nRESERVAS HECHAS POR CLIENTES: \n\n");

        while (responsable == null) {
            System.out.print("Ingrese Número de Documento de el responsable: ");
            int numDoc = scanner.nextInt();
            scanner.nextLine();
            List<Huesped> listaHuesp = reservaController.listHuesp();
            responsable = listaHuesp.stream().filter(h -> h.getNumDoc() == numDoc).findFirst().orElse(null);
            if (responsable == null) {
                System.out.println("NO EXISTE un usuario registrado con ese número de documento.");
                return;
            }
        }

        System.out.println("---------------------------------------------");
        System.out.println("RESPONSABLE: " + responsable.getName() + " "+ responsable.getApaterno() +"\n");

        List<HabitacionReserva> reservas = reservaController.listAllHabitReserva(responsable.getNumDoc());
        for (HabitacionReserva reserva : reservas) {
            System.out.println("Reserva ID: " + reserva.getReserva().getId() + "\nCreada el " + reserva.getReserva().getFechaReserva());
            System.out.println("Habitación ID: " + reserva.getHabitacion().getId() + "\nDe tipo: " + reserva.getHabitacion().getTipoHabit().getNombre());
            System.out.println("Para " + reserva.getReserva().getCantPersonas() + " personas");
            System.out.println("Hotel: " + reserva.getHabitacion().getHotel().getName());
            System.out.println("Desde: " + reserva.getFechaInicio() + " Hatsa: " + reserva.getFechaFin());
            System.out.println("----------------------------------");
        }


    }

    private void updateHabitacionReserva() {

        Huesped responsable = null;
        System.out.println("\nRESERVAS HECHAS POR CLIENTES: \n\n");

        while (responsable == null) {
            System.out.print("Ingrese Número de Documento de el responsable: ");
            int numDoc = scanner.nextInt();
            scanner.nextLine();
            List<Huesped> listaHuesp = reservaController.listHuesp();
            responsable = listaHuesp.stream().filter(h -> h.getNumDoc() == numDoc).findFirst().orElse(null);
            if (responsable == null) {
                System.out.println("NO EXISTE un usuario registrado con ese número de documento.");
                return;
            }
        }

        System.out.println("---------------------------------------------");
        System.out.println("RESPONSABLE: " + responsable.getName() + " "+ responsable.getApaterno() +"\n");

        List<HabitacionReserva> reservas = reservaController.listAllHabitReserva(responsable.getNumDoc());
        for (HabitacionReserva reserva : reservas) {
            System.out.println("Reserva ID: " + reserva.getReserva().getId() + "\nCreada el " + reserva.getReserva().getFechaReserva());
            System.out.println("Habitación ID: " + reserva.getHabitacion().getId() + "\nDe tipo: " + reserva.getHabitacion().getTipoHabit().getNombre());
            System.out.println("Para " + reserva.getReserva().getCantPersonas() + " personas");
            System.out.println("Hotel: " + reserva.getHabitacion().getHotel().getName());
            System.out.println("Desde: " + reserva.getFechaInicio() + " Hatsa: " + reserva.getFechaFin());
            System.out.println("----------------------------------");
        }


        System.out.print("Ingrese el ID de la reserva a modificar: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nueva fecha de inicio (YYYY-MM-DD): ");
        String inicio = scanner.nextLine();
        System.out.print("Nueva fecha de fin (YYYY-MM-DD): ");
        String fin = scanner.nextLine();
        System.out.print("Nueva observación: ");
        String observacion = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fechaInicio = formatter.parse(inicio);
            Date fechaFin = formatter.parse(fin);

            if (fechaInicio.after(fechaFin)) {
                System.out.println("La fecha de inicio no puede ser mayor que la fecha de fin.");
                return;
            }

            boolean actualizado = reservaController.updateHabitacionReserva(idReserva, inicio, fin, observacion);

            if (actualizado) {
                System.out.println("\nReserva actualizada correctamente.");
            } else {
                System.out.println("Error al actualizar la reserva.");
            }
        } catch (ParseException e) {
            System.out.println("Error al interpretar las fechas. Asegúrate de usar el formato 'YYYY-MM-DD'.");
        }

    }

    private void deleteReserva() {

        Huesped responsable = null;
        System.out.println("\nCANCELAR RESERVAS\n\n");

        while (responsable == null) {
            System.out.print("Ingrese Número de Documento de el responsable: ");
            int numDoc = scanner.nextInt();
            scanner.nextLine();
            List<Huesped> listaHuesp = reservaController.listHuesp();
            responsable = listaHuesp.stream().filter(h -> h.getNumDoc() == numDoc).findFirst().orElse(null);
            if (responsable == null) {
                System.out.println("NO EXISTE un usuario registrado con ese número de documento.");
                return;
            }
        }

        System.out.println("---------------------------------------------");
        System.out.println("RESPONSABLE: " + responsable.getName() + " "+ responsable.getApaterno() +"\n");

        List<Reserva> reservas = reservaController.listAllReservas(responsable.getNumDoc());
        for (Reserva reserva : reservas) {
            System.out.println("Reserva ID: " + reserva.getId());
            System.out.println("Cantidad de Personas: " + reserva.getCantPersonas());
            System.out.println("Fecha: " + reserva.getFechaReserva());
            System.out.println("Responsable: " + reserva.getHuesped().getName());
            System.out.println("----------------------------------");
        }

        System.out.print("Ingresa el ID de la reserva a eliminar: ");
        int idReserva = scanner.nextInt();
        scanner.nextLine();

        if (reservaController.deleteReserva(idReserva)) {
            System.out.println("Reserva cancelada correctamente.");
        } else {
            System.out.println("Error al cancelar la reserva.");
        }
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

