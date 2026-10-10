import datos.DatosIniciales;
import modelo.Paquete;
import servicio.CentroDistribucion;

import java.util.Scanner;
import java.util.Set;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CentroDistribucion centro = new CentroDistribucion();
        DatosIniciales.cargarDatos(centro);

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== QUINDÍO EXPRESS - GESTIÓN DE OPERACIONES ===");
            System.out.println("1. Consultar paquete por código");
            System.out.println("2. Ver municipios destino");
            System.out.println("3. Ver municipios ordenados alfabéticamente");
            System.out.println("4. Ver paquetes destinados a un municipio");
            System.out.println("5. Atender próximo paquete por Orden de Llegada");
            System.out.println("6. Atender próximo paquete por Prioridad");
            System.out.println("7. Asignar paquete a repartidor");
            System.out.println("8. Ver todos los paquetes registrados");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el código del paquete (ej. PQ101): ");
                    String codigo = scanner.nextLine().trim();
                    Paquete p = centro.consultarPaquete(codigo);
                    if (p != null) {
                        System.out.println("Paquete encontrado: " + p);
                    } else {
                        System.out.println("Error: Paquete no encontrado.");
                    }
                    break;
                case 2:
                    Set<String> municipios = centro.obtMunicipiosSinDuplicados();
                    System.out.println("Municipios (Sin orden garantizado): " + municipios);
                    break;
                case 3:
                    Set<String> municipiosOrd = centro.obtMunicipiosOrdenados();
                    System.out.println("Municipios (Orden alfabético): " + municipiosOrd);
                    break;
                case 4:
                    System.out.print("Ingrese el municipio (ej. Armenia): ");
                    String municipio = scanner.nextLine().trim();
                    List<Paquete> paquetes = centro.obtPaquetesPorMunicipio(municipio);
                    if (paquetes.isEmpty()) {
                        System.out.println("No hay paquetes registrados para " + municipio);
                    } else {
                        System.out.println("Paquetes para " + municipio + ":");
                        for (Paquete pq : paquetes) {
                            System.out.println(" - " + pq);
                        }
                    }
                    break;
                case 5:
                    Paquete proximoLlegada = centro.obtSiguientePorOrdenLlegada();
                    if (proximoLlegada != null) {
                        System.out.println("Paquete a despachar (Orden Llegada): " + proximoLlegada.getCodigo());
                    } else {
                        System.out.println("No hay paquetes pendientes en la cola de llegada.");
                    }
                    break;
                case 6:
                    Paquete proximoPrio = centro.obtSiguientePorPrioridad();
                    if (proximoPrio != null) {
                        System.out.println("Paquete a despachar (Prioridad): " + proximoPrio.getCodigo() +
                                " | Prioridad: " + proximoPrio.getPrioridad() +
                                " | Tiempo: " + proximoPrio.getTiempoEstimado());
                    } else {
                        System.out.println("No hay paquetes pendientes en la cola prioritaria.");
                    }
                    break;
                case 7:
                    System.out.print("Ingrese código del paquete a asignar (ej. PQ101): ");
                    String codAsignar = scanner.nextLine().trim();
                    System.out.print("Ingrese ID del repartidor (ej. R001): ");
                    String idRep = scanner.nextLine().trim();
                    try {
                        centro.asignarPaqueteRepartidor(codAsignar, idRep);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                case 8:
                    List<Paquete> listaPaquetes = centro.getPaquetesOrdenRegistro();
                    if (listaPaquetes.isEmpty()) {
                        System.out.println("No hay paquetes registrados en el sistema.");
                    } else {
                        for (Paquete pq : listaPaquetes) {
                            System.out.println(pq);
                        }
                        System.out.println("Total paquetes: " + listaPaquetes.size());
                    }
                    break;
            }
        }
        scanner.close();
    }
}