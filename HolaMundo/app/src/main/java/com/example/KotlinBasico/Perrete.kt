package com.example.KotlinBasico

class Perrete(val nombre: String,
              val edad: Int,
              val amos: Array<Amos>,
              val raza: String,
              val friends: Array<Perrete>? = null /*Puede ser null*/) {

    // Clase de Listado de objetos
    enum class Amos {
        JULEN,
        LERIE,
        BEGO,
        ANTONIO
    }
}