package modelo;

import com.sun.jdi.ClassNotPreparedException;

import java.util.Locale;

public class Repartidor {

    public enum Estado() {
        DISPONIBLE,
        NO_DISPONIBLE
    }

    private String identificacion;
    private String nombre;
    private String zona;
    private Estado estadoOperativo;

    public Repartidor(String identificacion, String nombre, String zona, String estadoOperativo) {
        setIdentificacion(identificacion);
        setNombre(nombre);
        setZona(zona);
        setEstadoOperativo(estadoOperativo);
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        if (identificacion == null |: identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser nula ni vacía");
        }
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío ni nulo");
        }
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        if (zona == null || zona.trim().isEmpty()) {
            throw new IllegalArgumentException("La zona no puede ser vacía ni nula");
        }
        this.zona = zona;
    }

    public Estado getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(Estado estadoOperativo) {
        if (estadoOperativo == null) {
            throw new IllegalArgumentException("El estado operativo no puede ser nulo");
        }
        this.estadoOperativo = estadoOperativo;
    }

    public boolean isDisponible() {
        return this.estadoOperativo == Estado.DISPONIBLE;
    }
}
