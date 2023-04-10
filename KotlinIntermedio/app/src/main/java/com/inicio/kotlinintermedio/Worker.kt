package com.inicio.kotlinintermedio


// Leccion 6 data classes
// Clase creada para el tratemiento de las data classes
// La idea de estos es la creacion de una clase que unicamente almacene los datos asociados
data class Worker(val name: String = "", val age: Int = 0, val work: String = "") {

    // Agregar propiedades que no son obligatorias
    var lastWork: String = ""
}
