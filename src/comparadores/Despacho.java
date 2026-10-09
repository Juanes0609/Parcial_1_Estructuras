package comparadores;
import modelo.Paquete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Despacho implements Comparator<Paquete> {
    @Override
    public int compare(Paquete p1, Paquete p2) {
        int porPrioridad = Integer.compare(p2.getPrioridad(), p1.getPrioridad());
        if (porPrioridad != 0) return porPrioridad;

        int porTiempo = Integer.compare(p1.getTiempoEstimado(), p2.getTiempoEstimado());
        if (porTiempo != 0) return porTiempo;

        return p1.getCodigo().compareTo(p2.getCodigo());
    }
}
