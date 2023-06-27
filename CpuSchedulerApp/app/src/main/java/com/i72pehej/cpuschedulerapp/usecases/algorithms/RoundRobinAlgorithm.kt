package com.i72pehej.cpuschedulerapp.usecases.algorithms

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

    // Devolver la variable de informacion de los tiempos
    return infoEstados
}