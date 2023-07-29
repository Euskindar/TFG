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
 * @property nombre El nombre del proceso asociado al momento del cambio de estado
 * @property estado Estado que pasa a tener el proceso en el momento actual
 * @property momento Tiempo en el que el proceso adquiere un estado distinto al anterior
 */
data class InfoGraficoEstados(
    private var nombre: String,
    private var estado: Proceso.EstadoDeProceso,
    private var momento: Int
) {
    // Getters
    fun getNombre(): String {
        return this.nombre
    }

    fun getEstado(): Proceso.EstadoDeProceso {
        return this.estado
    }

    fun getMomento(): Int {
        return this.momento
    }
}