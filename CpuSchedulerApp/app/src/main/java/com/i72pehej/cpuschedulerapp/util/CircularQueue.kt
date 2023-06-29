package com.i72pehej.cpuschedulerapp.util

/**
 * @author Julen Perez Hernandez
 */


/**
 * Clase que crea una cola de elementos
 *
 * @param T Tipo generico para considerar cualquier tipo de elemento
 * @property capacidad Cantidad de elementos de la cola
 */
class CircularQueue<T>(private val capacidad: Int) {
    // Array del tipo especificado relleno con nulls
    private val cola = arrayOfNulls<Any?>(capacidad)

    private var cabeza = -1  // Valor de cabeza
    private var final = -1   // Valor de final

    // SISTEMA DE VOTACION PARA SELECCIONAR EL PROXIMO ELEMENTO CON EL QUE TRABAJAR
    // Comprueba la cola y establece los valores de cabeza y final. Devuelve el elementos de la cabeza
    @Throws(NoSuchElementException::class)
    fun votacion(): T {
        // Control de errores
        if (isEmpty()) throw NoSuchElementException("Cola vacia")

        // Variable para guardar la cabeza de la cola
        val item = cola[cabeza] as T

        // Eliminamos la cabeza
        cola[cabeza] = null

        // Reseteamos valores iniciales de cabeza y final si son iguales
        if (cabeza == final) {
            cabeza = -1
            final = -1
        }
        // Sino, reasignamos el valor de cabeza
        else {
            cabeza = (cabeza + 1) % capacidad
        }

        // Devuelve el elemento de la cabeza
        return item
    }

    // Agrega elementos a la cola en caso de que tenga espacio para ser introducido
    @Throws(IllegalStateException::class)
    fun add(item: T) {
        // Control de espacio
        if (isFull()) throw IllegalStateException("Cola llena")
        if (isEmpty()) cabeza = 0

        // Reasignamos los valores de espacio
        final = (final + 1) % capacidad
        cola[final] = item
    }

    // Funcionalidades para simplificar comprobaciones
    private fun isEmpty(): Boolean = cabeza == -1
    private fun isFull(): Boolean = (final + 1) % capacidad == cabeza
    fun isNotEmpty(): Boolean = !isEmpty()
}