package com.i72pehej.cpuschedulerapp.util

import com.i72pehej.cpuschedulerapp.R

/**
 * @author Julen Perez Hernandez
 */

// ==============================================================

// Valores constantes para las animaciones
const val slideAnimationSpeed = 300
const val fadeAnimationSpeed = 300

// ==============================================================

// Valores para los botones basicos comunes de la app
const val buttonCornerRadius = 50
const val buttonBorderWith = 1
const val buttonDefaultPadding = 16

// ==============================================================

// Iconos comunes
val appIcon = R.drawable.logos_version_uco

// ==============================================================

// Lista de procesos de prueba
//val listaDeProcesosPrueba = mutableListOf<Proceso>()
//
//fun crearProcesosDePrueba(): MutableList<Proceso> {
//    for (i in 1..5) {
//        val nombre = "Proceso $i"
//        val tiempoLlegada = Random.nextInt(1, 11)
//        val duracion = Random.nextInt(1, 11)
//        val estado = Proceso.EstadoDeProceso.NUEVO
//        val progreso = 0
//
//        listaDeProcesosPrueba.add(Proceso(i, nombre, tiempoLlegada, duracion, estado, progreso))
//    }
//
//    return listaDeProcesosPrueba
//}

// ==============================================================

// Variable para almacenar el indice del algoritmo seleccionado de la lista de algoritmos en el formulario de Home
var algoritmo = 0

// ==============================================================