package com.i72pehej.cpuschedulerapp.usecases.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.i72pehej.cpuschedulerapp.R

/**
 * @author Julen Perez Hernandez
 */

// TODO -> FALTA DE IMPLEMENTACION DE LAS COLAS DE PROCESOS

/**
 * Pantalla de colas en la que ver las diferentes colas que se crean cuando los procesos estan listos
 */
@Composable
//fun GraphsScreen(navController: NavHostController) {
fun QueuesScreen() {
    // Disposicion principal de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = R.string.queues_name))
    }
}