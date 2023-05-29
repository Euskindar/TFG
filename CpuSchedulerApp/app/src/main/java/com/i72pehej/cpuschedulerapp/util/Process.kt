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
 * @property nombre Nombre asignado al proceso
 * @property tiempoLlegada Momento en el que el proceso entra en la cola de procesos listos
 * @property duracion Duracion estimada del proceso
 * @property estado Estado actual del proceso, representado por la clase enumerada
 * @constructor Crea la representacion de un Proceso de CPU
 */
class Proceso(
    val nombre: String,
    val tiempoLlegada: Int,
    val duracion: Int,
    var estado: EstadoDeProceso?
) {
    // Listado de estados asociados a los procesos
    enum class EstadoDeProceso {
        NUEVO,      // Cuando el proceso acaba de ser creado
        LISTO,      // Cuando el proceso se encuentra a la espera de entrar en la CPU
        EJECUCION,  // Cuando el proceso se encuentra en la CPU
        BLOQUEADO,  // Cuando el proceso queda temporalmente detenido por otras tareas
        COMPLETADO  // Cuando el proceso ha terminado su ejecucion
    }
}

/**
 * ===================================================================
 */

/**
 * Funcion para crear y agregar un proceso nuevo a la lista de procesos
 *
 * @property nombre Nombre asignado al proceso
 * @property tiempoLlegada Momento en el que el proceso entra en la cola de procesos listos
 * @property duracion Duracion estimada del proceso
 */
fun crearProceso(
    nombre: String,
    tiempoLlegada: Int,
    duracion: Int,
): Proceso {
    // Valores por defecto
    val newEstado = Proceso.EstadoDeProceso.NUEVO

    // Se crea un proceso y se devuelve
    return Proceso(nombre, tiempoLlegada, duracion, newEstado)
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
        println("NOMBRE: ${proceso.nombre}, LLEGADA: ${proceso.tiempoLlegada}, DURACION: ${proceso.duracion}, ESTADO: ${proceso.estado}")
    }
}

/**
 * Imprimir proceso por terminal
 */
fun imprimirProceso(proceso: Proceso) {
    println("IMPRIMIENDO PROCESO . . .")
    println("NOMBRE: ${proceso.nombre}, LLEGADA: ${proceso.tiempoLlegada}, DURACION: ${proceso.duracion}, ESTADO: ${proceso.estado}")
}