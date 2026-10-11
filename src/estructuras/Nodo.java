package estructuras;

class Nodo<T> {
    private T valor;
    private Nodo<T> siguiente;

    Nodo(T valor) {
        this.valor = valor;
    }

    T getValor() {
        return valor;
    }

    Nodo<T> getSiguiente() {
        return siguiente;
    }

    void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}