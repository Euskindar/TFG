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
fun algoritmoFifo(): MutableList<InfoGraficoEstados> {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarListaProcesos(listaDeProcesosGlobal)

    // Comienzo de la ejecucion

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf(InfoGraficoEstados(proceso = crearProceso(nombre = "-", tiempoLlegada = 0, duracion = 0), momento = 0))

    // Creacion de la cola de procesos LISTOS con el primer proceso y obtencion de la cabeza
    val colaDeListos: MutableList<Proceso> = mutableListOf(listaDeProcesosGlobal.first())
    val cabezaDeCola = colaDeListos.first()

    // Variable para almacenar el avance del tiempo con cada proceso
    var tiempoActual = cabezaDeCola.getLlegada()

    // Actualizamos su estado a proceso EJECUCION y lo guardamos
    cabezaDeCola.setEstado(Proceso.EstadoDeProceso.EJECUCION)
    infoEstados.add(InfoGraficoEstados(proceso = cabezaDeCola, momento = tiempoActual))

    // En caso de que el proceso tenga un evento de E/S, se ejecuta primero hasta el comienzo del evento...
    if (cabezaDeCola.getTiempoEntrada() >= 0) {
        // Avanza el tiempo hasta entrar en el evento de E/S
        tiempoActual = cabezaDeCola.getTiempoEntrada()

        // Actualizamos su estado a proceso BLOQUEADO y lo guardamos
        cabezaDeCola.setEstado(Proceso.EstadoDeProceso.BLOQUEADO)
        infoEstados.add(InfoGraficoEstados(proceso = cabezaDeCola, momento = tiempoActual))

        // Se guarda el tiempo
        cabezaDeCola.setTiempoEspera(cabezaDeCola.getTiempoSalida() - cabezaDeCola.getTiempoEntrada())

        // Avanzamos el tiempo hasta finalizar el evento de E/S
        tiempoActual = cabezaDeCola.getTiempoSalida()

        // Actualizamos su estado a proceso LISTO y lo guardamos
        cabezaDeCola.setEstado(Proceso.EstadoDeProceso.EJECUCION)
        infoEstados.add(InfoGraficoEstados(proceso = cabezaDeCola, momento = tiempoActual))
    }

    // ...sino, se considera que el proceso se ha ejecutado al completo
    tiempoActual = cabezaDeCola.getDuracion()

    // Actualizamos su estado a proceso COMPLETADO y lo guardamos
    cabezaDeCola.setEstado(Proceso.EstadoDeProceso.COMPLETADO)
    infoEstados.add(InfoGraficoEstados(proceso = cabezaDeCola, momento = tiempoActual))





    // Iteraciones para recorrer todos los procesos restantes
    for (procesoActual in 1 until listaDeProcesosGlobal.size) {
        // Actualizar el estado del proceso a en EJECUCION
        listaDeProcesosGlobal[procesoActual].setEstado(Proceso.EstadoDeProceso.EJECUCION)

        // Calculo de la diferencia entre el momento actual y la llegada del proceso para el tiempo de espera
        val diffTiempos = tiempoActual - listaDeProcesosGlobal[procesoActual].getLlegada()
        listaDeProcesosGlobal[procesoActual].setTiempoEspera(if (diffTiempos > 0) diffTiempos else 0)

        // Guardado del cambio de estado
        infoEstados.add(InfoGraficoEstados(proceso = listaDeProcesosGlobal[procesoActual], momento = tiempoActual))

        // Avanzamos el momento actual hasta finalizar el proceso
        tiempoActual += listaDeProcesosGlobal[procesoActual].getDuracion()

        // Actualizamos el estado a proceso COMPLETADO
        listaDeProcesosGlobal[procesoActual].setEstado(Proceso.EstadoDeProceso.COMPLETADO)

        // Guardado del cambio de estado
        infoEstados.add(InfoGraficoEstados(proceso = listaDeProcesosGlobal[procesoActual], momento = tiempoActual))
    }

    // Limpiamos el primer elemento utilizado de base para poder operar
    infoEstados.removeAt(0)

    // Devolver la variable de informacion de los tiempos
    return infoEstados
}