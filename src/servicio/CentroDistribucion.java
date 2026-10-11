package servicio;

import modelo.Paquete;
import estructuras.ListaEnlazada;
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

    // Colas para las modalidades de atención
    private Queue<Paquete> atencionOrdenLlegada;
    private PriorityQueue<Paquete> despachoPrioritario;
    private ListaEnlazada<Paquete> historialEntregas;

    public CentroDistribucion() {
        this.paquetesOrdenRegistro = new ArrayList<>();
        this.paquetesPorCodigo = new HashMap<>();
        this.paquetesPorMunicipio = new HashMap<>();
        this.repartidores = new HashMap<>();
        this.atencionOrdenLlegada = new ArrayDeque<>();
        this.despachoPrioritario = new PriorityQueue<>(new comparadores.Despacho());
        this.historialEntregas = new ListaEnlazada<>();
    }

    /**
     * Registra un nuevo paquete validando que el código no
     * exista previamente y actualiza las colas de atención.
     * <p>
     *     Valida que si la llave {@code paquete.getDestino()}, no tiene
     *     un valor ({@code List<Paquete>}) asociado, instancia una lista antes
     *     de pasar a agregar el paquete.
     * </p>
     * <p>
     *     Inserta el paquete a las cola de atención por orden de llegada
     *     ({@link #atencionOrdenLlegada}) y en la cola de despacho prioritario
     *     ({@link #despachoPrioritario}).
     * </p>
     * @param paquete el paquete de tipo {@link Paquete} que se va a registrar en el sistema.
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

        atencionOrdenLlegada.offer(paquete);
        despachoPrioritario.offer(paquete);
    }

    /**
     * Registra un nuevo repartidor validando que la identidad no esté
     * repetida.
     * @param repartidor el repartidor de tipo {@link Repartidor}
     *                   que se agregará al mapa.
     * @throws IllegalArgumentException si ya existe un repartidor con ese id.
     */
    public void registrarRepartidor(Repartidor repartidor) {
        if (repartidores.containsKey(repartidor.getIdentificacion())) {
            throw new IllegalArgumentException("Ya existe un repartidor con ese ID: " + repartidor.getIdentificacion());
        }
        repartidores.put(repartidor.getIdentificacion(), repartidor);
    }

    /**
     * Consulta el paquete por código. Necesidad 2.
     * @param codigo el código de {@link Paquete} de tipo
     * {@code String} a consultar.
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

    /**
     * Extrae el siguiente paquete según orden de llegada,
     * sigue la restricción FIFO.
     * <p>
     *     {@code poll} extrae y elimina el elemento de la
     *     cabeza de la cola.
     * </p>
     * @return el paquete de tipo {@link Paquete} que se extrajo
     *         de la cola.
     */
    public Paquete obtSiguientePorOrdenLlegada() {
        return atencionOrdenLlegada.poll();
    }

    /**
     * Extrae el siguiente paquete según las reglas de prioridad
     * establecidas en {@link comparadores.Despacho}.
     *
     * @return el paquete de tipo {@link Paquete} que se extrajo
     *         de la cola
     */
    public Paquete obtSiguientePorPrioridad() {
        return despachoPrioritario.poll();
    }

    public boolean asignarPaqueteRepartidor(String codigoPaquete, String idRepartidor) {
        Paquete paquete = consultarPaquete(codigoPaquete);
        Repartidor repartidor = repartidores.get(idRepartidor);

        if (paquete == null || repartidor == null) {
            throw new IllegalArgumentException("El paquete o repartidor no están en el sistema.");
        }

        if (!repartidor.isDisponible()) {
            System.out.println("No se puede asignar: El repartidor " + repartidor.getNombre() + " no está disponible.");
            return false;
        }

        if (!repartidor.getZona().equals(paquete.getDestino())) {
            System.out.println("No se puede asignar: La zona del repartidor (" + repartidor.getZona() + ")" +
                    "no coincide con el destino del paquete (" + paquete.getDestino() + ").");
            return false;
        }
        repartidor.setEstadoOperativo(Repartidor.Estado.NO_DISPONIBLE);
        System.out.println("Éxito: Paquete " + paquete.getCodigo() +
                " asignado a " + repartidor.getNombre() +
                " rumbo a " + paquete.getDestino());
        return true;
    }

    public void registrarEntrega(String codigo) {
        Paquete paquete = paquetesPorCodigo.get(codigo);
        if (paquete == null) {
            throw new IllegalArgumentException("No existe un paquete con el código: " + codigo);
        }
        if (!paquetesOrdenRegistro.remove(paquete)) {
            throw new IllegalArgumentException("El paquete " + codigo + " ya fue entregado");
        }

        List<Paquete> delMunicipio = paquetesPorMunicipio.get(paquete.getDestino());
        delMunicipio.remove(paquete);
        if (delMunicipio.isEmpty()) {
            paquetesPorMunicipio.remove(paquete.getDestino());
        }

        atencionOrdenLlegada.remove(paquete);
        despachoPrioritario.remove(paquete);

        historialEntregas.agregar(paquete);
    }

    public ListaEnlazada<Paquete> getHistorialEntregas() {
        return historialEntregas;
    }
}
