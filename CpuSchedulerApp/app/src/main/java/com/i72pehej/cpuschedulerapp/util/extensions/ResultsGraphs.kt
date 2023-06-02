package com.i72pehej.cpuschedulerapp.util.extensions

import com.i72pehej.cpuschedulerapp.util.Proceso

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===========================================================================================
 */

/**
 * Informacion del estado de un proceso en un momento determinado
 *
 * @property proceso Proceso al que se le asocia el resto de informacion de estados
 * @property momento Tiempo en el que el proceso adquiere un estado distinto al anterior
// * @property espera Tiempo de espera del proceso desde que llega hasta que comienza su ejecucion
// * @property estado Nuevo estado que adquiere el proceso
 */
data class InfoGraficoEstados(
    val proceso: Proceso,
    val momento: Int
)