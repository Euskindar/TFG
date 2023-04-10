package com.inicio.kotlinintermedio

// Clase creada para la herencia de clases y heredar de Person
class Designer(val name: String, val age: Int) : MyInheritanceClass.Persona(name, age) {

    // Sobreescribir la funcion original
    override fun work() {
        println("Estoy disenando en Android Studio")

        // Llamada a la funcion original de Persona
        super.work()
    }
}