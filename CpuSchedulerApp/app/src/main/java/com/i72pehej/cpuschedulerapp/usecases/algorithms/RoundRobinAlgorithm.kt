package com.i72pehej.cpuschedulerapp.usecases.algorithms

import com.i72pehej.cpuschedulerapp.util.CircularQueue
import com.i72pehej.cpuschedulerapp.util.Proceso
import com.i72pehej.cpuschedulerapp.util.crearProceso
import com.i72pehej.cpuschedulerapp.util.extensions.InfoGraficoEstados
import com.i72pehej.cpuschedulerapp.util.listaDeProcesosGlobal
import com.i72pehej.cpuschedulerapp.util.ordenarListaProcesos

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

/**
 * Funcion para implementar el algoritmo RoundRobin considerando estados
 */
fun algoritmoRoundRobin(): MutableList<InfoGraficoEstados> {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarListaProcesos(listaDeProcesosGlobal)

    // Comienzo de la ejecucion

//    // Variable para almacenar el avance del tiempo con cada proceso
//    var tiempoActual = listaDeProcesosGlobal[0].getDuracion()

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf(
        InfoGraficoEstados(
            proceso = crearProceso(nombre = "-", tiempoLlegada = 0, duracion = 0),
            momento = 0
        )
    )

    //////////////////////////////////////////////////////////////////////////


//        val info: MutableList<ChartInfo> = mutableListOf(ChartInfo(-1, processes[0].arrivalTime, 0)) // muestra 0 primero

    // Variable para almacenar el avance del tiempo con cada proceso
    var tiempoActual = listaDeProcesosGlobal[0].getLlegada()

    // Iniciamos la cola de procesos LISTOS y agregamos el primero
    val colaDeListos = CircularQueue<Proceso>(listaDeProcesosGlobal.size + 1)
    colaDeListos.add(listaDeProcesosGlobal[0])

    // Empezamos el tiempo de respuesta en 0 para el primer proceso
    listaDeProcesosGlobal[0].setTiempoRespuesta(0)

    // Variable par indicar el siguiente proceso ????????????
    var nextIdx = 1

    // Bucle de procesado para la cola de listos
    while (colaDeListos.isNotEmpty()) {
        val newRunProcess: Proceso = colaDeListos.votacion()
        val performedTime =
            if (newRunProcess.remainedTime >= quantum) quantum else newRunProcess.remainedTime % quantum
        if (newRunProcess.responseTime == -1) newRunProcess.responseTime = currentRunTime

        currentRunTime += performedTime
        newRunProcess.remainedTime -= performedTime

        info += ChartInfo(
            newRunProcess.pid,
            currentRunTime,
            performedTime
        ) // ran at $currentRunTime

        while (nextIdx < listaDeProcesosGlobal.size && currentRunTime >= listaDeProcesosGlobal[nextIdx].arrivalTime) {
            colaDeListos.add(listaDeProcesosGlobal[nextIdx])
            ++nextIdx
        }

        if (newRunProcess.remainedTime == 0) { // Job finished
            newRunProcess.waitedTime =
                currentRunTime - newRunProcess.arrivalTime - newRunProcess.executionTime
            //println("${performed.pid}: finished,, at $currentRunTime")
        } else {
            colaDeListos.add(newRunProcess)
        }
    }
//    println("Total Executed time : $currentRunTime")
//    println("Context Switched : ${info.size - 2}")
    return ProgramData(listaDeProcesosGlobal, info)


    //////////////////////////////////////////////////////////////////////////

    // Devolver la variable de informacion de los tiempos
    return infoEstados
}