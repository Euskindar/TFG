package com.inicio.kotlinintermedio

// Clase creada para la herencia de clases y heredar de Person
class Programmer(val name: String, val age: Int, val lenguajes: Array<String>): MyInheritanceClass.Persona(name, age) {

    // Sobreescribir funcion original
    override fun work() {
        println("Estoy programando en Kotlin")

        // Llamada a la funcion original de Persona
        super.work()
    }

    // Ampliar funcionamiento de clase
    fun printLenguajes() {

        // Imprime todos los valores del array lenguajes
        lenguajes.forEach {
            println("Se programar en $it.")
        }
    }
}