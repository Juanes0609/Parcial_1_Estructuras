package demo;

import algoritmos.BusquedaBinaria;
import algoritmos.DivideYVenceras;
import algoritmos.Recursivos;
import comparadores.Despacho;
import estructuras.ListaEnlazada;
import modelo.Paquete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demostracion {

    public static void ejecutar() {
        System.out.println("\n===== DEMOSTRACIÓN AUTOMÁTICA =====");

        ListaEnlazada<Paquete> historial = new ListaEnlazada<>();
        historial.agregar(new Paquete("PQ105", "Salento", 3.0, 5, 25));
        historial.agregar(new Paquete("PQ942", "Armenia", 2.0, 4, 20));
        historial.agregar(new Paquete("PQ731", "Armenia", 4.0, 5, 45));
        StringBuilder sb = new StringBuilder();
        for (Paquete p : historial) {
            sb.append(p.getCodigo()).append(" ");
        }
        System.out.println("Historial: " + sb + "(esperado: PQ105 PQ942 PQ731)");

        List<Paquete> despacho = new ArrayList<>(Arrays.asList(
                new Paquete("PQ420", "Armenia", 1.0, 3, 30),
                new Paquete("PQ731", "Armenia", 1.0, 5, 45),
                new Paquete("PQ105", "Armenia", 1.0, 5, 25),
                new Paquete("PQ942", "Armenia", 1.0, 4, 20)));
        despacho.sort(new Despacho());
        sb = new StringBuilder();
        for (Paquete p : despacho) {
            sb.append(p.getCodigo()).append(" ");
        }
        System.out.println("Despacho: " + sb + "(esperado: PQ105 PQ731 PQ942 PQ420)");

        List<Paquete> pesos = new ArrayList<>(Arrays.asList(
                new Paquete("PQ101", "Armenia", 4.5, 3, 10),
                new Paquete("PQ203", "Salento", 3.0, 3, 10),
                new Paquete("PQ415", "Armenia", 7.2, 3, 10),
                new Paquete("PQ730", "Armenia", 2.3, 3, 10)));
        System.out.println("Peso Armenia: " + Recursivos.pesoTotalPorMunicipio(pesos, "Armenia")
                + " (esperado: 14.0)");

        int[] prioridades = {5, 2, 4, 1, 5, 3, 4};
        List<Paquete> conPrioridad = new ArrayList<>();
        int n = 1;
        for (int pr : prioridades) {
            conPrioridad.add(new Paquete("P" + n++, "Armenia", 1.0, pr, 10));
        }
        System.out.println("Prioridad >= 4: " + Recursivos.contarPrioridadMinima(conPrioridad, 4)
                + " (esperado: 4)");

        String[] codigos = {"PQ105", "PQ214", "PQ318", "PQ421", "PQ567", "PQ731", "PQ842", "PQ942"};
        List<Paquete> ordenada = new ArrayList<>();
        for (String c : codigos) {
            ordenada.add(new Paquete(c, "Armenia", 1.0, 3, 10));
        }
        System.out.println("Buscar PQ731: " + BusquedaBinaria.buscar(ordenada, "PQ731") + " (esperado: 5)");
        System.out.println("Buscar PQ500: " + BusquedaBinaria.buscar(ordenada, "PQ500") + " (esperado: -1)");

        double[] mayores = {4.5, 12.0, 3.2, 18.7, 7.4, 5.1};
        System.out.println("Mayor peso: " + DivideYVenceras.mayorPeso(mayores) + " (esperado: 18.7)");
    }
}