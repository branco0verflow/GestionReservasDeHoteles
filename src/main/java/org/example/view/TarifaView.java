package org.example.view;


import org.example.controller.TarifaController;
import org.example.model.Tarifa;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class TarifaView {
    private TarifaController tarifaController;
    private Scanner scanner;

    // Códigos ANSI para colores
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";

    public TarifaView(Scanner scanner) {
        this.tarifaController = new TarifaController();
        this.scanner = scanner;
    }

    public void manageTarifa() {
        int option = -1;
        do {
            System.out.println(BLUE + "\n\nGESTIÓN DE TARIFAS\n" + RESET);
            System.out.println(GREEN + "1. Crear nueva tarifa" + RESET);
            System.out.println(GREEN + "2. Listar tarifas" + RESET);
            System.out.println(GREEN + "3. Modificar tarifa" + RESET);
            System.out.println(GREEN + "4. Eliminar tarifa según ID" + RESET);
            System.out.println(YELLOW + "0. Volver al menú principal" + RESET);
            System.out.print(CYAN + "Seleccione una opción: " + RESET);

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addTarifa();
                    break;
                case 2:
                    viewAllTarifas();
                    break;
                case 3:
                    updateTarifa();
                    break;
                case 4:
                    deleteTarifa();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(RED + "Opción no válida. Intente nuevamente." + RESET);
            }
        } while (option != 0);
    }

    public void addTarifa(){
        System.out.print("Ingresa el nombre de la nueva tarifa: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingresa el precio: ");
        double precio = scanner.nextDouble();

        Tarifa tarifa = new Tarifa(nombre, precio);

        if(tarifaController.addTarifa(tarifa)){
            System.out.println("Tarifa creada correctamente");
        }else{
            System.out.println("Ocurrió un error al crear la tarifa");
        }

    }


    public void viewAllTarifas(){

        List<Tarifa> listTarifa = tarifaController.getAllTarifas();

        System.out.println("\n\nTARIFAS EXISTENTES\n");

        for (Tarifa tarifa : listTarifa){
            System.out.println("\n"+tarifa.getId() + "- Nombre: " + tarifa.getNombre() + " Precio: " + tarifa.getPrecio());
            System.out.println("_____________________________________________");
        }

    }


    public void updateTarifa() {

        List<Tarifa> listTarifa = tarifaController.getAllTarifas();

        System.out.println("\n\nTARIFAS EXISTENTES\n");

        for (Tarifa tarifa : listTarifa){
            System.out.println(tarifa.getId() + "- Nombre: " + tarifa.getNombre() + "Precio: " + tarifa.getPrecio());
            System.out.println("_____________________________________________\n");
        }

        System.out.print("Ingresa el ID de la tarifa a modificar: ");
        int idTarifa = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Tarifa tarifaExistente = listTarifa.stream().filter(t -> t.getId() == idTarifa).findFirst().orElse(null);

        if (tarifaExistente == null) {
            System.out.println("Tarifa no encontrada.");
            return;
        }

        System.out.println("\nModificando tarifa: " + tarifaExistente.getNombre()+"\n\n");
        System.out.print("Nuevo nombre (Actual: " + tarifaExistente.getNombre() + "): ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Nuevo precio (Actual: " + tarifaExistente.getPrecio() + "): ");
        double nuevoPrecio = scanner.nextDouble();

        Tarifa tarifaModificada = new Tarifa(idTarifa, nuevoNombre, nuevoPrecio);

        if (tarifaController.updateTarifa(tarifaModificada)) {
            System.out.println("Tarifa actualizada correctamente.");
        } else {
            System.out.println("Ocurrió un error al actualizar la tarifa.");
        }
    }

    public void deleteTarifa(){
        System.out.print("Ingrese el ID de la tarifa que quiere Eliminar: ");
        int idEliminar = scanner.nextInt();

        if(tarifaController.deleteTarifa(idEliminar)){
            System.out.println("\n\nELIMINAR TARIFA SEGÚN ID\n");
            System.out.println("Tarifa eliminada correctamente");
        }else{
            System.out.println("Ocurrió un error al eliminar la tarifa");
        }

    }



}
