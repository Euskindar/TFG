package com.i72pehej.cpuschedulerapp.usecases.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.i72pehej.cpuschedulerapp.ui.theme.Azul_com_3
import com.i72pehej.cpuschedulerapp.ui.theme.Rojo_com_2
import com.i72pehej.cpuschedulerapp.ui.theme.Verde_deriv_1
import com.i72pehej.cpuschedulerapp.util.extensions.TablaResultadosGraficos
import com.i72pehej.cpuschedulerapp.util.infoResultadosGlobal

/**
 * @author Julen Perez Hernandez
 */

/**
 * Pantalla de graficos en la que poder visualizar los resultados obtenidos de manera grafica
 */
@Composable
//fun GraphsScreen(navController: NavHostController) {
fun GraphsScreen() {
    // Disposicion principal de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Tabla de leyenda para la simbologia de la tabla grafica de resultados
        TablaLeyendaGraficos()

        Spacer(modifier = Modifier.height(8.dp))

        TablaResultadosGraficos(infoRes = infoResultadosGlobal)
    }
}

/**
 * ===========================================================================================
 */

/**
 * Tabla para considerar la leyenda de los simbolos colocados en la grafica
 */
@Composable
fun TablaLeyendaGraficos() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant, RoundedCornerShape(5.dp))
            .padding(8.dp)
    ) { FilaLeyenda() }
}

/**
 * Fila en la que visualizan todos los elementos de la leyenda
 */
@Composable
private fun FilaLeyenda() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SimboloDeLeyenda("E", "Espera", Color.Black.copy(alpha = 0.7f))
        SimboloDeLeyenda("X", "Ejecución", Azul_com_3.copy(alpha = 0.7f))
        SimboloDeLeyenda("B", "Bloqueado", Rojo_com_2.copy(alpha = 0.7f))
        SimboloDeLeyenda("C", "Completado", Verde_deriv_1.copy(alpha = 0.7f))
    }
}

/**
 * Funcion para crear cada uno de los elementos que se colocaran en la leyenda
 *
 * @param simbolo Simbolo que se colocara en la tabla grafica
 * @param etiqueta Significado al que hace referencia el simbolo
 * @param color Color de fondo del simbolo
 */
@Composable
private fun SimboloDeLeyenda(simbolo: String, etiqueta: String, color: Color) {
    Row(verticalAlignment = CenterVertically) {
        Surface(
            modifier = Modifier.size(30.dp),
            shape = MaterialTheme.shapes.small,
            color = color
        ) {
            Text(
                text = simbolo,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.align(CenterVertically),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = etiqueta,
            fontSize = 12.sp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
            modifier = Modifier.align(CenterVertically)
        )
    }
}