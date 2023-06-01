package com.i72pehej.cpuschedulerapp.usecases.home

import com.i72pehej.cpuschedulerapp.util.Proceso
import com.i72pehej.cpuschedulerapp.util.extensions.InfoGraficoEstados
import com.i72pehej.cpuschedulerapp.util.extensions.InfoGraficoGantt
import com.i72pehej.cpuschedulerapp.util.extensions.InfoResultsFinalData
import com.i72pehej.cpuschedulerapp.util.ordenarListaProcesos
import kotlin.system.exitProcess

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

/**
 * Funcion para implementar el algoritmo FIFO considerando estados
 *
 * @param listaDeProcesos Recibe una lista con los procesos creados para aplicar el algoritmo
 */
fun algoritmoFifo(listaDeProcesos: MutableList<Proceso>): InfoResultsFinalData {
    // Comprobacion inicial para que la lista de procesos no este vacia
    if (listaDeProcesos.isEmpty()) {
        exitProcess(-1)
    } else {
        // Ordenar la lista de procesos por tiempo de llegada
        val listaProcesosOrdenada = ordenarListaProcesos(listaDeProcesos)

        // Comienzo de la ejecucion

        // Variable para almacenar el avance del tiempo con cada proceso
        var tiempoActual = listaProcesosOrdenada[0].getDuracion()

        // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
        val infoEstados = mutableListOf(
            InfoGraficoEstados(
                pid = (-1).toString(),
                momento = 0,
                espera = 0,
                estado = Proceso.EstadoDeProceso.LISTO
            )
        )

        // Comprobar que el proceso no se encuentra bloqueado
        if (listaProcesosOrdenada[0].getEstado() == Proceso.EstadoDeProceso.BLOQUEADO) {
            // Cambio de estado para continuar su ejecucion
            listaProcesosOrdenada[0].setEstado(Proceso.EstadoDeProceso.LISTO)

            // Guardado del cambio de estado
            infoEstados.add(
                InfoGraficoEstados(
                    pid = listaProcesosOrdenada[0].getNombre(),
                    momento = tiempoActual,
                    espera = listaProcesosOrdenada[0].getTiempoEspera(),
                    estado = listaProcesosOrdenada[0].getEstado()
                )
            )
        }

        // Variable para almacenar el progreso de los TIEMPOS de cada proceso durante el algoritmo
        val infoTiempos = mutableListOf(
            InfoGraficoGantt(
                pid = (-1).toString(),
                tiempoFinEjecucion = listaProcesosOrdenada[0].getLlegada(),
                tiempoEjecutado = 0
            )
        )

        // Iniciamos los tiempos de control
        listaProcesosOrdenada[0].setTiempoRespuesta(0)
        listaProcesosOrdenada[0].setTiempoRestante(0)
        listaProcesosOrdenada[0].setTiempoEspera(0)

        // Continuacion de la ejecucion agregando los tiempos del primer proceso en CPU
        infoTiempos.add(
            InfoGraficoGantt(
                pid = listaProcesosOrdenada[0].getNombre(),
                tiempoFinEjecucion = tiempoActual,
                tiempoEjecutado = listaProcesosOrdenada[0].getDuracion()
            )
        )

        // Actualizamos su estado a proceso COMPLETADO
        listaProcesosOrdenada[0].setEstado(Proceso.EstadoDeProceso.COMPLETADO)

        // Guardado del cambio de estado
        infoEstados.add(
            InfoGraficoEstados(
                pid = listaProcesosOrdenada[0].getNombre(),
                momento = tiempoActual,
                espera = listaProcesosOrdenada[0].getTiempoEspera(),
                estado = listaProcesosOrdenada[0].getEstado()
            )
        )

        // Iteraciones para recorrer todos los procesos restantes despues del primero
        for (proceso in 1 until listaProcesosOrdenada.size) {
            // Comprobar que el proceso no se encuentra bloqueado
            if (listaProcesosOrdenada[proceso].getEstado() == Proceso.EstadoDeProceso.BLOQUEADO) {
                // Cambio de estado para continuar su ejecucion
                listaProcesosOrdenada[proceso].setEstado(Proceso.EstadoDeProceso.LISTO)

                // Guardado del cambio de estado
                infoEstados.add(
                    InfoGraficoEstados(
                        pid = listaProcesosOrdenada[proceso].getNombre(),
                        momento = tiempoActual,
                        espera = listaProcesosOrdenada[proceso].getTiempoEspera(),
                        estado = listaProcesosOrdenada[proceso].getEstado()
                    )
                )
            }

            // Actualizar el estado del proceso a en EJECUCION
            listaProcesosOrdenada[proceso].setEstado(Proceso.EstadoDeProceso.EJECUCION)

            // Guardado del cambio de estado
            infoEstados.add(
                InfoGraficoEstados(
                    pid = listaProcesosOrdenada[proceso].getNombre(),
                    momento = tiempoActual,
                    espera = listaProcesosOrdenada[proceso].getTiempoEspera(),
                    estado = listaProcesosOrdenada[proceso].getEstado()
                )
            )

            // Calculo de la diferencia entre el momento actual y la llegada del proceso para el tiempo de espera
            val diffTiempos = tiempoActual - listaProcesosOrdenada[proceso].getLlegada()
            listaProcesosOrdenada[proceso].setTiempoEspera(if (diffTiempos > 0) diffTiempos else 0)

            // Asignamos el tiempo pasado como tiempo de respuesta (FIFO)
            listaProcesosOrdenada[proceso].setTiempoRespuesta(tiempoActual)

            // Avanzamos el momento actual hasta finalizar el proceso
            tiempoActual += listaProcesosOrdenada[proceso].getDuracion()

            // Actualizamos el tiempo restante, en FIFO no es realmente relevante ya que no se bloquea el estado de los procesos hasta que no terminan
            listaProcesosOrdenada[proceso].setTiempoRestante(0)

            // Agregar los tiempos del proceso ejecutado a la lista de informacion
            infoTiempos.add(
                InfoGraficoGantt(
                    pid = listaProcesosOrdenada[proceso].getNombre(),
                    tiempoFinEjecucion = tiempoActual,
                    tiempoEjecutado = listaProcesosOrdenada[proceso].getDuracion()
                )
            )

            // Actualizamos el estado a proceso COMPLETADO
            listaProcesosOrdenada[proceso].setEstado(Proceso.EstadoDeProceso.COMPLETADO)

            // Guardado del cambio de estado
            infoEstados.add(
                InfoGraficoEstados(
                    pid = listaProcesosOrdenada[proceso].getNombre(),
                    momento = tiempoActual,
                    espera = listaProcesosOrdenada[proceso].getTiempoEspera(),
                    estado = listaProcesosOrdenada[proceso].getEstado()
                )
            )
        }

        // Limpiamos el primer elemento utilizado de base para poder operar
        infoTiempos.removeAt(0)
        infoEstados.removeAt(0)

        println("\n--------------------------\n")
        println(infoTiempos)
        println("\n--------------------------\n")
        println(infoEstados)

        // Devolver la variable de informacion de los tiempos
        return InfoResultsFinalData(infoTiempos, infoEstados)
    }
}