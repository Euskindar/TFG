package com.jolon.jetpackcomposeapp.navigation

// Clase sellada de Kotlin para centralizar todas las pantallas
// Para navegar entre pantallas se necesita conocer las diferentes rutas de las pantallas
sealed class AppScreens(val ruta: String) {
    // Creamos los objetos correspondientes a las pantallas que tengamos, asignandoles una ruta
    object HomeScreen : AppScreens("home_screen")
    object SecondScreen : AppScreens("second_screen")
}
