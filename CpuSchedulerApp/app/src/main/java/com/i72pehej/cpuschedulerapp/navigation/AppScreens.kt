package com.i72pehej.cpuschedulerapp.navigation

// Fichero para definir las diferentes pantallas entre las que podemos navegar

/**
 * @author Julen Perez Hernandez
 * App screens
 *
 * @property route Nombre de la ruta de la pagina de destino a la que se va a navegar
 * @constructor Crea los objetos de las rutas de cada una de las paginas a las que se puede navegar
 */
sealed class AppScreens(val route: String) {
    object SplashScreen : AppScreens("splash_screen")
    object HomeScreen : AppScreens("home_screen")
}