package com.i72pehej.cpuschedulerapp.usecases.home

import com.i72pehej.cpuschedulerapp.util.Proceso
import com.i72pehej.cpuschedulerapp.util.imprimirListaProcesos
import com.i72pehej.cpuschedulerapp.util.imprimirProceso
import com.i72pehej.cpuschedulerapp.util.ordenarListaProcesos

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
fun algoritmoFifo(listaDeProcesos: MutableList<Proceso>) {
    // Comprobacion inicial para que la lista de procesos no este vacia
    if (listaDeProcesos.isNotEmpty()) {
        println("\nAlgoritmo FIFO seleccionado\n")

        println("\nLISTA DE PROCESOS SIN ORDENAR\n")
        imprimirListaProcesos(listaDeProcesos)

        val listaProcesosOrdenada = (listaDeProcesos).also { ordenarListaProcesos(it) }

        println("\nLISTA DE PROCESOS ORDENADA\n")
        imprimirListaProcesos(listaProcesosOrdenada)

        for (proceso in listaProcesosOrdenada) {
            // Comprobar que el proceso no se encuentra bloqueado o algo asi antes de nada
            if (proceso.estado == Proceso.EstadoDeProceso.BLOQUEADO) {
                println("Proceso bloqueado")
            }

            // Comprobar que el proceso esta listo para su ejecucion
            if (proceso.estado != Proceso.EstadoDeProceso.LISTO) {
                println("PROCESO NO LISTO")
            } else {
                // Actualizar el estado del proceso a "Corriendo"
                proceso.estado = Proceso.EstadoDeProceso.CORRIENDO

                // Simular la ejecucion del proceso
                for (time in 1..proceso.duracion) {
                    // Aqui puedes realizar las operaciones necesarias durante la ejecucion del proceso


                    // Actualizar el estado del proceso a "Esperando"
                    proceso.estado = Proceso.EstadoDeProceso.ESPERANDO
                }

                // Actualizar el estado del proceso a "Completado"
                proceso.estado = Proceso.EstadoDeProceso.COMPLETADO
                imprimirProceso(proceso)
            }
        }
    } else {
        // Creacion del mensaje

    }
}