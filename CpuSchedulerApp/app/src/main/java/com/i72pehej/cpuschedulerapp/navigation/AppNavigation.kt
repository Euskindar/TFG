package com.i72pehej.cpuschedulerapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.i72pehej.cpuschedulerapp.usecases.home.HomeScreen
import com.i72pehej.cpuschedulerapp.usecases.launch.SplashScreen

/**
 * @author Julen Perez Hernandez
 *
 * Fichero para establecer la navegacion entre las diferentes pantallas
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(temaOscuro: Boolean, onActualizarTema: () -> Unit) {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        // Elemento composable para la Splash Screen
        composable(
            AppScreens.SplashScreen.route,
            // Llamada a la clase de animaciones personalizada para modularizar
            exitTransition = AppNavigationAnimations.SplashAnimations.exit
        ) {
            // Llamada a la funcion que maneja el contenido de la pagina
            SplashScreen(navController)
        }

        // Elemento composable para la Home Screen
        composable(
            AppScreens.HomeScreen.route,
            enterTransition = AppNavigationAnimations.BasicNavigateAnimation.enter,
            exitTransition = AppNavigationAnimations.BasicNavigateAnimation.exit,
            popEnterTransition = AppNavigationAnimations.BasicNavigateAnimation.popEnter,
            popExitTransition = AppNavigationAnimations.BasicNavigateAnimation.popExit
        ) {
            // Llamada a la funcion que maneja el contenido de la pagina
            HomeScreen(
//                navController,
                temaOscuro,
                onActualizarTema
            )
        }
    }
}