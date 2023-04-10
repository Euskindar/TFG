package com.jolon.jetpackcomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jolon.jetpackcomposeapp.screens.HomeScreen
import com.jolon.jetpackcomposeapp.screens.SecondScreen

// Este sera el elemento composable que se encargara de gestionar la navegacion, conocer las pantallas y navgar entre ellas
@Composable
fun AppNavigation() {
    // Los elementos que nos sirven para navegar entre pantallas y ficheros se encuentran en una libreria a parte
    // Esta libreria la agregamos desde el menu lateral => Gradle Scripts > build.Gradle > hacemos scroll hasta "dependencies"
    // ahi escribimos la ruta de la libreria, en este caso: "implementation 'androidx.navigation:navigation-compose:2.5.3'"

    // Ahora podemos seguir con la navegacion
    // Creamos la siguiente constante, que se encargara de controlar el estado de la navegacion entre pantallas y es necesario propagarla entre todas ellas
    val navController = rememberNavController()

    // Se encarga de crear el grafico a seguir por el navController para gestionar la navegacion entre pantallas
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.ruta) {
        // Se agregan los elemenos composable que hacen referencias a cada una de las pantallas
        composable(route = AppScreens.HomeScreen.ruta) { HomeScreen(navController) }

        // Para pasar argumentos entre pantallas, se le suma a la ruta flow URL
        composable(
            route = AppScreens.SecondScreen.ruta + "/{text}",

            // Se le pueden agregar todos los argumentos que se quieran
            arguments = listOf(navArgument(name = "text") {
                // En este caso es de tipo string
                type = NavType.StringType
            })
            // Comprobar la recepcion de argumentos
        ) { SecondScreen(navController, it.arguments?.getString("text")) }
    }

}