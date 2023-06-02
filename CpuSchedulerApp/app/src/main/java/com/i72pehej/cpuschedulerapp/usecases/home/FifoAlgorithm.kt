package com.i72pehej.cpuschedulerapp.usecases.home

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
            momento = 0,
//            espera = 0,
//            estado = Proceso.EstadoDeProceso.LISTO
        )
    )

//    // Comprobar que el proceso no se encuentra bloqueado
//    if (listaProcesosOrdenada[0].getEstado() == Proceso.EstadoDeProceso.BLOQUEADO) {
//        // Cambio de estado para continuar su ejecucion
//        listaProcesosOrdenada[0].setEstado(Proceso.EstadoDeProceso.LISTO)
//
//        // Guardado del cambio de estado
//        infoEstados.add(
//            InfoGraficoEstados(
//                proceso = listaProcesosOrdenada[0],
//                momento = tiempoActual,
//                espera = listaProcesosOrdenada[0].getTiempoEspera(),
//                estado = listaProcesosOrdenada[0].getEstado()
//            )
//        )
//    }

//    // Variable para almacenar el progreso de los TIEMPOS de cada proceso durante el algoritmo
//    val infoTiempos = mutableListOf(
//        InfoGraficoGantt(
//            pid = (-1).toString(),
//            tiempoFinEjecucion = listaProcesosOrdenada[0].getLlegada(),
//            tiempoEjecutado = 0
//        )
//    )

    // Iniciamos los tiempos de control
    listaDeProcesosGlobal[0].setTiempoEspera(0)

    // Actualizamos su estado a proceso COMPLETADO
    listaDeProcesosGlobal[0].setEstado(Proceso.EstadoDeProceso.COMPLETADO)

    // Guardado del cambio de estado
    infoEstados.add(
        InfoGraficoEstados(
            proceso = listaDeProcesosGlobal[0],
            momento = tiempoActual,
//            espera = listaProcesosOrdenada[0].getTiempoEspera(),
//            estado = listaProcesosOrdenada[0].getEstado()
        )
    )

    // Continuacion de la ejecucion agregando los tiempos del primer proceso en CPU
    listaDeProcesosGlobal[0].setTiempoRestante(0) // En FIFO, al no tener interrupciones, siempre sera 0

//    infoTiempos.add(
//        InfoGraficoGantt(
//            pid = listaProcesosOrdenada[0].getNombre(),
//            tiempoFinEjecucion = tiempoActual,
//            tiempoEjecutado = listaProcesosOrdenada[0].getDuracion()
//        )
//    )

    // Iteraciones para recorrer todos los procesos restantes despues del primero
    for (proceso in 1 until listaDeProcesosGlobal.size) {
//        // Comprobar que el proceso no se encuentra bloqueado
//        if (listaProcesosOrdenada[proceso].getEstado() == Proceso.EstadoDeProceso.BLOQUEADO) {
//            // Cambio de estado para continuar su ejecucion
//            listaProcesosOrdenada[proceso].setEstado(Proceso.EstadoDeProceso.LISTO)
//
//            // Guardado del cambio de estado
//            infoEstados.add(
//                InfoGraficoEstados(
//                    proceso = listaProcesosOrdenada[proceso].getNombre(),
//                    momento = tiempoActual,
//                    espera = listaProcesosOrdenada[proceso].getTiempoEspera(),
//                    estado = listaProcesosOrdenada[proceso].getEstado()
//                )
//            )
//        }

        // Actualizar el estado del proceso a en EJECUCION
        listaDeProcesosGlobal[proceso].setEstado(Proceso.EstadoDeProceso.EJECUCION)

        // Calculo de la diferencia entre el momento actual y la llegada del proceso para el tiempo de espera
        val diffTiempos = tiempoActual - listaDeProcesosGlobal[proceso].getLlegada()
        listaDeProcesosGlobal[proceso].setTiempoEspera(if (diffTiempos > 0) diffTiempos else 0)

        // Guardado del cambio de estado
        infoEstados.add(
            InfoGraficoEstados(
                proceso = listaDeProcesosGlobal[proceso],
                momento = tiempoActual,
//                espera = listaProcesosOrdenada[proceso].getTiempoEspera(),
//                estado = listaProcesosOrdenada[proceso].getEstado()
            )
        )

        // Avanzamos el momento actual hasta finalizar el proceso
        tiempoActual += listaDeProcesosGlobal[proceso].getDuracion()

//        // Agregar los tiempos del proceso ejecutado a la lista de informacion
//        infoTiempos.add(
//            InfoGraficoGantt(
//                pid = listaProcesosOrdenada[proceso].getNombre(),
//                tiempoFinEjecucion = tiempoActual,
//                tiempoEjecutado = listaProcesosOrdenada[proceso].getDuracion()
//            )
//        )

        // Actualizamos el estado a proceso COMPLETADO
        listaDeProcesosGlobal[proceso].setEstado(Proceso.EstadoDeProceso.COMPLETADO)

        // Guardado del cambio de estado
        infoEstados.add(
            InfoGraficoEstados(
                proceso = listaDeProcesosGlobal[proceso],
                momento = tiempoActual,
//                espera = listaProcesosOrdenada[proceso].getTiempoEspera(),
//                estado = listaProcesosOrdenada[proceso].getEstado()
            )
        )
    }

    // Limpiamos el primer elemento utilizado de base para poder operar
//    infoTiempos.removeAt(0)
    infoEstados.removeAt(0)

    // Reasignamos la lista de procesos definitiva a la lista compartida
//    listaDeProcesosGlobal = listaProcesosOrdenada

    println("\n--------------------------\n")
    listaDeProcesosGlobal.forEach {
        println("$it, ${it.getEstado()}")
    }
    println("\n--------------------------\n")
    println(infoEstados)

    // Devolver la variable de informacion de los tiempos
    return infoEstados
}