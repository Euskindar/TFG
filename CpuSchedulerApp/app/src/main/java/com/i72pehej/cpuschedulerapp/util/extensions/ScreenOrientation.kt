package com.i72pehej.cpuschedulerapp.util.extensions

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


/**
 * @author Julen Perez Hernandez
 */


/**
 * Funcion para modificar la orientacion de la pantalla
 *
 * @param context Contexto actual en el que se encuentra el usuario
 */
@Composable
fun ScreenOrientation(
    context: Context,
    orientacionVertical: Boolean
) {
    // Variable para almacenar la orientacion de la pantalla
    val vertical = remember {
        mutableStateOf(orientacionVertical)
    }

    // Variable para indicar la actividad
    val activity = context as Activity

    // Cambio de la orientacion de la pantalla
    if (vertical.value) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    } else {
        // No es recomendable forzar una orientacion, asi que permitimos volver a vertical si el usuario lo desea
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

    // Invertimos el estado de la orientacion actual
    vertical.value = !vertical.value
}