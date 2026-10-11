import algoritmos.BusquedaBinaria;
import algoritmos.DivideYVenceras;
import algoritmos.Recursivos;
import datos.DatosIniciales;
import demo.Demostracion;
import estructuras.ListaEnlazada;
import modelo.Paquete;
import servicio.CentroDistribucion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
            System.out.println("9. Registrar entrega de un paquete");
            System.out.println("10. Ver historial de entregas");
            System.out.println("11. Peso total de un municipio (recursivo)");
            System.out.println("12. Contar paquetes con prioridad mínima (recursivo)");
            System.out.println("13. Buscar paquete por código (búsqueda binaria)");
            System.out.println("14. Paquete de mayor peso (divide y vencerás)");
            System.out.println("15. Demostración automática con los ejemplos del enunciado");
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
                case 9:
                    System.out.print("Ingrese el código del paquete entregado: ");
                    String codEntrega = scanner.nextLine().trim();
                    try {
                        centro.registrarEntrega(codEntrega);
                        System.out.println("Entrega registrada: " + codEntrega);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    ListaEnlazada<Paquete> historial = centro.getHistorialEntregas();
                    if (historial.estaVacia()) {
                        System.out.println("El historial de entregas está vacío.");
                    } else {
                        int orden = 1;
                        for (Paquete entregado : historial) {
                            System.out.println(orden++ + ". " + entregado);
                        }
                        System.out.println("Total entregados: " + historial.tamano());
                    }
                    break;
                case 11:
                    System.out.print("Ingrese el municipio (ej. Armenia): ");
                    String munPeso = scanner.nextLine().trim();
                    double total = Recursivos.pesoTotalPorMunicipio(centro.getPaquetesOrdenRegistro(), munPeso);
                    System.out.println("Peso total pendiente hacia " + munPeso + ": " + total + " kg");
                    break;
                case 12:
                    System.out.print("Ingrese la prioridad mínima (1-5): ");
                    try {
                        int minima = Integer.parseInt(scanner.nextLine().trim());
                        int cantidad = Recursivos.contarPrioridadMinima(centro.getPaquetesOrdenRegistro(), minima);
                        System.out.println("Paquetes con prioridad >= " + minima + ": " + cantidad);
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor ingrese un número válido.");
                    }
                    break;
                case 13:
                    System.out.print("Ingrese el código a buscar (ej. PQ731): ");
                    String codBuscado = scanner.nextLine().trim();
                    // La búsqueda binaria exige lista ordenada: se ordena una copia por código
                    List<Paquete> porCodigo = new ArrayList<>(centro.getPaquetesOrdenRegistro());
                    Collections.sort(porCodigo);
                    int pos = BusquedaBinaria.buscar(porCodigo, codBuscado);
                    if (pos >= 0) {
                        System.out.println("Encontrado en la posición " + pos + ": " + porCodigo.get(pos));
                    } else {
                        System.out.println("El código " + codBuscado + " no está entre los pendientes.");
                    }
                    break;
                case 14:
                    List<Paquete> pendientes = centro.getPaquetesOrdenRegistro();
                    if (pendientes.isEmpty()) {
                        System.out.println("No hay paquetes pendientes.");
                    } else {
                        System.out.println("Paquete más pesado: " + DivideYVenceras.paqueteMayorPeso(pendientes));
                    }
                    break;
                case 15:
                    Demostracion.ejecutar();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        scanner.close();
    }
}