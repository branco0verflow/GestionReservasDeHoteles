package org.example;


import org.example.view.HabitacionView;
import org.example.view.HotelView;
import org.example.view.HuespedView;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HuespedView huespView;
        HotelView hotelView;
        HabitacionView habitView;





        boolean salir = false;
        int opcion = -1;
        Scanner scanner = new Scanner(System.in);
        while (!salir) {
            limpiarConsola();
            System.out.println("Menú Principal:\n");
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
                    limpiarConsola();
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
                    //listarDatos();
                    break;
                case 5:
                    //listarDatos();
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





    private static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}