package org.example;


import org.example.view.HuespedView;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HuespedView huespView;
        boolean salir = false;
        int opcion = -1;
        Scanner scanner = new Scanner(System.in);
        while (!salir) {
            limpiarConsola();
            System.out.println("Menú Principal:");
            System.out.println("1. Ingresar Datos");
            System.out.println("2. Listar Datos");
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
                    listarDatos();
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



    private static void listarDatos() {
        limpiarConsola();
        System.out.println("Listar Datos:");
        new Scanner(System.in).nextLine();
    }

    private static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}