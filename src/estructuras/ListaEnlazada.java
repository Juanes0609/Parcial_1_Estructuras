package estructuras;

import java.util.Objects;

public class ListaEnlazada<T> implements Coleccion<T> {
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamano;

    @Override
    public void agregar(T elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("No se pueden agregar elementos nulos");
        }
        Nodo<T> nuevo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            cola.setSiguiente(nuevo);
        }
        cola = nuevo;
        tamano++;
    }

    @Override
    public boolean eliminar(T elemento) {
        Nodo<T> anterior = null;
        Nodo<T> actual = cabeza;

        while (actual != null && !Objects.equals(actual.getValor(), elemento)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (actual == null) {
            return false; // lista vacía o elemento inexistente
        }
        if (anterior == null) {
            cabeza = actual.getSiguiente(); // era el primero
        } else {
            anterior.setSiguiente(actual.getSiguiente());
        }
        if (actual == cola) {
            cola = anterior; // era el último
        }
        tamano--;
        return true;
    }

    @Override
    public T obtener(int posicion) {
        if (posicion < 0 || posicion >= tamano) {
            throw new IndexOutOfBoundsException("Posición fuera de rango: " + posicion);
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getValor();
    }

    @Override
    public int tamano() {
        return tamano;
    }

    @Override
    public boolean estaVacia() {
        return tamano == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.getValor());
            if (actual.getSiguiente() != null) {
                sb.append(" -> ");
            }
            actual = actual.getSiguiente();
        }
        return sb.append("]").toString();
    }
}