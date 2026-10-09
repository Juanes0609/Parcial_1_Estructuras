package modelo;

import java.util.Comparator;

public class Paquete implements Comparable<Paquete> {
    private String codigo;
    private String destino;
    private double peso;
    private int prioridad;
    private int tiempoEstimado;

    public static final int PRIORIDAD_POR_DEFECTO = 5;
    public static final int PRIORIDAD_MIN = 1;
    public static final int PRIORIDAD_MAX = 5;

    public Paquete(String codigo, String destino, double peso, int prioridad, int tiempoEstimado) {
        setCodigo(codigo);
        setDestino(destino);
        setPeso(peso);
        setPrioridad(prioridad);
        setTiempoEstimado(tiempoEstimado);
    }

    public Paquete(String codigo, String destino, double peso, int tiempoEstimado){
        this(codigo, destino, peso, PRIORIDAD_POR_DEFECTO, tiempoEstimado);
    }

    @Override
    public int compareTo(Paquete otroPaquete) {
        return this.codigo.compareTo(otroPaquete.getCodigo());
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser nulo ni vacío");
        }
        this.codigo = codigo;
    }

    public void setDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino no puede ser nulo ni vacío");
        }
        this.destino = destino;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero");
        }
        this.peso = peso;
    }

    public void setPrioridad(int prioridad) {
        if (prioridad < PRIORIDAD_MIN || prioridad > PRIORIDAD_MAX) {
            throw new IllegalArgumentException("La prioridad de estar entre " + PRIORIDAD_MIN + " y " + PRIORIDAD_MAX );
        }
        this.prioridad = prioridad;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        if (tiempoEstimado <= 0) {
            throw new IllegalArgumentException("El tiempo estimado debe ser mayor que 0");
        }
        this.tiempoEstimado = tiempoEstimado;
    }

    @Override
    public String toString() {
        return "Paquete: " +
                codigo + " | " +
                destino + " | " +
                peso + " | " +
                prioridad + " | " +
                tiempoEstimado;
    }
}
