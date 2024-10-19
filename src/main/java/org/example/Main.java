package org.example;


import org.example.view.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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
            System.out.println("\n\n\n    MENÚ PRINCIPAL:\n");
            System.out.println("1. Gestión de Huespedes");
            System.out.println("2. Gestión de Hoteles");
            System.out.println("3. Gestión de Habitaciones");
            System.out.println("4. Gestión de Tarifas");
            System.out.println("5. Gestión de Reservas");
            System.out.println("0. Salir");
            System.out.println(" ");
            System.out.print("Ingrese la opción deseada: ");
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
                    break;
                default:
                    System.out.println("Opción no válida. Presione Enter para continuar.");
                    scanner.nextLine();
                    break;
            }
        }
        scanner.close();
    }


}