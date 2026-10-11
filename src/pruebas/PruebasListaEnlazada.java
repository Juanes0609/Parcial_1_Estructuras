package pruebas;

import estructuras.ListaEnlazada;

public class PruebasListaEnlazada {

    public static void main(String[] args) {
        eliminar("1. Lista vacía", new ListaEnlazada<>(), "A");
        eliminar("2. Un único elemento", lista("A"), "A");

        ListaEnlazada<String> l = lista("A", "B", "C", "D");
        eliminar("3. Primero", l, "A");
        eliminar("4. Intermedio", l, "C");
        eliminar("5. Último", l, "D");
        eliminar("6. Inexistente", l, "Z");

        l.agregar("E");
        System.out.println("Se agrega E -> " + l + " tamaño " + l.tamano());
    }

    private static ListaEnlazada<String> lista(String... elementos) {
        ListaEnlazada<String> l = new ListaEnlazada<>();
        for (String e : elementos) {
            l.agregar(e);
        }
        return l;
    }

    private static void eliminar(String caso, ListaEnlazada<String> l, String elemento) {
        String antes = l + " (tamaño " + l.tamano() + ")";
        boolean resultado = l.eliminar(elemento);
        System.out.println(caso + " | " + antes + " | eliminar(" + elemento + ") | " + resultado
                + " | " + l + " | " + l.tamano());
    }
}