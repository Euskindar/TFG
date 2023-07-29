package com.i72pehej.cpuschedulerapp.usecases.algorithms

import com.i72pehej.cpuschedulerapp.util.classes.InfoGraficoEstados
import com.i72pehej.cpuschedulerapp.util.classes.Proceso.EstadoDeProceso.BLOQUEADO
import com.i72pehej.cpuschedulerapp.util.classes.Proceso.EstadoDeProceso.COMPLETADO
import com.i72pehej.cpuschedulerapp.util.classes.Proceso.EstadoDeProceso.EJECUCION
import com.i72pehej.cpuschedulerapp.util.classes.Proceso.EstadoDeProceso.LISTO
import com.i72pehej.cpuschedulerapp.util.classes.ordenarListaProcesos
import com.i72pehej.cpuschedulerapp.util.infoResultadosGlobal
import com.i72pehej.cpuschedulerapp.util.listaDeProcesosGlobal

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

/**
 * Funcion para implementar el algoritmo FIFO considerando estados
 */
fun algoritmoFifo() {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarListaProcesos(listaDeProcesosGlobal)

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf(InfoGraficoEstados(nombre = "", estado = LISTO, momento = 0))

    // Creacion de la cola de procesos LISTOS
    val colaDeListos = listaDeProcesosGlobal.toMutableList()

    // Variable que almacena el primer elemento de la cola para inicializar los tiempos
    var cabezaDeCola = colaDeListos.first()

    // Variable para almacenar el avance del tiempo con cada proceso
    var momentoActual = cabezaDeCola.getLlegada()

    // Consideramos que se deba completar el ultimo proceso como condicion de parada del bucle
    while (listaDeProcesosGlobal.last().getEstado() != COMPLETADO) {
        cabezaDeCola = colaDeListos.first()

        // Comprobamos que el proceso tenga evento de E/S
        if ((cabezaDeCola.getTiempoEntrada() > 0) && (cabezaDeCola.getTiempoEntrada() == momentoActual)) {

            // Bucle para almacenar los tiempos de bloqueo por E/S del proceso
            for (tiempos in 0 until cabezaDeCola.getTiempoDeEsperaES()) {
                // Guardamos el estado BLOQUEADO
                infoEstados.add(InfoGraficoEstados(nombre = cabezaDeCola.getNombre(), estado = BLOQUEADO, momento = momentoActual + tiempos))
            }

            // Modificamos la llegada del proceso que sale y se coloca al final de la cola para poder retomarlo desde su salida de E/S
            cabezaDeCola.setLlegada(cabezaDeCola.getTiempoSalida())

            // Movemos el proceso al final de la lista de LISTOS
            colaDeListos.add(cabezaDeCola)
            colaDeListos.removeFirst()

            // Actualizamos el momentoActual a la llegada del siguiente proceso (-1 para considerar el aumento del bucle)
            momentoActual = colaDeListos.first().getLlegada() - 1
        }
        // Si no hay evento de bloqueo de proceso...
        else {
            // Comprobamos que le quede tiempo restante al proceso
            if (cabezaDeCola.getTiempoRestante() > 0) {
                // Buscamos algun proceso que este en ejecucion en este momento
                val procesoEnEjecucion = infoEstados.any { ((it.getEstado() == EJECUCION) && (it.getMomento() == momentoActual)) }

                // Si NO hay ningun proceso en EJECUCION...
                if (!procesoEnEjecucion) {
                    // Pasamos a EJECUCION
                    infoEstados.add(InfoGraficoEstados(nombre = cabezaDeCola.getNombre(), estado = EJECUCION, momento = momentoActual))

                    // Restamos una unidad al tiempoRestante
                    cabezaDeCola.setTiempoRestante(cabezaDeCola.getTiempoRestante() - 1)
                }
                // Si hay otro proceso ejecutandose...
                else {
                    // Pasamos a LISTO == ESPERA
                    infoEstados.add(InfoGraficoEstados(nombre = cabezaDeCola.getNombre(), estado = LISTO, momento = momentoActual))
                }
            }
            // Si no le queda tiempo restante
            else {
                // Pasamos a COMPLETADO
                infoEstados.add(InfoGraficoEstados(nombre = cabezaDeCola.getNombre(), estado = COMPLETADO, momento = momentoActual))

                // Actualizamos el estado final del proceso
                cabezaDeCola.setEstado(COMPLETADO)

                // Eliminamos el proceso de la cola
                colaDeListos.removeFirst()

                // Actualizamos el momentoActual a la llegada del siguiente proceso (-1 para considerar el aumento del bucle)
                val llegadaCabeza = if (colaDeListos.firstOrNull() == null) 0 else colaDeListos.first().getLlegada() - 1
                momentoActual = llegadaCabeza
            }
        }

        // Avanzamos el tiempo
        momentoActual++
    }

    // Limpiamos el primer elemento utilizado de base para poder operar
    infoEstados.removeAt(0)

    // Almacenamos la variable de informacion de los tiempos
    infoResultadosGlobal = infoEstados
}