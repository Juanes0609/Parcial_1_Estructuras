package algoritmos;

import modelo.Paquete;

import java.util.List;

public class BusquedaBinaria {

    // Precondición: la lista debe estar ordenada por código
    public static int buscar(List<Paquete> ordenada, String codigo) {
        return buscar(ordenada, codigo, 0, ordenada.size() - 1);
    }

    public static int buscar(List<Paquete> ordenada, String codigo, int ini, int fin) {
        if (ini > fin) {
            return -1; // caso base: no está
        }
        int mid = ini + (fin - ini) / 2;
        int comparacion = codigo.compareTo(ordenada.get(mid).getCodigo());

        if (comparacion == 0) {
            return mid; // caso base: encontrado
        }
        if (comparacion < 0) {
            return buscar(ordenada, codigo, ini, mid - 1);
        }
        return buscar(ordenada, codigo, mid + 1, fin);
    }
}