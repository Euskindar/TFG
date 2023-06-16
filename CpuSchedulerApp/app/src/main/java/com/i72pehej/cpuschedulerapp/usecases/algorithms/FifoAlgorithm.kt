package com.i72pehej.cpuschedulerapp.usecases.algorithms

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
 * Funcion para implementar el algoritmo FIFO considerando estados
 */
//fun algoritmoFifo(listaDeProcesos: MutableList<Proceso>): MutableList<InfoGraficoEstados> {
fun algoritmoFifo(): MutableList<InfoGraficoEstados> {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarListaProcesos(listaDeProcesosGlobal)

    // Comienzo de la ejecucion

    // Variable para almacenar el avance del tiempo con cada proceso
    var tiempoActual = listaDeProcesosGlobal[0].getDuracion()

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf(
        InfoGraficoEstados(
            proceso = crearProceso(nombre = "-", tiempoLlegada = 0, duracion = 0),
            momento = 0
        )
    )

    // Iniciamos los tiempos de control
    listaDeProcesosGlobal[0].setTiempoEspera(0)

    // Actualizamos su estado a proceso COMPLETADO
    listaDeProcesosGlobal[0].setEstado(Proceso.EstadoDeProceso.COMPLETADO)

    // Guardado del cambio de estado
    infoEstados.add(
        InfoGraficoEstados(
            proceso = listaDeProcesosGlobal[0],
            momento = tiempoActual
        )
    )

    // Continuacion de la ejecucion agregando los tiempos del primer proceso en CPU
    listaDeProcesosGlobal[0].setTiempoRestante(0) // En FIFO, al no tener interrupciones, siempre sera 0

    // Iteraciones para recorrer todos los procesos restantes despues del primero
    for (proceso in 1 until listaDeProcesosGlobal.size) {
        // Actualizar el estado del proceso a en EJECUCION
        listaDeProcesosGlobal[proceso].setEstado(Proceso.EstadoDeProceso.EJECUCION)

        // Calculo de la diferencia entre el momento actual y la llegada del proceso para el tiempo de espera
        val diffTiempos = tiempoActual - listaDeProcesosGlobal[proceso].getLlegada()
        listaDeProcesosGlobal[proceso].setTiempoEspera(if (diffTiempos > 0) diffTiempos else 0)

        // Guardado del cambio de estado
        infoEstados.add(
            InfoGraficoEstados(
                proceso = listaDeProcesosGlobal[proceso],
                momento = tiempoActual
            )
        )

        // Avanzamos el momento actual hasta finalizar el proceso
        tiempoActual += listaDeProcesosGlobal[proceso].getDuracion()

        // Actualizamos el estado a proceso COMPLETADO
        listaDeProcesosGlobal[proceso].setEstado(Proceso.EstadoDeProceso.COMPLETADO)

        // Guardado del cambio de estado
        infoEstados.add(
            InfoGraficoEstados(
                proceso = listaDeProcesosGlobal[proceso],
                momento = tiempoActual
            )
        )
    }

    // Limpiamos el primer elemento utilizado de base para poder operar
    infoEstados.removeAt(0)

    // Devolver la variable de informacion de los tiempos
    return infoEstados
}