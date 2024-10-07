package org.example.view;

import org.example.controller.HuespedController;
import org.example.model.Huesped;
import org.example.model.Pais;
import org.example.model.TipoDoc;

import java.util.List;
import java.util.Scanner;

public class HuespedView {
    private HuespedController huespedController;
    private Scanner scanner;

    public HuespedView(Scanner scanner) {
        this.huespedController = new HuespedController();
        this.scanner = scanner;
    }

    public void manageHuesp(){
        int option = -1;
        do {
            System.out.println("\n----- Gestion de Huespedes -----");
            System.out.println("1. Ingresar un nuevo huesped");
            System.out.println("2. Listar huespedes");
            System.out.println("0. Volver al menu principal");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    insertHuesped();
                    break;
                case 2:
                    listHuespedes();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (option != 0);
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

        System.out.println("Ingrese su número de documento: ");
        int numDoc = scanner.nextInt();

        System.out.println("Ingrese su fecha de nacimiento YYYY-MM--DD: ");
        String fechaNac = scanner.nextLine();


        List<Pais> listPaisess = huespedController.listPaises();
        System.out.println("Los países existentes son:");
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


        Huesped huesped = new Huesped(name, apaterno, amaterno, TipoObject, numDoc, fechaNac, PaisObject);
        boolean userInserted = this.huespedController.insertHuesped(huesped);

        if (userInserted){
            System.out.println("Usuario insertado! ");
        } else {
            System.out.println("Ocurrió un error al insertar el usuario");
        }
    }

    private void listHuespedes(){
        List<Huesped> listHuesp = huespedController.listHuespedes();
        System.out.println("Los Huespedes ingresados son:");
        System.out.println("---------------------------------------------");

        for (Huesped huesped : listHuesp){
            System.out.println("Id:"+ huesped.getId() + "\nNombre: "+ huesped.getName() + "\nApellido Paterno: " + huesped.getApaterno()+ "\nApellido Materno: " + huesped.getAmaterno()+ huesped.getTipoDoc().getName()+ huesped.getNumDoc()+ huesped.getFechaNac() +"\nPais: " + huesped.getPais().getName());
            System.out.println("---------------------------------------------");
        }
    }

}
