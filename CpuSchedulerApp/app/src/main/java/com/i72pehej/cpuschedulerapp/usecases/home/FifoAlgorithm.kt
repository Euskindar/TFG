package com.i72pehej.cpuschedulerapp.usecases.home

import com.i72pehej.cpuschedulerapp.util.InfoGraficoGantt
import com.i72pehej.cpuschedulerapp.util.Proceso
import com.i72pehej.cpuschedulerapp.util.imprimirListaProcesos
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
fun algoritmoFifo(listaDeProcesos: MutableList<Proceso>): List<InfoGraficoGantt> {
    // Comprobacion inicial para que la lista de procesos no este vacia
    if (listaDeProcesos.isEmpty()) {
        exitProcess(-1)
    } else {
        println("\nAlgoritmo FIFO seleccionado . . .\n")

        println("\nLISTA DE PROCESOS SIN ORDENAR:\n")
        imprimirListaProcesos(listaDeProcesos)

        // Ordenar la lista de procesos
        val listaProcesosOrdenada = (listaDeProcesos).also { ordenarListaProcesos(it) }

        println("\nLISTA DE PROCESOS ORDENADA\n")
        imprimirListaProcesos(listaProcesosOrdenada)

        // Comienzo de la ejecucion
        // Comprobar que el proceso no se encuentra bloqueado
        if (listaProcesosOrdenada[0].getEstado() == Proceso.EstadoDeProceso.BLOQUEADO) {
            println("Proceso bloqueado")

            // Cambio de estado para continuar su ejecucion
            listaProcesosOrdenada[0].setEstado(Proceso.EstadoDeProceso.LISTO)
        }

        // Variable para almacenar el progreso de los tiempos de cada proceso del algoritmo
        val infoTiempos = mutableListOf(
            InfoGraficoGantt(
                pid = (-1).toString(),
                tiempoEjecucion = listaProcesosOrdenada[0].getLlegada(),
                tiempoEjecutado = 0
            )
        )

        // Variable para almacenar el avance del tiempo con cada proceso
        var tiempoActual = listaProcesosOrdenada[0].getDuracion()

        // Iniciamos los tiempos de control
        listaProcesosOrdenada[0].setTiempoRespuesta(0)
        listaProcesosOrdenada[0].setTiempoRestante(0)
        listaProcesosOrdenada[0].setTiempoEspera(0)

        // Continuacion de la ejecucion agregando los tiempos del primer proceso en CPU
        infoTiempos += InfoGraficoGantt(
            pid = listaProcesosOrdenada[0].getNombre(),
            tiempoEjecucion = tiempoActual,
            tiempoEjecutado = listaProcesosOrdenada[0].getDuracion()
        )

        // Iteraciones para recorrer todos los procesos restantes despues del primero
        for (proceso in 1 until listaProcesosOrdenada.size) {
            // Comprobar que el proceso no se encuentra bloqueado
            if (listaProcesosOrdenada[proceso].getEstado() == Proceso.EstadoDeProceso.BLOQUEADO) {
                println("Proceso bloqueado")

                // Cambio de estado para continuar su ejecucion
                listaProcesosOrdenada[proceso].setEstado(Proceso.EstadoDeProceso.LISTO)
            }

            // Actualizar el estado del proceso a en EJECUCION
            listaProcesosOrdenada[proceso].setEstado(Proceso.EstadoDeProceso.EJECUCION)

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
                    tiempoEjecucion = tiempoActual,
                    tiempoEjecutado = listaProcesosOrdenada[proceso].getDuracion()
                )
            )

            // Actualizamos el estado a proceso COMPLETADO
            listaProcesosOrdenada[proceso].setEstado(Proceso.EstadoDeProceso.COMPLETADO)
        }

        // Devolver la variable de informacion de los tiempos
        return infoTiempos
    }
}