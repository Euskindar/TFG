package com.i72pehej.cpuschedulerapp.usecases.home

import com.i72pehej.cpuschedulerapp.util.Proceso
import com.i72pehej.cpuschedulerapp.util.orderProcessList

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

val listaDeProcesos = mutableListOf<Proceso>()

fun FifoAlgorithm(listaDeProcesos: MutableList<Proceso>) {
    val listaProcesosOrdenada = listaDeProcesos
    orderProcessList(listaProcesosOrdenada)

    for (proceso in listaProcesosOrdenada) {

        // TODO
        // Comprobar que el proceso no se encuentra bloqueado o algo asi antes de nada



        // Actualizar el estado del proceso a "Ejecutándose"
        proceso.estado = Proceso.EstadoDeProceso.CORRIENDO

        // Simular la ejecución del proceso
        for (time in 1..proceso.duracion) {
            // Aquí puedes realizar las operaciones necesarias durante la ejecución del proceso





            // Actualizar el progreso del proceso
            proceso.progreso = time

            // Pausar la ejecución para simular el tiempo de ráfaga del proceso
            Thread.sleep(1000) // 1 segundo de pausa

            // Actualizar el estado del proceso a "Esperando"
            proceso.estado = Proceso.EstadoDeProceso.BLOQUEADO
        }

        // Actualizar el estado del proceso a "Terminado"
        proceso.estado = Proceso.EstadoDeProceso.COMPLETADO
    }
}

/**
 * ===================================================================
 */