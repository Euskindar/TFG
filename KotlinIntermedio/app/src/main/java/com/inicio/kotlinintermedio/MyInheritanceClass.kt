package com.inicio.kotlinintermedio

open class MyInheritanceClass {

    // Herencia de clases (le voy a meter lo de la leccion 2 clases anidadas)

    // Se agrega la clase Work, haciendo que la clase Persona extienda de la clase Work
    // Esto obliga a que implementemos la operacion de la funcion que hay en la clase Work

    // Si se quiere agregar mas clases extendidas, se deben poner con la coma y ser tipo INTERFACE (Lo es "Vehicle" en este caso)
    open class Persona(name: String, age: Int) : Work(), Vehicle, Game {

        // Cualquier clase tiene como superclase la clase "ANY"
        open fun work() {
            println("Estoy empezando a aprender Kotlin desde cero")
        }

        // Se agrega la implementacion de las funciones de la clase extendida
        override fun goToWork() {
            println("A trabajar pedazo de vago!")
        }

        // Interfaces de Game
        override val game: String = "LoL"

        override fun play() {
            println("Estoy jugando a $game.")
        }
    }
}