package com.i72pehej.cpuschedulerapp.util.extensions

import com.i72pehej.cpuschedulerapp.util.Proceso.EstadoDeProceso

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===========================================================================================
 */

/**
 * Informacion de los tiempos que tiene cada proceso para el procesamiento del diagrama de Gantt
 *
 * @property pid Nombre o ID del proceso
 * @property tiempoEjecucion El momento en el que el proceso deja de ejecutarse
 * @property tiempoEjecutado El tiempo que lleva ejecutado
 */
data class InfoGraficoGantt(
    val pid: String,
    val tiempoEjecucion: Int,
    val tiempoEjecutado: Int,
)

/**
 * ===========================================================================================
 */

/**
 * Informacion del estado de un proceso en un momento determinado
 *
 * @property pid Nombre o ID del proceso
 * @property momento Tiempo en el que el proceso adquiere un estado distinto al anterior
 * @property estado Nuevo estado que adquiere el proceso
 */
data class InfoGraficoEstados(
    val pid: String,
    val momento: Int,
    val estado: EstadoDeProceso
)

/**
 * ===========================================================================================
 */

/**
 * Contenedor de las listas con la informacion de tiempos y estados de los procesos
 *
 * @property listaTiemposFinal Lista con la informacion de los tiempos de los procesos
 * @property listaEstadosFinal Lista con la informacion de los estados de los procesos
 */
data class InfoResultsFinalData(
    val listaTiemposFinal: List<InfoGraficoGantt>,
    val listaEstadosFinal: List<InfoGraficoEstados>
)