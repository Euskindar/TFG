package com.inicio.kotlinintermedio

class MyNestedInnerClass {

    private val one = 1 // No se puede acceder desde la clase anidada

    // Crear clase anidada
    class MyNestedClass {
        fun sum(num1: Int, num2: Int) : Int {
            return num1 + num2
        }
    }

    // Clase interna (permite acceso a los miembros de la clase superior)
    inner class MyInnerClass {
        fun sumOne(num1: Int) : Int {
            return num1 + one
        }
    }
}