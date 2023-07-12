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
 *
 * @param tiempoQuantum Quantum seleccionado para ejecutar el algoritmo de RR
 * @return Devuelve un listado con el historico de cambios de estados de los procesos
 */
fun algoritmoRoundRobin(tiempoQuantum: Int): MutableList<InfoGraficoEstados> {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarListaProcesos(listaDeProcesosGlobal)

    // Comienzo de la ejecucion

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf(
        InfoGraficoEstados(
            proceso = crearProceso(nombre = "-", tiempoLlegada = 0, duracion = 0),
            momento = 0
        )
    )

    //////////////////////////////////////////////////////////////////////////


    // Variable para almacenar el avance del tiempo con cada proceso
    var tiempoActual = listaDeProcesosGlobal[0].getLlegada()

    // Iniciamos la cola de procesos LISTOS y agregamos el primero
    val colaDeListos = CircularQueue<Proceso>(listaDeProcesosGlobal.size + 1)
    colaDeListos.add(listaDeProcesosGlobal[0])

    // Empezamos el tiempo de respuesta en 0 para el primer proceso
    listaDeProcesosGlobal[0].setTiempoRespuesta(0)

    // TODO Variable par indicar el siguiente proceso ????????????
    var nextIdx = 1

    // Bucle de procesado para la cola de listos
    while (colaDeListos.isNotEmpty()) {
        // Inicio de la votacion para seleccionar los indices de la cola
        val procesoEnEjecucion: Proceso = colaDeListos.votacion()

        // Variable para almacenar el tiempo que lleva la ejecucion
        val tiempoEjecutado =
            if (procesoEnEjecucion.getTiempoRestante() >= tiempoQuantum) tiempoQuantum else procesoEnEjecucion.getTiempoRestante() % tiempoQuantum
        if (procesoEnEjecucion.getTiempoRespuesta() == -1) procesoEnEjecucion.setTiempoRespuesta(
            tiempoActual
        )

        tiempoActual += tiempoEjecutado
        procesoEnEjecucion.setTiempoRestante(procesoEnEjecucion.getTiempoRestante() - tiempoEjecutado)

        // TODO ->  Guardar el cambio de estados ????
//        infoEstados += ChartInfo(
//            newRunProcess.pid,
//            tiempoActual,
//            tiempoEjecutado
//        ) // ran at $currentRunTime

        while ((nextIdx < listaDeProcesosGlobal.size) && (tiempoActual >= listaDeProcesosGlobal[nextIdx].getLlegada())) {
            colaDeListos.add(listaDeProcesosGlobal[nextIdx])
            ++nextIdx
        }

        // Job finished
        if (procesoEnEjecucion.getTiempoRestante() == 0) {
            procesoEnEjecucion.setTiempoEspera(tiempoActual - procesoEnEjecucion.getLlegada() - procesoEnEjecucion.getDuracion())
            //println("${performed.pid}: finished,, at $currentRunTime")
        } else {
            colaDeListos.add(procesoEnEjecucion)
        }
    }
//    println("Total Executed time : $currentRunTime")
//    println("Context Switched : ${infoEstados.size - 2}")
//    return ProgramData(listaDeProcesosGlobal, infoEstados)


    //////////////////////////////////////////////////////////////////////////

    // Devolver la variable de informacion de los tiempos
    return infoEstados
}


// CHAT DE BING

fun roundRobin(quantum: Int): MutableList<InfoGraficoEstados> {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarListaProcesos(listaDeProcesosGlobal)

    // Comienzo de la ejecucion

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf(InfoGraficoEstados(proceso = crearProceso(nombre = "-", tiempoLlegada = 0, duracion = 0), momento = 0))

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

