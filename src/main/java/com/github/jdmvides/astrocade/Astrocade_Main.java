/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.github.jdmvides.astrocade;

import java.util.Scanner;

public class Astrocade_Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("    BIENVENIDO AL SISTEMA DE RECREACION ASTROCADE    ");
        System.out.println("==================================================");

        // 1. INSTANCIACIÓN DE LOS 3 USUARIOS (Requerimiento del corte)
        System.out.println("\n--- REGISTRO DE USUARIOS EN EL SISTEMA ---");
        
        System.out.print("Ingresa el nombre del Usuario 1 (Titular): ");
        String nombre1 = scanner.nextLine();
        System.out.print("Cedula: ");
        String cedula1 = scanner.nextLine();

        System.out.print("\nIngresa el nombre del Usuario 2: ");
        String nombre2 = scanner.nextLine();
        System.out.print("Cedula: ");
        String cedula2 = scanner.nextLine();

        System.out.print("\nIngresa el nombre del Usuario 3: ");
        String nombre3 = scanner.nextLine();
        System.out.print("Cedula: ");
        String cedula3 = scanner.nextLine();

        // 2. EXPEDICIÓN AUTOMÁTICA DE LA TARJETA
        System.out.println("\n--- EXPEDICION DE LA TARJETA ASTROCADE ---");
        System.out.print("¿Con cuanto saldo deseas expedir la tarjeta inicial para " + nombre1 + "? $: ");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine();

        // El sistema asigna el ID seguro automáticamente (UUID)
        Tarjeta tarjetaCompartida = new Tarjeta(saldoInicial);

        // Se crean los 3 objetos independientes en memoria
        Usuario u1 = new Usuario(nombre1, cedula1, tarjetaCompartida); // Nace con la tarjeta
        Usuario u2 = new Usuario(nombre2, cedula2);                   // Nace sin tarjeta (null)
        Usuario u3 = new Usuario(nombre3, cedula3);                   // Nace sin tarjeta (null)
        
        MaquinaArcade maquina1 = new MaquinaArcade("Street Fighter",3500.0);
        MaquinaArcade maquina2 = new MaquinaArcade("Mario Kart",4000.0);
        
        Premios peluche = new Premios("Peluche", 100);
        Premios audifonos = new Premios("Audifonos", 300);
        Premios consola = new Premios("Consola", 1000);

        // Control del portador que tiene la tarjeta en la mano en ese instante
        Usuario portadorActivo = u1;

        System.out.println("\n Usuarios registrados exitosamente.");
        System.out.println(" Tarjeta expedida con ID: " + tarjetaCompartida.getNumeroTarjeta());

        // 3. MENÚ INTERACTIVO PRINCIPAL
        int opcion = 0;
        do {
            System.out.println("\n==================================================");
            System.out.println("                 MENU PRINCIPAL                   ");
            System.out.println("==================================================");
            System.out.println("1. Ver estado de todos los usuarios y la tarjeta");
            System.out.println("2. Recargar saldo a la tarjeta");
            System.out.println("3. Prestar / Transferir tarjeta");
            System.out.println("4. Simular partida en maquina (Gastar saldo y ganar tickets)");
            System.out.println("5. Canjear premio");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opcion (1-6): ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println(" Opcion invalida. Debe ingresar un número.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    // Estado actual de las 3 instancias de Usuario
                    u1.mostrarInfo();
                    u2.mostrarInfo();
                    u3.mostrarInfo();
                    break;

                case 2:
                    // Recarga de dinero
                    System.out.println("\n--- RECARGA DE SALDO ---");
                    if (portadorActivo.getTarjeta() != null) {
                        System.out.println("La tarjeta la tiene actualmente: " + portadorActivo.getNombre());
                        System.out.print("Monto a recargar $: ");
                        double monto = scanner.nextDouble();
                        scanner.nextLine();

                        portadorActivo.getTarjeta().recargar(monto);
                    } else {
                        System.out.println(" Nadie tiene la tarjeta en este momento.");
                    }
                    break;

                case 3:
                    // LÓGICA DE PRESTAR LA TARJETA ENTRE LOS 3 USUARIOS
                    System.out.println("\n--- PRESTAR / PASAR TARJETA ---");
                    System.out.println("Portador actual: " + portadorActivo.getNombre());
                    System.out.println("¿A quien deseas entregarle la tarjeta?");
                    System.out.println("1. " + u1.getNombre());
                    System.out.println("2. " + u2.getNombre());
                    System.out.println("3. " + u3.getNombre());
                    System.out.print("Selecciona el destinatario (1-3): ");

                    int seleccion = scanner.nextInt();
                    scanner.nextLine();

                    Usuario destino = null;
                    if (seleccion == 1) destino = u1;
                    else if (seleccion == 2) destino = u2;
                    else if (seleccion == 3) destino = u3;

                    if (destino == null) {
                        System.out.println(" Seleccion de usuario no valida.");
                    } else if (destino == portadorActivo) {
                        System.out.println(destino.getNombre() + " ya tiene la tarjeta en su poder.");
                    } else {
                        // Pasamos la tarjeta al nuevo objeto y actualizamos el portador
                        portadorActivo.prestarTarjetaA(destino);
                        portadorActivo = destino;
                    }
                    break;

                case 4:
                    // Partida en máquina
                    System.out.println("SELECCIONAR MAQUINA");

                    System.out.println("1. " + maquina1.getNombre()
                    + " - $" + maquina1.getPrecioPartida());

                    System.out.println("2. " + maquina2.getNombre()
                    + " - $" + maquina2.getPrecioPartida());

                    System.out.print("Selecciona una maquina: ");

                    int maquinaSeleccionada = scanner.nextInt();
                    scanner.nextLine();

                    if (portadorActivo.getTarjeta() == null) {
                        System.out.println(portadorActivo.getNombre() +" no tiene tarjeta para jugar.");
                    } else if (maquinaSeleccionada == 1) {
                        maquina1.jugar(portadorActivo.getTarjeta());
                    } else if (maquinaSeleccionada == 2) {
                        maquina2.jugar(portadorActivo.getTarjeta());
                    } else {
                        System.out.println("Máquina no válida.");
                    }
                    break;
                
                case 5:

                System.out.println("\n====================================");
                System.out.println("          CANJEAR PREMIO");
                System.out.println("====================================");

                if (portadorActivo.getTarjeta() == null) {

                    System.out.println(portadorActivo.getNombre() +" no tiene una tarjeta.");
                    break;
                }
                System.out.println("Portador actual: " +portadorActivo.getNombre());
                System.out.println("Tickets disponibles: " +portadorActivo.getTarjeta().getTickets());
                System.out.println("--- PREMIOS DISPONIBLES ---");
                System.out.println("1. " + peluche.getNombre()+ " - " + peluche.getCostoTickets() + " tickets");
                System.out.println("2. " + audifonos.getNombre()+ " - " + audifonos.getCostoTickets() + " tickets");
                System.out.println("3. " + consola.getNombre()+ " - " + consola.getCostoTickets() + " tickets");
                System.out.println("4. Cancelar");
                System.out.print("Selecciona un premio: ");
                int premioSeleccionado = scanner.nextInt();
                scanner.nextLine();
                switch (premioSeleccionado) {
                    case 1:
                        peluche.reclamarPremio(portadorActivo.getTarjeta());
                        break;
                    case 2:
                        audifonos.reclamarPremio(portadorActivo.getTarjeta());
                        break;
                    case 3:
                        consola.reclamarPremio(portadorActivo.getTarjeta());
                        break;
                    case 4:
                        System.out.println("Canje cancelado.");
                        break;
                    default:
                        System.out.println("Premio no válido.");
                }
                break;

                case 6:
                    System.out.println("¡Gracias por usar el sistema AstroCade!");
                    break;

                default:
                    System.out.println(" Opción fuera de rango.");
            }

        } while (opcion != 6);

        scanner.close();
    }
}
