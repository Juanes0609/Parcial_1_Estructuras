package algoritmos;

import modelo.Paquete;

import java.util.List;

public class Recursivos {

    public static double pesoTotalPorMunicipio(List<Paquete> paquetes, String municipio) {
        return pesoTotalPorMunicipio(paquetes, municipio, 0);
    }

    public static double pesoTotalPorMunicipio(List<Paquete> paquetes, String municipio, int i) {
        if (i == paquetes.size()) {
            return 0; // caso base
        }
        Paquete actual = paquetes.get(i);
        double aporte = actual.getDestino().equals(municipio) ? actual.getPeso() : 0;
        return aporte + pesoTotalPorMunicipio(paquetes, municipio, i + 1);
    }

    public static int contarPrioridadMinima(List<Paquete> paquetes, int minima) {
        return contarPrioridadMinima(paquetes, minima, 0);
    }

    public static int contarPrioridadMinima(List<Paquete> paquetes, int minima, int i) {
        if (i == paquetes.size()) {
            return 0; // caso base
        }
        int cuenta = paquetes.get(i).getPrioridad() >= minima ? 1 : 0;
        return cuenta + contarPrioridadMinima(paquetes, minima, i + 1);
    }
}