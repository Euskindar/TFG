package com.i72pehej.cpuschedulerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.i72pehej.cpuschedulerapp.navigation.AppNavigation
import com.i72pehej.cpuschedulerapp.ui.theme.CpuSchedulerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CpuSchedulerAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    // Llamamos al gestor de navegacion, que se encargara de visualizar las pantallas en el orden selecionado
                    AppNavigation()
                }
            }
        }
    }
}