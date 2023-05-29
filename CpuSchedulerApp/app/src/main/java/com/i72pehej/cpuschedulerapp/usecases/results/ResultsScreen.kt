package com.i72pehej.cpuschedulerapp.usecases.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.i72pehej.cpuschedulerapp.util.extensions.TablaResultados
import com.i72pehej.cpuschedulerapp.util.listaDeProcesosGlobal

/**
 * @author Julen Perez Hernandez
 */

/**
 * Pantalla inicial en la que comenzar la navegacion por la app
 */
@Composable
fun ResultsScreen() {
    // Disposicion principal de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Text(text = stringResource(id = R.string.results_name))

        // Generar tabla de resultados
        TablaResultados(procesos = listaDeProcesosGlobal)
    }
}