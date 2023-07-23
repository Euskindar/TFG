package com.i72pehej.cpuschedulerapp.util.classes

import com.i72pehej.cpuschedulerapp.util.infoResultadosGlobal

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
    private var estado: EstadoDeProceso = EstadoDeProceso.LISTO
) {
    // Listado de estados asociados a los procesos
    enum class EstadoDeProceso {
        LISTO,      // Cuando el proceso se encuentra a la espera de entrar en la CPU
        EJECUCION,  // Cuando el proceso se encuentra en la CPU
        BLOQUEADO,  // Cuando el proceso queda temporalmente detenido por otras tareas
        COMPLETADO  // Cuando el proceso ha terminado su ejecucion
    }

    // Variables para considerar tiempos de Inicio y Fin de un evento de E/S del proceso
    private var tiempoEntrada = -1
    private var tiempoSalida = -1

    // Setters y getters de los parametros
    fun getNombre(): String {
        return this.nombre
    }

//    fun setNombre(nombre: String) {
//        this.nombre = nombre
//    }

    fun getLlegada(): Int {
        return this.tiempoLlegada
    }

//    fun setLlegada(tiempo: Int) {
//        this.tiempoLlegada = tiempo
//    }

    fun getEstado(): EstadoDeProceso {
        return this.estado
    }

    fun setEstado(estado: EstadoDeProceso) {
        this.estado = estado
    }

    fun getDuracion(): Int {
        return this.duracion
    }

//    fun setDuracion(tiempo: Int) {
//        this.duracion = tiempo
//    }

    fun getTiempoEntrada(): Int {
        return this.tiempoEntrada
    }

    fun setTiempoEntrada(tiempo: Int) {
        this.tiempoEntrada = tiempo
    }

    fun getTiempoSalida(): Int {
        return this.tiempoSalida
    }

    fun setTiempoSalida(tiempo: Int) {
        this.tiempoSalida = tiempo
    }

    fun getTiempoDeEsperaES(): Int {
        return this.getTiempoSalida() - this.getTiempoEntrada()
    }

    // Control de los tiempos del proceso

//    private var tiempoRespuesta: Int = 0
//    fun getTiempoRespuesta(): Int {
//        return this.tiempoRespuesta
//    }

//    fun setTiempoRespuesta(tiempo: Int) {
//        this.tiempoRespuesta = tiempo
//    }

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

    // Tiempo que ha tardado el proceso en completarse desde su llegada
    fun tiempoFin() = this.getLlegada() + this.getDuracion() + this.getTiempoEspera()

    // Tiempo en el que el proceso inicia su ejecucion
    fun tiempoInicio(): Int {
        // Se busca en la lista de estados la primera aparicion del proceso, correspondiente con el estado de EJECUCION, sino devuelve -1 como "error"
        return infoResultadosGlobal.find { it.getProceso().getEstado() == EstadoDeProceso.EJECUCION }?.getMomento() ?: -1
    }

    // Tiempo que ha tardado en completarse tras entrar a la CPU
//    fun tiempoDeCompletado() = this.getDuracion() + this.getTiempoEspera()

    // Comprobador de proceso finalizado
//    fun isFinished() = this.getTiempoRestante() == 0

    // Limpieza de tiempos de control
//    fun reset() {
//        setTiempoRestante(this.getDuracion())
//        setTiempoEspera(0)
//    }
}
/**
 * ===================================================================
 */

/**
 * Funcion para crear un proceso nuevo a la lista de procesos
 *
 * @param nombre Nombre asignado al proceso
 * @param tiempoLlegada Momento en el que el proceso entra en la cola de procesos listos
 * @param duracion Duracion estimada del proceso
 *
 * @return Devuelve un proceso con los valores correspondientes
 */
fun crearProceso(
    nombre: String,
    tiempoLlegada: Int,
    duracion: Int,
    estado: Proceso.EstadoDeProceso
): Proceso {
    // Se crea un proceso y se devuelve
    return Proceso(nombre, tiempoLlegada, duracion, estado)
}

/**
 * Sobrecarga de la funcion para crear un proceso junto a los tiempos de bloqueo por E/S
 *
 * @param nombre Nombre asignado al proceso
 * @param tiempoLlegada Momento en el que el proceso entra en la cola de procesos listos
 * @param duracion Duracion estimada del proceso
 * @param tiempoEntrada Momento en el que el proceso se bloquea por una accion de E/S
 * @param tiempoSalida Momento en el que el proceso sale del estado de bloqueo por E/S
 *
 * @return Devuelve un proceso con los valores correspondientes y tiempos de E/S
 */
fun crearProceso(
    nombre: String,
    tiempoLlegada: Int,
    duracion: Int,
    estado: Proceso.EstadoDeProceso,
    tiempoEntrada: Int,
    tiempoSalida: Int
): Proceso {
    // Crea un proceso
    val proceso = Proceso(nombre, tiempoLlegada, duracion, estado)

    // Agrega los tiempos de E/S al proceso creado
    proceso.setTiempoEntrada(tiempoEntrada)
    proceso.setTiempoSalida(tiempoSalida)

    return proceso
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
//fun imprimirListaProcesos(listaDeProcesos: MutableList<Proceso>) {
//    println("IMPRIMIENDO LISTADO DE PROCESOS . . .")
//    listaDeProcesos.forEach { proceso ->
//        println("NOMBRE: ${proceso.getNombre()}, LLEGADA: ${proceso.getLlegada()}, DURACION: ${proceso.getDuracion()}, ESTADO: ${proceso.getEstado()}")
//    }
//}
//
///**
// * Imprimir proceso por terminal
// */
//fun imprimirProceso(proceso: Proceso) {
//    println("IMPRIMIENDO PROCESO . . .")
//    println("NOMBRE: ${proceso.getNombre()}, LLEGADA: ${proceso.getLlegada()}, DURACION: ${proceso.getDuracion()}, ESTADO: ${proceso.getEstado()}")
//}