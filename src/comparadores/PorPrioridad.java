package comparadores;

import modelo.Paquete;

import java.util.Comparator;

public class PorPrioridad implements Comparator<Paquete> {
    @Override
    public int compare(Paquete p1, Paquete p2) {
        // De mayor a menor
        return Integer.compare(p2.getPrioridad(), p1.getPrioridad());
    }
}
