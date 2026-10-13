package comparadores;

import modelo.Paquete;

import java.util.Comparator;

public class PorPeso implements Comparator<Paquete> {
    @Override
    public int compare(Paquete p1, Paquete p2) {
        // De mayor a menor
        return Double.compare(p2.getPeso(), p1.getPeso());
    }
}
