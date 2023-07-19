package com.i72pehej.cpuschedulerapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.i72pehej.cpuschedulerapp.navigation.AppNavigation
import com.i72pehej.cpuschedulerapp.ui.theme.CpuSchedulerAppTheme

/**
 * @author Julen Perez Hernandez
 * Main activity
 *
 * @constructor Crea la actividad Main para la llamada a las funciones iniciales
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val systemTheme = LocalContext.current.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            var temaOscuro by remember { mutableStateOf(systemTheme == Configuration.UI_MODE_NIGHT_YES) }

            CpuSchedulerAppTheme(darkTheme = temaOscuro) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    // Llamamos al gestor de navegacion, que se encargara de visualizar las pantallas en el orden seleccionado
                    AppNavigation(temaOscuro, onActualizarTema = { temaOscuro = !temaOscuro })
                }
            }
        }
    }
}