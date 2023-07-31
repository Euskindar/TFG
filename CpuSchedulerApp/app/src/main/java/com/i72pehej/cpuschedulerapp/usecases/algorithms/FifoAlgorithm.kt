package com.i72pehej.cpuschedulerapp.usecases.algorithms

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.i72pehej.cpuschedulerapp.util.classes.InfoGraficoEstados
import com.i72pehej.cpuschedulerapp.util.classes.Proceso
import com.i72pehej.cpuschedulerapp.util.classes.Proceso.EstadoDeProceso.BLOQUEADO
import com.i72pehej.cpuschedulerapp.util.classes.Proceso.EstadoDeProceso.COMPLETADO
import com.i72pehej.cpuschedulerapp.util.classes.Proceso.EstadoDeProceso.EJECUCION
import com.i72pehej.cpuschedulerapp.util.classes.Proceso.EstadoDeProceso.LISTO
import com.i72pehej.cpuschedulerapp.util.classes.crearProceso
import com.i72pehej.cpuschedulerapp.util.classes.ordenarLlegadaProcesos

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

/**
 * Funcion para implementar el algoritmo FIFO considerando estados
 *
 * @param listaProcesos Listado de prodesos con los que realizar el algoritmo FIFO
 *
 * @return Devuelve el listado de estados en cada momento para cada proceso de la lista
 */
fun algoritmoFifo(listaProcesos: SnapshotStateList<Proceso>): MutableList<InfoGraficoEstados> {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarLlegadaProcesos(listaProcesos)

    // Funcion para realizar una copia independiente del listado de procesos
    fun copiarLista(listaProcesosOriginal: SnapshotStateList<Proceso>): MutableList<Proceso> {
        val nuevaLista = mutableListOf<Proceso>()

        listaProcesosOriginal.forEach { elemento ->
            nuevaLista.add(
                crearProceso(
                    nombre = elemento.getNombre(),
                    tiempoLlegada = elemento.getLlegada(),
                    duracion = elemento.getDuracion(),
                    estado = elemento.getEstado(),
                    tiempoEntrada = elemento.getTiempoEntrada(),
                    tiempoSalida = elemento.getTiempoSalida()
                )
            )
        }

        return nuevaLista
    }

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf<InfoGraficoEstados>()

    // Creacion de la cola de procesos LISTOS
    val colaDeListos = copiarLista(listaProcesos)

    // Variable para almacenar el avance del tiempo con cada proceso
    var momentoActual = colaDeListos.first().getLlegada()

    // Consideramos que se deba completar el ultimo proceso como condicion de parada del bucle
    while (colaDeListos.isNotEmpty()) {
        // Variable que almacena el primer elemento de la cola
        val cabezaDeCola = colaDeListos.first()

        // Comprobamos que el proceso tenga evento de E/S
        if ((cabezaDeCola.getTiempoEntrada() > 0) && (cabezaDeCola.getTiempoEntrada() == momentoActual)) {
            // Bucle para almacenar los tiempos de bloqueo por E/S del proceso
            for (tiempos in 0 until cabezaDeCola.getTiempoDeEsperaES()) {
                // Guardamos el estado BLOQUEADO
                infoEstados.add(InfoGraficoEstados(nombre = cabezaDeCola.getNombre(), estado = BLOQUEADO, momento = momentoActual + tiempos))
            }

            // Actualizar la llegada del proceso de vuelta del estado de BLOQUEO == salida del evento de E/S para retomarlo desde ese punto
            cabezaDeCola.setLlegada(cabezaDeCola.getTiempoSalida())

            // Movemos el proceso al final de la lista de LISTOS
            colaDeListos.add(cabezaDeCola)
            colaDeListos.removeAt(0)

            // Reordenamos la cola por tiempo de llegada para considerar que el evento E/S termine antes de que haya llegado el siguiente proceso a la cola
            colaDeListos.sortBy { it.getLlegada() }

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
                colaDeListos.removeAt(0)

                // Actualizamos el momentoActual a la llegada del siguiente proceso (-1 para considerar el aumento del bucle)
                val llegadaCabeza = if (colaDeListos.firstOrNull() == null) 0 else colaDeListos.first().getLlegada() - 1
                momentoActual = llegadaCabeza
            }
        }

        // Avanzamos el tiempo
        momentoActual++
    }

    // Almacenamos la variable de informacion de los tiempos
    return infoEstados
}