package com.i72pehej.cpuschedulerapp.util

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

/**
 * Clase que representa cada uno de los procesos
 *
 * @property id Identificador del proceso
 * @property nombre Nombre asignado al proceso
 * @property tiempoLlegada Momento en el que el proceso entra en la cola de procesos listos
 * @property duracion Duracion estimada del proceso
 * @property estado Estado actual del proceso, representado por la clase enumerada
 * @property progreso Tiempo actual en el que se encuentra el proceso
 * @constructor Crea la representacion de un Proceso de CPU
 */
class Proceso(
    val id: Int = 0,
    val nombre: String,
    val tiempoLlegada: Int,
    val duracion: Int,
    var estado: EstadoDeProceso = EstadoDeProceso.NUEVO,
    var progreso: Int = 0
) {
    // Listado de estados asociados a los procesos
    enum class EstadoDeProceso {
        NUEVO, LISTO, CORRIENDO, ESPERANDO, COMPLETADO, BLOQUEADO
    }
}

/**
 * ===================================================================
 */

/**
 * Listado de procesos
 */
val listaDeProcesos = mutableListOf<Proceso>()

/**
 * Funcion para crear y agregar un proceso nuevo a la lista de procesos
 *
 * @property id Identificador del proceso
 * @property nombre Nombre asignado al proceso
 * @property tiempoLlegada Momento en el que el proceso entra en la cola de procesos listos
 * @property duracion Duracion estimada del proceso
 * @property estado Estado actual del proceso, representado por la clase enumerada ProcessState
 * @property progreso Tiempo actual en el que se encuentra el proceso
 */
fun crearProceso(
    id: Int,
    nombre: String,
    tiempoLlegada: Int,
    duracion: Int,
    estado: Proceso.EstadoDeProceso,
    progreso: Int
) {
    // Se crea un proceso
    val proceso = Proceso(id, nombre, tiempoLlegada, duracion, estado, progreso)

    // Se agrega a la lista de procesos
    listaDeProcesos.add(proceso)
}

/**
 * ===================================================================
 */

/**
 * Funcion que ordena la lista de procesos por orden de llegada de forma ascendente
 */
fun ordenarListaProcesos(listaDeProcesos: MutableList<Proceso>) {
    listaDeProcesos.sortBy { it.tiempoLlegada }
}

/**
 * ===================================================================
 */

/**
 * Imprimir lista de procesos por terminal
 */
fun imprimirListaProcesos(listaDeProcesos: MutableList<Proceso>) {
    println("IMPRIMIENDO LISTADO DE PROCESOS . . .")
    listaDeProcesos.forEach { proceso ->
        println("ID: ${proceso.id}, NOMBRE: ${proceso.nombre}, LLEGADA: ${proceso.tiempoLlegada}, DURACION: ${proceso.duracion}, ESTADO: ${proceso.estado}, PROGRESO: ${proceso.progreso}")
    }
}

/**
 * Imprimir proceso por terminal
 */
fun imprimirProceso(proceso: Proceso) {
    println("IMPRIMIENDO PROCESO . . .")
    println("ID: ${proceso.id}, NOMBRE: ${proceso.nombre}, LLEGADA: ${proceso.tiempoLlegada}, DURACION: ${proceso.duracion}, ESTADO: ${proceso.estado}, PROGRESO: ${proceso.progreso}")
}

/**
 * ===================================================================
 */