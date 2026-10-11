package algoritmos;

import modelo.Paquete;

import java.util.List;

public class DivideYVenceras {

    public static double mayorPeso(double[] pesos) {
        if (pesos == null || pesos.length == 0) {
            throw new IllegalArgumentException("Se necesita al menos un peso");
        }
        return mayorPeso(pesos, 0, pesos.length - 1);
    }

    public static double mayorPeso(double[] pesos, int ini, int fin) {
        if (ini == fin) {
            return pesos[ini]; // caso base
        }
        int mid = (ini + fin) / 2; // división
        double izquierdo = mayorPeso(pesos, ini, mid);
        double derecho = mayorPeso(pesos, mid + 1, fin);
        return Math.max(izquierdo, derecho); // combinación
    }

    public static Paquete paqueteMayorPeso(List<Paquete> paquetes) {
        if (paquetes == null || paquetes.isEmpty()) {
            throw new IllegalArgumentException("Se necesita al menos un paquete");
        }
        return paqueteMayorPeso(paquetes, 0, paquetes.size() - 1);
    }

    public static Paquete paqueteMayorPeso(List<Paquete> paquetes, int ini, int fin) {
        if (ini == fin) {
            return paquetes.get(ini); // caso base
        }
        int mid = (ini + fin) / 2;
        Paquete izquierdo = paqueteMayorPeso(paquetes, ini, mid);
        Paquete derecho = paqueteMayorPeso(paquetes, mid + 1, fin);
        return derecho.getPeso() > izquierdo.getPeso() ? derecho : izquierdo;
    }
}