package com.i72pehej.cpuschedulerapp.util

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.util.extensions.InfoGraficoEstados

/**
 * @author Julen Perez Hernandez
 */

// ==============================================================

// Valores constantes para las animaciones
const val slideAnimationSpeed = 400
const val fadeAnimationSpeed = 300

// ==============================================================

// Valores para los botones basicos comunes de la app
const val buttonCornerRadius = 50
const val buttonBorderWith = 1
const val buttonDefaultPadding = 16

// ==============================================================

// Iconos comunes
//val appIconUCOColor = R.drawable.logos_version_uco
val appIconUCOSolo = R.drawable.uco_solo
val appIconCPU1 = R.drawable.cpu_icon_1
//val appIconCPU2 = R.drawable.cpu_icon_2
//val appIconCPU3 = R.drawable.cpu_icon_3

// ==============================================================

// Lista de procesos global con la que trabajar entre pantallas
var listaDeProcesosGlobal = mutableStateListOf<Proceso>()

// Contenedor de informacion de resultados
var infoResultadosGlobal = mutableListOf<InfoGraficoEstados>()

// ==============================================================

// Variable para almacenar el indice del algoritmo seleccionado de la lista de algoritmos en el formulario de Home
var selectorAlgoritmo = 0

// ==============================================================

// Variable para controlar el cambio de pagina a "Resultados"
var siguienteButtonHome = mutableStateOf(false)