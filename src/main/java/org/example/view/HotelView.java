package org.example.view;

import org.example.controller.HabitacionController;
import org.example.controller.HotelController;
import org.example.model.Ciudad;
import org.example.model.Habitacion;
import org.example.model.Hotel;
import org.example.model.Pais;

import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

public class HotelView {

    private HotelController hotelController;
    private HabitacionController habitController;
    private Scanner scanner;


    public HotelView(Scanner scanner) { this.hotelController = new HotelController(); this.scanner = scanner; this.habitController = new HabitacionController();}

    public void manageHotel() {
        int option = -1;
        do {
            System.out.println("\n\n\n----- Gestión de Hoteles -----");
            System.out.println("1. Crear nuevo Hotel");
            System.out.println("2. Listar hoteles");
            System.out.println("3. Modificar un hoteles");
            System.out.println("4. Eliminar un hotel\n");

            System.out.println("5. Ver habitaciones ocupadas");
            System.out.println("6. Filtrar hoteles");
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
                case 5:
                    verHabitacionesOcupadas();
                    break;
                case 6:
                    filtrarHoteles();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
    }


    public void filtrarHoteles(){
            Scanner scanner = new Scanner(System.in);

            boolean continuar = true;
            while (continuar) {
                System.out.println("\n\nENCUENTRA HOTELES\n");
                System.out.println("1. Filtrar por nombre");
                System.out.println("2. Filtrar por ciudad");
                System.out.println("3. Filtrar por cantidad de estrellas");
                System.out.println("4. Aplicar filtros y mostrar resultados");
                System.out.println("0. Salir");
                System.out.print("\nSeleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("\nIngrese el nombre del hotel: ");
                        String name = scanner.nextLine();

                        List<Hotel> hotelesList = hotelController.getAllHotels();
                        List<Hotel> hoteles = hotelesList.stream().filter(h -> h.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());

                        if (hoteles.isEmpty()) {
                            System.out.println("\nNo se encontraron hoteles con ese nombre.\n");
                        } else {
                            System.out.println("\nHoteles encontrados:\n");
                            hoteles.forEach(h -> System.out.println(h.getName()));
                        }
                        break;

                    case 2:
                        System.out.print("\nIngrese la ciudad del hotel: ");
                        String ciudad = scanner.nextLine();

                        List<Hotel> hotelesLis = hotelController.getAllHotels();
                        List<Hotel> hotels = hotelesLis.stream()
                                .filter(h -> h.getCiudad().getName().equalsIgnoreCase(ciudad))
                                .collect(Collectors.toList());

                        if (hotels.isEmpty()) {
                            System.out.println("No se encontraron hoteles en la ciudad indicada.");
                        } else {
                            System.out.println("Hoteles encontrados:");
                            hotels.forEach(h -> System.out.println(h.getName() + " - " + h.getCiudad().getName()));
                        }

                        break;

                    case 3:
                        System.out.print("\nIngrese la cantidad de estrellas: ");
                        int estrellas = scanner.nextInt();
                        scanner.nextLine();

                        List<Hotel> hotelesLista = hotelController.getAllHotels();
                        List<Hotel> hotele = hotelesLista.stream().filter(h -> h.getCantEstrella() == estrellas).collect(Collectors.toList());

                        if (hotele.isEmpty()) {
                            System.out.println("No se encontraron hoteles con esa cantidad de estrellas.");
                        } else {
                            System.out.println("\n\nHoteles encontrados:\n");
                            hotele.forEach(h -> System.out.println(h.getName() + " - " + h.getCantEstrella() + " estrellas"));
                        }

                        break;

                    case 4:
                        System.out.println("\n\nBUSCAR HOTELES SEGÚN CIUDAD - NOMBRE - CANTIDAD DE ESTRELLAS\n");
                        System.out.print("Ingrese el nombre del hotel: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la ciudad del hotel: ");
                        ciudad = scanner.nextLine();
                        System.out.print("Ingrese la cantidad de estrellas: ");
                        estrellas = scanner.nextInt();
                        List<Hotel> resultados = buscarHoteles(nombre, ciudad, estrellas);
                        if (resultados.isEmpty()) {
                            System.out.println("No se encontraron hoteles con esos criterios.");
                        } else {
                            System.out.println("\nHoteles encontrados:\n");
                            resultados.forEach(h -> System.out.println(
                                    h.getName() + " - " + h.getCiudad().getName() + " - " + h.getCantEstrella() + " estrellas"
                            ));
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo del menú...");
                        continuar = false;
                        break;

                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            }
        }
    public List<Hotel> buscarHoteles(String nombre, String ciudad, Integer cantEstrella) {
        List<Hotel> hotelesList = hotelController.getAllHotels();
        return hotelesList.stream()
                .filter(h -> (nombre == null || h.getName().toLowerCase().contains(nombre.toLowerCase())))
                .filter(h -> (ciudad == null || h.getCiudad().getName().toLowerCase().equals(ciudad.toLowerCase())))
                .filter(h -> (cantEstrella == null || h.getCantEstrella() == cantEstrella))
                .toList();
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

    public void verHabitacionesOcupadas(){
        List<Hotel> hotels = hotelController.getAllHotels();
        Hotel hotelSeleccionado = null;

        System.out.println("\n\nHABITACIONES OCUPADAS\n");
        if (hotels.isEmpty()) {
            System.out.println("\nNo hay hoteles disponibles.");
            return;
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

        boolean salir = false;
      while (!salir){
          System.out.print("\n\nSeleccione un hotel: ");
          int idHoel = scanner.nextInt();
          scanner.nextLine();

          hotelSeleccionado = hotels.stream().filter(h -> h.getId() == idHoel).findFirst().orElse(null);
          if(hotelSeleccionado == null){
              System.out.println("\nHotel no encontrado\n");
              return;
          }else{
              salir = true;
          }
      }
        List<Habitacion> habitacionesList = habitController.getAllHabit();

        Hotel finalHotelSeleccionado = hotelSeleccionado;
        List<Habitacion> habitacionesOcupadas = habitacionesList.stream()
                .filter(h -> h.getHotel().getId() == finalHotelSeleccionado.getId() && h.isOcupado())
                .toList();

        if (habitacionesOcupadas.isEmpty()) {
            System.out.println("No hay habitaciones ocupadas en este hotel.");
        } else {
            habitacionesOcupadas.forEach(habitacion -> {
                System.out.println("\n---------------------------------------------");
                System.out.println("NÚMERO DE HABITACIÓN: " + habitacion.getId());
                System.out.println("TIPO: " + habitacion.getTipoHabit().getNombre());
                System.out.println("Cantidad de camas: " + habitacion.getCantCama());
                System.out.println("Cama Doble: " + (habitacion.isCamaDoble() ? "Sí" : "No"));
                System.out.println("Estado actual: Ocupado");
                System.out.println("Tiene aire acondicionado: " + (habitacion.isAireAcon() ? "Sí" : "No"));
                System.out.println("Tiene balcón: " + (habitacion.isBalcon() ? "Sí" : "No"));
                System.out.println("Cuenta con amenities: " + (habitacion.isAmenities() ? "Sí" : "No"));
                System.out.println("Vista: " + habitacion.getVista());
                System.out.println("---------------------------------------------");
            });
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
