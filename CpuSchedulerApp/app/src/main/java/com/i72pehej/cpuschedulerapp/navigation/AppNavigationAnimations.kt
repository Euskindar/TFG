package com.i72pehej.cpuschedulerapp.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry
import com.i72pehej.cpuschedulerapp.util.fadeAnimationSpeed
import com.i72pehej.cpuschedulerapp.util.slideAnimationSpeed

// Fichero para modularizar las animaciones de transicion de la navegacion de la app

/**
 * @author Julen Perez Hernandez
 * App navigation animations
 *
 * Clase que modulariza las animaciones de transicion entre paginas de la app
 *
 * @property enter Animacion de navegacion a la pantalla destino usando navigate()
 * @property exit Animacion de salida de la pantalla actual cuando se navega a otro destino
 * @property popEnter Animacion de la pantalla destino cuando se le da al boton de volver (popBackStack()). Por defecto == enter
 * @property popExit Animacion de la pantalla actual que se va al pulsar el boton de volver (popBackStack()). Por defecto == exit
 * @constructor Crea los objetos de las animaciones de navegacion que utilizaran las distintas paginas
 */
sealed class AppNavigationAnimations(
    val enter: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    val exit: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    val popEnter: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    val popExit: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null
) {
    // Animacion de la SplashScreen
    object SplashAnimations : AppNavigationAnimations(
        exit = {
            slideOut(
                animationSpec = tween(
                    durationMillis = slideAnimationSpeed,
                    easing = LinearOutSlowInEasing
                )
            ) { fullSize ->
                IntOffset(-fullSize.width / 4, -100)
            } + fadeOut()
        }
    )

    // Animaciones base para las transiciones entre pantallas normales
    object BasicNavigateAnimation : AppNavigationAnimations(
        enter = {
            slideInHorizontally(
                animationSpec = tween(
                    durationMillis = slideAnimationSpeed,
                    easing = LinearOutSlowInEasing
                )
            ) { fullWidth ->
                fullWidth / 3
            } + fadeIn(tween(fadeAnimationSpeed))
        },
        exit = { fadeOut(tween(fadeAnimationSpeed)) },
        popEnter = {
            expandHorizontally()
        },
        popExit = {
            fadeOut()
        }
    )
}
