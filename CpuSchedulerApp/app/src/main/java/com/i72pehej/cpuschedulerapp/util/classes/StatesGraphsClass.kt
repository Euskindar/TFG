package com.i72pehej.cpuschedulerapp.util.classes

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===========================================================================================
 */

/**
 * Informacion del estado de un proceso en un momento determinado
 *
 * @property proceso El proceso asociado al momento del cambio de estado
 * @property momento Tiempo en el que el proceso adquiere un estado distinto al anterior
 */
data class InfoGraficoEstados(
    private var proceso: Proceso,
    private var momento: Int
) {
    // Getters y Setters
    fun getProceso(): Proceso {
        return this.proceso
    }

//    fun setProceso(proceso: Proceso) {
//        this.proceso = proceso
//    }

    fun getMomento(): Int {
        return this.momento
    }

//    fun setMomento(momento: Int) {
//        this.momento = momento
//    }
}