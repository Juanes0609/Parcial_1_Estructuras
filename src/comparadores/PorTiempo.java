package comparadores;

import modelo.Paquete;

import java.util.Comparator;

public class PorTiempo implements Comparator<Paquete> {
    @Override
    public int compare(Paquete p1, Paquete p2) {
        return Integer.compare(p1.getTiempoEstimado(), p2.getTiempoEstimado());
    }
}
