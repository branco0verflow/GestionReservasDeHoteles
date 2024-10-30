package org.example;


import org.example.view.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    // Secuencias ANSI para colores de texto y fondo
    public static final String ANSI_RESET = "\u001B[02m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        HuespedView huespView;
        HotelView hotelView;
        HabitacionView habitView;
        TarifaView tarifaView;
        ReservaView reservaView;

        boolean salir = false;
        int opcion = -1;
        Scanner scanner = new Scanner(System.in);

        while (!salir) {
            System.out.println(ANSI_BLUE + "\n\n\n    MENÚ PRINCIPAL:" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "1. Gestión de Huéspedes" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "2. Gestión de Hoteles" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "3. Gestión de Habitaciones" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. Gestión de Tarifas" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "5. Gestión de Reservas" + ANSI_RESET);
            System.out.println(ANSI_RED + "0. Salir" + ANSI_RESET);
            System.out.print(ANSI_CYAN + "\nIngrese la opción deseada: " + ANSI_RESET);

            try {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        huespView = new HuespedView(scanner);
                        huespView.manageHuesp();
                        break;
                    case 2:
                        hotelView = new HotelView(scanner);
                        hotelView.manageHotel();
                        break;
                    case 3:
                        habitView = new HabitacionView(scanner);
                        habitView.manageHabitacion();
                        break;
                    case 4:
                        tarifaView = new TarifaView(scanner);
                        tarifaView.manageTarifa();
                        break;
                    case 5:
                        reservaView = new ReservaView(scanner);
                        reservaView.manageReserva();
                        break;
                    case 0:
                        salir = true;
                        System.out.println(ANSI_GREEN + "Saliendo del sistema..." + ANSI_RESET);
                        break;
                    default:
                        System.out.println(ANSI_RED + "Opción no válida. Presione Enter para continuar." + ANSI_RESET);
                        scanner.nextLine();
                        break;
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Entrada no válida. Por favor, ingrese un número." + ANSI_RESET);
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
