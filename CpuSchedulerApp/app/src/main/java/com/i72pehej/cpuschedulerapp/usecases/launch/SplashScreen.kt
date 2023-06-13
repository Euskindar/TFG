package com.i72pehej.cpuschedulerapp.usecases.launch

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.i72pehej.cpuschedulerapp.navigation.AppScreens
import com.i72pehej.cpuschedulerapp.util.appIconCPU1
import kotlinx.coroutines.delay

/**
 * @author Julen Perez Hernandez
 */

// ==================================================================================

/**
 * Pantalla de carga para cuando se inicia la aplicacion de forma que de tiempo a que cargue lo necesario
 *
 * @param navController Elemento para controlar la informacion de navegacion
 */
@Composable
fun SplashScreen(navController: NavHostController) {
    // Variable para la animacion de salida de circulo
    val scaleBall = remember {
        Animatable(1.0f)
    }

    // Variable para la animacion de entrada de Icono de App
    val scaleUco = remember {
        Animatable(0.0f)
    }

    // Elementos
    Splash(scaleUco, scaleBall)

    // Launch screen
    LaunchScreen(navController, scaleBall, scaleUco)
}

// ==================================================================================

/**
 * Creacion del efecto de bote del icono de la pantalla de carga
 *
 * @param navController Elemento para controlar la informacion de navegacion
 * @param scaleBallIcon Escala inicial del circulo
 * @param scaleUcoIcon Escala inicial del icono de la app
 */
@Composable
fun LaunchScreen(
    navController: NavHostController,
    scaleBallIcon: Animatable<Float, AnimationVector1D>,
    scaleUcoIcon: Animatable<Float, AnimationVector1D>
) {
    // Llamada a animacion de transicion
    TransitionCircleExit(scaleBallIcon)

    // Llamada a animacion de icono
    AppIconAnimationEnter(navController, scaleUcoIcon)
}

// ==================================================================================

/**
 * Animacion del circulo de salida
 *
 * @param scaleBallIcon Escala inicial del circulo
 */
@Composable
fun TransitionCircleExit(scaleBallIcon: Animatable<Float, AnimationVector1D>) {
    // Efecto para el circulo que sale
    LaunchedEffect(key1 = true) {
        scaleBallIcon.animateTo(
            targetValue = 0.0f,
            animationSpec = tween(900, easing = {
                OvershootInterpolator(0f).getInterpolation(it)
            })
        )

        // Espera para coordinar efectos
        delay(100)
    }
}

// ==================================================================================

/**
 * Animacion de icono de app
 *
 * @param navController Controlador de la navegacion
 * @param scaleUcoIcon Escala inicial del icono de la app
 */
@Composable
fun AppIconAnimationEnter(
    navController: NavHostController,
    scaleUcoIcon: Animatable<Float, AnimationVector1D>
) {
    // Efecto para icono que entra
    LaunchedEffect(key1 = true) {
        scaleUcoIcon.animateTo(
            targetValue = 1.1f,
            animationSpec = tween(1000, easing = {
                OvershootInterpolator(6f).getInterpolation(it)
            })
        )
        // Para mantener el logo en pantalla por estetica
        delay(400)

        // Se llama primero a esta funcion para que el Home sea la primera pantalla
        // y en caso de darle hacia atras no volvamos a la splashscreen
        navController.popBackStack()
        navController.navigate(AppScreens.HomeScreen.route)
    }
}

// ==================================================================================

/**
 * Visualizacion de los elementos de la pantalla de carga
 *
 * @param scaleUco Escala inicial del icono de la app
 * @param scaleBall Escala inicial del circulo
 */
@Composable
fun Splash(
    scaleUco: Animatable<Float, AnimationVector1D>,
    scaleBall: Animatable<Float, AnimationVector1D>
) {
    // Pantalla de logo para la carga de la app
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        CenterHorizontally
    ) {
        // Imagen de Icono de la App
        Image(
            painter = painterResource(id = appIconCPU1),
            contentDescription = "Logo de la app",
            modifier = Modifier
                .fillMaxWidth()
                .scale(scaleUco.value)
        )
    }

    val circleColor = MaterialTheme.colors.background

    // Transicion
    Column(
        modifier = Modifier.fillMaxSize(),
        Arrangement.Center,
        CenterHorizontally
    ) {
        // Imagen en blanco para desaparecer y que salga el icono de la App
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .scale(scaleBall.value)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawCircle(
                color = circleColor,
                center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
                radius = size.minDimension
            )
        }
    }
}

// ==================================================================================