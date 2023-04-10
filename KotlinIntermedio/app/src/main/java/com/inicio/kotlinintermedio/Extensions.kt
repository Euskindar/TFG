package com.inicio.kotlinintermedio

import java.text.SimpleDateFormat
import java.util.*

// Fichero para la leccion 9 de Extensiones en Kotlin

// *** TIP *** Crear un fichero de extenisones por cada clase que se esta extendiendo (por ejemplo: DateExtensions)
// De esta forma podemos crear elementos concretos para nosotros, como puede ser un tipo de Boton en concreto

// Extensiones en funciones
fun Date.nuevoFormato() : String {
    // Hemos importado la libreria de Java de fechas para darle una nueva funcionalidad
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ", Locale.getDefault())
    return formatter.format(this) // This hacer referencia a la clase base, en este caso a Date
}

// para no tener que estar comprobando en el main si es nulo o no, se considera aqui el ser nullable
fun Date?.nuevoFormatoNull() : String? {
    // Hemos importado la libreria de Java de fechas para darle una nueva funcionalidad
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ", Locale.getDefault())
    return this?.let { formatter.format(it) } // This hacer referencia a la clase base, en este caso a Date
}

// Extension de propiedades + Control de valores null
val Date?.formatSize: Int
    get() = this.nuevoFormatoNull()?.length ?: 0 // El ?: para considerar valor por defecto en caso de null