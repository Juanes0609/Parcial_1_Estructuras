package servicio;

import modelo.Paquete;
import modelo.Repartidor;

import java.util.*;

public class CentroDistribucion {
    // Necesidad 1: Mantener orden exacto de registro
    private List<Paquete> paquetesOrdenRegistro;

    //Necesidades 2 y 7: consulta rápida por código
    private Map<String, Paquete> paquetesPorCodigo;

    // Agrupar paquete según destino
    private Map<String, List<Paquete>> paquetesPorMunicipio;

    // Gestionar los repartidores por identificación.
    private Map<String, Repartidor> repartidores;

    public CentroDistribucion() {
        this.paquetesOrdenRegistro = new ArrayList<>();
        this.paquetesPorCodigo = new HashMap<>();
        this.paquetesPorMunicipio = new HashMap<>();
        this.repartidores = new HashMap<>();
    }

    /**
     * Registra un nuevo paquete validando que el código no
     * exista previamente.
     * <p>
     *     Valida que si la llave {@code paquete.getDestino()}, no tiene
     *     un valor ({@code List<Paquete>}) asociado, instancia una lista antes
     *     de pasar a agregar el paquete.
     * </p>
     * @param paquete el paquete asociado de tipo {@code Paquete} a agregar
     *                al mapa.
     * @throws IllegalArgumentException si ya existe un paquete con ese
     *         código asociado a un valor.
     */
    public void registrarPaquete(Paquete paquete) {
        if (paquetesPorCodigo.containsKey(paquete.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un paquete con ese código: " + paquete.getCodigo());
        }
        paquetesOrdenRegistro.add(paquete);

        paquetesPorCodigo.put(paquete.getCodigo(), paquete);

        paquetesPorMunicipio.putIfAbsent(paquete.getDestino(), new ArrayList<>());
        paquetesPorMunicipio.get(paquete.getDestino()).add(paquete);
    }

    /**
     * Registra un nuevo repartidor validando que la identidad no esté
     * repetida.
     * @param repartidor el repartidor asociado de tipo {@code Repartidor}
     *                   que se agregará al mapa.
     */
    public void registrarRepartidor(Repartidor repartidor) {
        if (repartidores.containsKey(repartidor.getIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un repartidor con ese ID: " + repartidor.getIdentificacion());
        }
        repartidores.put(repartidor.getIdentificacion(), repartidor);
    }

    /**
     * Consulta el paquete por código. Necesidad 2.
     * @param codigo el código de {@code Paquete} de tipo
     * {@code String} a consultar
     * @return el paquete buscado por código o {@null} si no
     *         no encuentra
     */
    public Paquete consultarPaquete(String codigo) {
        return paquetesPorCodigo.get(codigo);
    }

    /**
     * Obtiene los municipios destino sin duplicados. Necesidad 3.
     * <p>
     *     Extrae las llaves únicamente de {@link #paquetesPorMunicipio} de tipo {@code String}
     *     con {@code keySet()} y las guarda en {@code HashSet<String>}
     * </p>
     * @return un {@code HashSet<String>} con las llaves extraídas de {@link #paquetesPorMunicipio}
     */
    public Set<String> obtMunicipiosSinDuplicados() {
        return new HashSet<>(paquetesPorMunicipio.keySet());
    }


    /**
     * Obtiene los municipios ordenados alfabéticamente. Necesidad 4
     * <p>
     *     Extrae las llaves únicamente de {@link #paquetesPorMunicipio} de tipo {@code String}
     *     con {@code keySet()} y las guarda en {@code TreeSet<String>} para garantizar elementos
     *     únicos y ordenados.
     * </p>
     * @return {@code TreeSet<String>} con las llaves extraídas de {@link #paquetesPorMunicipio}
     */
    public Set<String> obtMunicipiosOrdenados() {
        return new TreeSet<>(paquetesPorMunicipio.keySet());
    }

    /**
     * Retorna todos los paquetes asociados a un municipio.
     * <p>
     *     Extrae los valores asociadas a la clave indicada {@code municipio}
     *     y si no existe la clave a buscar, devuelve una lista vacía para
     *     evitar {@code NullPointerException}.
     * </p>
     * @param municipio la clave de tipo {@code String} a buscar.
     * @return la lista de paquetes asociada a la clave, si no existe
     *         una lista vacía {@code []}.
     */
    public List<Paquete> obtPaquetesPorMunicipio(String municipio) {
        return paquetesPorMunicipio.getOrDefault(municipio, new ArrayList<>());
    }

    public List<Paquete> getPaquetesOrdenRegistro() {
        return paquetesOrdenRegistro;
    }
}
