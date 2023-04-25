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
 * @property estado Estado actual del proceso, representado por la clase enumerada ProcessState
 * @property progreso Tiempo actual en el que se encuentra el proceso
 * @constructor Crea la representacion de un Proceso de CPU
 */
class Proceso(
    val id: Int,
    val nombre: String,
    val tiempoLlegada: Int,
    val duracion: Int,
    var estado: EstadoDeProceso,
    var progreso: Int
) {
    // Listado de estados asociados a los procesos
    enum class EstadoDeProceso {
        NUEVO, LISTO, CORRIENDO, BLOQUEADO, COMPLETADO
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
fun createProcess(
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
fun orderProcessList(listaDeProcesos: MutableList<Proceso>) {
    listaDeProcesos.sortBy { it.tiempoLlegada }
}

/**
 * ===================================================================
 */