package com.i72pehej.cpuschedulerapp.util.extensions

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.i72pehej.cpuschedulerapp.R
import kotlinx.coroutines.delay

/**
 * @author Julen Perez Hernandez
 */

open class BackPressControl {
    object Idle : BackPressControl()
    object InitialTouch : BackPressControl()
}

/**
 * Funcion para controlar que la app no se cierre por accidente al pulsar el boton de back
 */
@Composable
fun ConfirmacionBackPress() {
    // Variables de control de estados
    var showToast by remember { mutableStateOf(false) }
    var backPressState by remember { mutableStateOf<BackPressControl>(BackPressControl.Idle) }
    val context = LocalContext.current

    // Creacion del mensaje
    if (showToast) {
        Toast.makeText(
            context,
            stringResource(id = R.string.back_press_confirmacion),
            Toast.LENGTH_SHORT
        ).show()
        showToast = false
    }

    // Control de la pulsacion del boton y tiempo de espera para la segunda pulsacion
    LaunchedEffect(backPressState) {
        if (backPressState == BackPressControl.InitialTouch) {
            delay(1500)
            backPressState = BackPressControl.Idle
        }
    }

    // Control del boton de back para cambiar los estados
    BackHandler(backPressState == BackPressControl.Idle) {
        backPressState = BackPressControl.InitialTouch
        showToast = true
    }
}