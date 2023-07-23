package com.i72pehej.cpuschedulerapp.usecases.algorithms

import com.i72pehej.cpuschedulerapp.util.classes.InfoGraficoEstados
import com.i72pehej.cpuschedulerapp.util.classes.Proceso
import com.i72pehej.cpuschedulerapp.util.classes.crearProceso
import com.i72pehej.cpuschedulerapp.util.classes.ordenarListaProcesos
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
fun algoritmoFifo(): MutableList<InfoGraficoEstados> {
    // Ordenar la lista de procesos por tiempo de llegada
    ordenarListaProcesos(listaDeProcesosGlobal)

    // Comienzo de la ejecucion

    // Variable para almacenar el progreso de los ESTADOS de cada proceso durante el algoritmo
    val infoEstados = mutableListOf(InfoGraficoEstados(proceso = crearProceso(nombre = "-", tiempoLlegada = 0, duracion = 0, estado = Proceso.EstadoDeProceso.LISTO), momento = 0))

    // Creacion de la cola de procesos LISTOS con el primer proceso
    val colaDeListos = listaDeProcesosGlobal.toMutableList()

    // Variable que almacena el primer elemento de la cola para inicializar los tiempos
    var cabezaDeCola = colaDeListos.first()

    // Variable para almacenar el avance del tiempo con cada proceso
    var tiempoActual = cabezaDeCola.getLlegada()

    // COMIENZA EL BUCLE
    for (indexProcesoActual in 0 until colaDeListos.size) {
        // Se almacena el primer elemento de la cola (el siguiente proceso a entrar en CPU)
        cabezaDeCola = colaDeListos.first()

        // Calculo de la diferencia entre el momento actual y la llegada del proceso para el tiempo de espera
        val diffTiempos = tiempoActual - cabezaDeCola.getLlegada()
        cabezaDeCola.setTiempoEspera(if (diffTiempos > 0) diffTiempos else 0)

        // Actualizamos su estado a proceso EJECUCION y lo guardamos
        cabezaDeCola.setEstado(Proceso.EstadoDeProceso.EJECUCION)
        infoEstados.add(InfoGraficoEstados(proceso = crearProceso(nombre = cabezaDeCola.getNombre(), tiempoLlegada = cabezaDeCola.getLlegada(), duracion = cabezaDeCola.getDuracion(), estado = Proceso.EstadoDeProceso.EJECUCION), momento = tiempoActual))

        // En caso de que el proceso tenga un evento de E/S, se ejecuta primero hasta el comienzo del evento...
        if (cabezaDeCola.getTiempoEntrada() >= 0) {
            // En caso de habernos pasado el evento de E/S, controlamos que el evento se ejecute en el momento en el que el proceso esta en CPU
            if (tiempoActual > cabezaDeCola.getTiempoEntrada()) {
                // Actualizamos su estado a proceso BLOQUEADO y lo guardamos
                cabezaDeCola.setEstado(Proceso.EstadoDeProceso.BLOQUEADO)
                infoEstados.add(InfoGraficoEstados(proceso = crearProceso(nombre = cabezaDeCola.getNombre(), tiempoLlegada = cabezaDeCola.getLlegada(), duracion = cabezaDeCola.getDuracion(), estado = Proceso.EstadoDeProceso.BLOQUEADO), momento = tiempoActual))

                // Avanzamos el momento actual hasta el final del evento de E/S ()
                tiempoActual += cabezaDeCola.getTiempoDeEsperaES()

                // Se guarda el tiempo de espera
                cabezaDeCola.setTiempoEspera(cabezaDeCola.getTiempoEspera() + cabezaDeCola.getTiempoDeEsperaES())

                // Actualizamos su estado a proceso EJECUCION y lo guardamos
                cabezaDeCola.setEstado(Proceso.EstadoDeProceso.EJECUCION)
                infoEstados.add(InfoGraficoEstados(proceso = crearProceso(nombre = cabezaDeCola.getNombre(), tiempoLlegada = cabezaDeCola.getLlegada(), duracion = cabezaDeCola.getDuracion(), estado = Proceso.EstadoDeProceso.EJECUCION), momento = tiempoActual))
            }

            // sino, el proceso se ejecutara hasta llegar al evento E/S y despues continuara
            else {
                // Se ejecuta el proceso hasta llegar al momento de entrada del evento E/S
                val tiempoAvanzadoHastaEntrada = cabezaDeCola.getTiempoEntrada() - tiempoActual
                val tRestante = if ((cabezaDeCola.getDuracion() - tiempoAvanzadoHastaEntrada) > 0) cabezaDeCola.getDuracion() - tiempoAvanzadoHastaEntrada else 0
                cabezaDeCola.setTiempoRestante(tRestante)

                // En caso de terminar su tiempo de CPU antes de llegar al evento de E/S, FIFO considera que no se puede ejecutar el evento
                if (tRestante == 0) {
                    // Se agrega la duracion completa del proceso al tiempo actual
                    tiempoActual += cabezaDeCola.getDuracion()
                } else {
                    // Avanza el tiempo hasta entrar en el evento de E/S
                    tiempoActual = cabezaDeCola.getTiempoEntrada()

                    // Actualizamos su estado a proceso BLOQUEADO y lo guardamos
                    cabezaDeCola.setEstado(Proceso.EstadoDeProceso.BLOQUEADO)
                    infoEstados.add(InfoGraficoEstados(proceso = crearProceso(nombre = cabezaDeCola.getNombre(), tiempoLlegada = cabezaDeCola.getLlegada(), duracion = cabezaDeCola.getDuracion(), estado = Proceso.EstadoDeProceso.BLOQUEADO), momento = tiempoActual))

                    // Se guarda el tiempo de espera
                    cabezaDeCola.setTiempoEspera(cabezaDeCola.getTiempoEspera() + cabezaDeCola.getTiempoDeEsperaES())

                    // Avanzamos el momento actual hasta el final del evento de E/S
                    tiempoActual += cabezaDeCola.getTiempoDeEsperaES()

                    // Actualizamos su estado a proceso LISTO y lo guardamos
                    cabezaDeCola.setEstado(Proceso.EstadoDeProceso.EJECUCION)
                    infoEstados.add(InfoGraficoEstados(proceso = crearProceso(nombre = cabezaDeCola.getNombre(), tiempoLlegada = cabezaDeCola.getLlegada(), duracion = cabezaDeCola.getDuracion(), estado = Proceso.EstadoDeProceso.EJECUCION), momento = tiempoActual))
                }
            }
        }

        // ...sino, consideramos que el proceso completa su ejecucion
        tiempoActual += cabezaDeCola.getTiempoRestante()

        // Actualizamos su estado a proceso COMPLETADO y lo guardamos
        cabezaDeCola.setEstado(Proceso.EstadoDeProceso.COMPLETADO)
        infoEstados.add(InfoGraficoEstados(proceso = crearProceso(nombre = cabezaDeCola.getNombre(), tiempoLlegada = cabezaDeCola.getLlegada(), duracion = cabezaDeCola.getDuracion(), estado = Proceso.EstadoDeProceso.COMPLETADO), momento = tiempoActual))

        // Actualizamos el proceso en la lista global
        listaDeProcesosGlobal[indexProcesoActual] = cabezaDeCola

        // Eliminamos de la cola el proceso terminado
        colaDeListos.removeAt(0)

    }

    // Limpiamos el primer elemento utilizado de base para poder operar
    infoEstados.removeAt(0)

    println(infoEstados.forEach { println("PROCESO ${it.getProceso().getNombre()} -> ${it.getProceso().getEstado()}, MOMENTO -> ${it.getMomento()}") })

    // Devolver la variable de informacion de los tiempos
    return infoEstados
}