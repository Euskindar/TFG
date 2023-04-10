package com.inicio.kotlinintermedio

// Creacion de interfaces (No guardan estado, es decir, no almacenan valores de nada)
interface Game {

    val game: String

    fun play()

    fun stream() {
        println("Estoy haciendo streaming!")
    }
}