package com.inicio.kotlinintermedio


// Clase para la leccion 5 de modificadores de visibilidad

// PUBLIC
private class Visibility {

    private var name: String? = null

    fun sayMyName() {
        name?.let { println("Mi nombre es $name.") }
            // Recordar que "?:" es para indicar una especie de ELSE para cuando el valor sea NULL
            ?: run { println("Soy un sinnombre!") }
    }
}

// PRIVATE
open class VisibilityPrivate {

    // Protected para el caso siguiente, sino dejarlo publico
    protected fun sayMyNamePrivate() {
        val visibility = Visibility()   // Esto no da error ya que estamos en el mismo fichero

        // val name = visibility.name // Esto da error ya que la variable name es privada
    }
}

// PROTECTED (solo se permite acceso a subclases)
class VisibilityProtected: VisibilityPrivate() {

    fun sayMyNameProtected() {
        sayMyNamePrivate()  // Se puede acceder porque estamos en subclase
    }
}

// INTERNAL (es la menos utilizada)
class VisibilityInternal {

    // Se hace accesible desde cualquier parte de la aplicacion, incluso fuera de este modulo
    internal val age: Int? = null
}
