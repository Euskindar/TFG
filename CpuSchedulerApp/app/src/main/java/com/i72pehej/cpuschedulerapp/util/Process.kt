package com.i72pehej.cpuschedulerapp.util

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

/**
 * Clase que representa cada uno de los procesos
 *
 * @property nombre Nombre asignado al proceso
 * @property tiempoLlegada Momento en el que el proceso entra en la cola de procesos listos
 * @property duracion Duracion estimada del proceso
 * @constructor Crea la representacion de un Proceso de CPU
 */
data class Proceso(
    private var nombre: String,
    private var tiempoLlegada: Int,
    private var duracion: Int,
) {
    // Estado actual del proceso, representado por la clase enumerada
    private var estado: EstadoDeProceso = EstadoDeProceso.LISTO

    // Listado de estados asociados a los procesos
    enum class EstadoDeProceso {
        LISTO,      // Cuando el proceso se encuentra a la espera de entrar en la CPU
        EJECUCION,  // Cuando el proceso se encuentra en la CPU
        BLOQUEADO,  // Cuando el proceso queda temporalmente detenido por otras tareas
        COMPLETADO  // Cuando el proceso ha terminado su ejecucion
    }

    // Setters y getters de los parametros
    fun getNombre(): String {
        return this.nombre
    }

    fun getLlegada(): Int {
        return this.tiempoLlegada
    }

    fun getDuracion(): Int {
        return this.duracion
    }

    fun getEstado(): EstadoDeProceso {
        return this.estado
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun setLlegada(tiempo: Int) {
        this.tiempoLlegada = tiempo
    }

    fun setDuracion(tiempo: Int) {
        this.duracion = tiempo
    }

    fun setEstado(estado: EstadoDeProceso) {
        this.estado = estado
    }

    // Control de los tiempos del proceso

    private var tiempoRespuesta: Int = 0
    fun getTiempoRespuesta(): Int {
        return this.tiempoRespuesta
    }

    fun setTiempoRespuesta(tiempo: Int) {
        this.tiempoRespuesta = tiempo
    }

    private var tiempoRestante: Int = this.getDuracion()
    fun getTiempoRestante(): Int {
        return this.tiempoRestante
    }

    fun setTiempoRestante(tiempo: Int) {
        this.tiempoRestante = tiempo
    }

    fun tiempoEstancia() = this.tiempoFin() - this.getLlegada()

    private var tiempoEspera: Int = 0
    fun getTiempoEspera(): Int {
        return this.tiempoEspera
    }

    fun setTiempoEspera(tiempo: Int) {
        this.tiempoEspera = tiempo
    }

    fun tiempoFin() =
        this.getLlegada() + this.getDuracion() + this.getTiempoEspera() // Tiempo que ha tardado el proceso en completarse desde su llegada

    fun tiempoInicioFIFO() =
        this.tiempoFin() - this.getDuracion()  // Tiempo en el que el proceso inicia su ejecucion en el algoritmo FIFO

    fun tiempoDeCompletado() =
        this.getDuracion() + this.getTiempoEspera() // Tiempo que ha tardado en completarse tras entrar a la CPU

    fun isFinished() = this.getTiempoRestante() == 0 // Comprobador de proceso finalizado

    fun reset() {   // Limpieza de tiempos de control
        setTiempoRestante(this.getDuracion())
        setTiempoEspera(0)
    }
}
/**
 * ===================================================================
 */

/**
 * Funcion para crear y agregar un proceso nuevo a la lista de procesos
 *
 * @property nombre Nombre asignado al proceso
 * @property tiempoLlegada Momento en el que el proceso entra en la cola de procesos listos
 * @property duracion Duracion estimada del proceso
 */
fun crearProceso(
    nombre: String,
    tiempoLlegada: Int,
    duracion: Int,
): Proceso {
    // Se crea un proceso y se devuelve
    return Proceso(nombre, tiempoLlegada, duracion)
}

/**
 * ===================================================================
 */

/**
 * Funcion que ordena la lista de procesos por orden de llegada de forma ascendente
 */
fun ordenarListaProcesos(listaDeProcesos: MutableList<Proceso>): List<Proceso> {
    listaDeProcesos.sortBy { proceso: Proceso -> proceso.getLlegada() }

    return listaDeProcesos
}

/**
 * ===================================================================
 */

/**
 * Imprimir lista de procesos por terminal
 */
fun imprimirListaProcesos(listaDeProcesos: MutableList<Proceso>) {
    println("IMPRIMIENDO LISTADO DE PROCESOS . . .")
    listaDeProcesos.forEach { proceso ->
        println("NOMBRE: ${proceso.getNombre()}, LLEGADA: ${proceso.getLlegada()}, DURACION: ${proceso.getDuracion()}, ESTADO: ${proceso.getEstado()}")
    }
}

/**
 * Imprimir proceso por terminal
 */
fun imprimirProceso(proceso: Proceso) {
    println("IMPRIMIENDO PROCESO . . .")
    println("NOMBRE: ${proceso.getNombre()}, LLEGADA: ${proceso.getLlegada()}, DURACION: ${proceso.getDuracion()}, ESTADO: ${proceso.getEstado()}")
}