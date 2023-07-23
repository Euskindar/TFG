package com.i72pehej.cpuschedulerapp.usecases.algorithms

import com.i72pehej.cpuschedulerapp.util.classes.InfoGraficoEstados
import com.i72pehej.cpuschedulerapp.util.classes.Proceso
import com.i72pehej.cpuschedulerapp.util.classes.crearProceso
import com.i72pehej.cpuschedulerapp.util.classes.ordenarListaProcesos
import com.i72pehej.cpuschedulerapp.util.listaDeProcesosGlobal

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

// TODO -> NO FUNCIONA BIEN TODAVIA, EN ESPERA DE FUTURA IMPLEMENTACION CORRECTA

/**
 * Funcion para implementar el algoritmo RoundRobin considerando estados
 *
 * @param quantum Quantum seleccionado para ejecutar el algoritmo de RR
 *
 * @return Devuelve un listado con el historico de cambios de estados de los procesos
 */
fun roundRobin(quantum: Int): MutableList<InfoGraficoEstados> {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarListaProcesos(listaDeProcesosGlobal)

    // Comienzo de la ejecucion

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf(InfoGraficoEstados(proceso = crearProceso(nombre = "-", tiempoLlegada = 0, duracion = 0, estado = Proceso.EstadoDeProceso.LISTO), momento = 0))

    // Inicializa el tiempo total en 0
    var tiempoActual = 0

    // Crea una lista mutable con los procesos restantes
    val procesosRestantes = listaDeProcesosGlobal.toMutableList()

    // Mientras haya procesos restantes
    while (procesosRestantes.isNotEmpty()) {
        // Obtiene el primer proceso de la lista
        val procesoActual = procesosRestantes.first()

        // Calculo de la diferencia entre el momento actual y la llegada del proceso para el tiempo de espera
        val diffTiempos = tiempoActual - listaDeProcesosGlobal[listaDeProcesosGlobal.indexOf(procesoActual)].getLlegada()
        listaDeProcesosGlobal[listaDeProcesosGlobal.indexOf(procesoActual)].setTiempoEspera(if (diffTiempos > 0) diffTiempos else 0)

        // Elimina el primer proceso de la lista (se pasa a ejecucion)
        procesosRestantes.removeAt(0)

        // Si el tiempo restante del proceso es menor o igual al quantum
        if (procesoActual.getTiempoRestante() <= quantum) {
            // Suma la duracion del proceso al tiempo total
            tiempoActual = if (procesoActual.getTiempoRestante() >= 0) tiempoActual + procesoActual.getTiempoRestante() else tiempoActual + 0
        }
        // Si la duracion del proceso es mayor al quantum
        else {
            // Suma el quantum al tiempo total
            tiempoActual += quantum

            // Reduce el tiempo restante del proceso en una cantidad igual al quantum
            val tiempoAux = if (procesoActual.getTiempoRestante() - quantum < 0) 0 else procesoActual.getTiempoRestante() - quantum
            procesoActual.setTiempoRestante(tiempoAux)
            listaDeProcesosGlobal[listaDeProcesosGlobal.indexOf(procesoActual)].setTiempoRestante(tiempoAux)

            println("---------TIEMPO1")
            println(procesoActual.getTiempoRestante())
            println("---------")
            println(listaDeProcesosGlobal[listaDeProcesosGlobal.indexOf(procesoActual)].getTiempoRestante())
            println("---------TIEMPO2")

            // Agrega el proceso actual al final de la lista de procesos restantes para que pueda ser ejecutado nuevamente más tarde
            procesosRestantes.add(procesoActual)
        }
        println(tiempoActual)
    }

    // Limpiamos el primer elemento utilizado de base para poder operar
    infoEstados.removeAt(0)

    // Devolver la variable de informacion de los tiempos
    return infoEstados
}

