## QuindíoExpress - Sistema de Gestión de Operaciones Logísticas

Este proyecto es una implementación en Java orientada a objetos desarrollada para gestionar las operaciones de un centro de distribución logístico (QuindíoExpress). El sistema está diseñado de forma colaborativa, combinando el poder y eficiencia de las colecciones nativas de Java con la implementación algorítmica y de estructuras de datos personalizadas desde cero.

## Arquitectura del Proyecto

El código fuente está modularizado en paquetes para separar las responsabilidades, aplicando principios de Clean Code y POO:

### 1. Modelo y Dominio (`src/modelo/`)
* **`Paquete.java`**: Entidad principal que implementa validaciones de estado y la interfaz `Comparable` para el orden natural (alfabético por código).
* **`Repartidor.java`**: Entidad que gestiona la disponibilidad de los conductores usando `Enum` para el control de estados.

### 2. Servicio y Lógica Core (`src/servicio/`)
* **`CentroDistribucion.java`**: Núcleo del sistema. Implementa eficiencia $O(1)$ y agrupaciones mediante el uso de:
    * `HashMap` para consultas ultrarrápidas y gestión de entidades únicas.
    * `HashSet` y `TreeSet` para el manejo y ordenamiento de municipios sin duplicados.
    * `PriorityQueue` y `ArrayDeque` para las colas de despacho y orden de llegada.

### 3. Ordenamiento (`src/comparadores/`)
Implementaciones de la interfaz `Comparator` para inyectar reglas de negocio flexibles:
* `Despacho.java`: Regla compleja combinando Prioridad, Tiempo y Orden natural.
* `PorPeso.java`, `PorPrioridad.java`, `PorTiempo.java`.

### 4. Estructuras de Datos Propias (`src/estructuras/`)
Implementación genérica desde cero para el manejo dinámico de la memoria:
* **`Coleccion.java`**: Interfaz base.
* **`ListaEnlazada.java`**: Lista simplemente enlazada basada en genéricos (`<T>`).
* **`Nodo.java`**: Elemento atómico de la lista enlazada.

### 5. Algoritmos Avanzados (`src/algoritmos/`)
Resolución de problemas mediante recursividad y paradigmas algorítmicos sin uso de bucles tradicionales:
* **`Recursivos.java`**: Operaciones de conteo y recorrido usando llamadas recursivas.
* **`BusquedaBinaria.java`**: Implementación de búsqueda eficiente en colecciones ordenadas.
* **`DivideYVenceras.java`**: Algoritmos de partición y conquista para procesamiento de datos.

### 6. Datos y Pruebas (`src/datos/`, `src/demo/`, `src/pruebas/`)
* **`DatosIniciales.java`**: Carga segura de 30 paquetes aleatorios y 6 repartidores, controlando colisiones con `ThreadLocalRandom` y `HashSet`.
* **`Main.java`**: Punto de entrada principal con un menú interactivo en consola.
* **`Demostracion.java` / `PruebasListaEnlazada.java`**: Clases dedicadas a la verificación individual de los algoritmos y estructuras propias.

## Cómo ejecutar el proyecto

1. Navega hasta la clase `src/Main.java` y ejecuta el método `main`.
2. Utiliza el menú interactivo en la consola para probar todas las funcionalidades de estructuras de datos y algoritmos.

## Flujo Colaborativo (Git)

Este proyecto fue desarrollado en paralelo por Juan Chica y Verónica Mendoza utilizando un flujo de trabajo centralizado en la rama `main`, garantizando la resolución de conflictos mediante prácticas de sincronización continua (`git pull` antes de cada `git`)
