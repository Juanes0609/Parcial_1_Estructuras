package datos;

import modelo.Paquete;
import modelo.Repartidor;
import servicio.CentroDistribucion;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class DatosIniciales {
    public static void cargarDatos(CentroDistribucion centro) {
        centro.registrarRepartidor(new Repartidor("R001", "Carlos Perez", "Armenia", Repartidor.Estado.DISPONIBLE));
        centro.registrarRepartidor(new Repartidor("R002", "Ana Gomez", "Salento", Repartidor.Estado.DISPONIBLE));
        centro.registrarRepartidor(new Repartidor("R003", "Luis Marin", "Calarcá", Repartidor.Estado.DISPONIBLE));
        centro.registrarRepartidor(new Repartidor("R004", "Maria Lopez", "Filandia", Repartidor.Estado.NO_DISPONIBLE));
        centro.registrarRepartidor(new Repartidor("R005", "Jorge Rios", "Quimbaya", Repartidor.Estado.DISPONIBLE));
        centro.registrarRepartidor(new Repartidor("R006", "Diana Ruiz", "Armenia", Repartidor.Estado.DISPONIBLE));

        String[] destinos = {"Armenia", "Salento", "Calarcá", "Filandia", "Quimbaya"};

        // Se garantiza que los códigos aleatorios no se repitan
        Set<String> codigosGenerados = new HashSet<>();
        for (int i = 1; i <= 30; i++) {
            String codigo;
            // Codigos no consecutivos

            do {codigo = "PQ" + String.format("%03d", ThreadLocalRandom.current().nextInt(1000));
            } while (!codigosGenerados.add(codigo));

            // Se reparte uniformemente entre los 5 municipios
            String destino = destinos[i % 5];

            // Pesos variados entre 1.5 y 16.0
            double peso = 1.5 + (i % 15);

            int prioridad = ThreadLocalRandom.current().nextInt(1,6);

            int tiempoEstimado = 20 + (i * 4);

            Paquete p = new Paquete(codigo, destino, peso, prioridad, tiempoEstimado);
            centro.registrarPaquete(p);
        }

        System.out.println("Carga de datos exitosa: 30 paquetes y 6 repartidores registrados.");
    }
}
