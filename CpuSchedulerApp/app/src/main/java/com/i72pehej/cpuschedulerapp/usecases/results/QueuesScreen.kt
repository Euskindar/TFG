package com.i72pehej.cpuschedulerapp.usecases.results

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.i72pehej.cpuschedulerapp.ui.theme.Azul_com_3
import com.i72pehej.cpuschedulerapp.ui.theme.Rojo_com_2
import com.i72pehej.cpuschedulerapp.ui.theme.Verde_deriv_1
import com.i72pehej.cpuschedulerapp.util.classes.Proceso

/**
 * @author Julen Perez Hernandez
 */


/**
 * Pantalla de colas en la que ver las diferentes colas que se crean cuando los procesos estan listos
 */
@Composable
fun QueuesScreen() {
    // Disposicion principal de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TablaColasDeProcesos()
    }
}

/**
 * ===========================================================================================
 */

// Map que almacena el listado de colas de procesos con el Momento de la cola como identificador
private val mapDeProcesos: Map<Int, MutableList<Proceso>> = mapOf()

/**
 * Funcion que crea una lista con el orden de los procesos en la cola en cada momento
 *
 * @param cola Listado de procesos en la cola
 */
fun crearListaColas(cola: MutableList<Proceso>) {
    // Variable para obtener la nueva clave incrementando la anterior (en caso de ser el primer elemento se coloca 0)
    val nuevaClave = mapDeProcesos.keys.maxOrNull()?.plus(1) ?: 0

    // Se asigna el nuevo elemento al map
    mapDeProcesos + (nuevaClave to cola)
}

/**
 * ===========================================================================================
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TablaColasDeProcesos() {
    // Creamos una tabla utilizando LazyColumn
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant, RoundedCornerShape(5.dp))
            .padding(8.dp)
    ) {
        // Agregamos una fila para el encabezado de la tabla
        stickyHeader {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.secondaryVariant, RoundedCornerShape(5.dp))
                    .padding(4.dp)
            ) {
                // Agregamos una celda inicial en blanco para considerar la columna
                Text(
                    text = "",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                // Agregamos el numero de columnas correspondientes a cada momento de la linea de tiempos
//                for (nCols in mapDeProcesos.keys.first() until mapDeProcesos.keys.max()) {
                for (nCols in 0 until 10) {
                    Text(
                        text = "$nCols",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Agregamos una fila para cada cola en cada momento

        item {

            // Filas
            Row(modifier = Modifier.padding(4.dp)) {
                // Agregamos los nombres de los procesos
                Text(
                    text = "A",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )

                // Separador vertical
                Divider(
                    modifier = Modifier
                        .height(21.dp)
                        .width(1.dp), color = MaterialTheme.colors.secondary
                )

                // Recorremos las columnas de tiempos
                for (cols in 0 until 10) {
                    // Para cada momento, se compara si el proceso tiene evento y se coloca el simbolo correspondiente
                    // Texto a poner en la celda
                    Text(
                        text = "A",
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun FilaColas() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ItemColaDeProcesos("E", "Espera", Color.Black.copy(alpha = 0.7f))
        ItemColaDeProcesos("X", "Ejecución", Azul_com_3.copy(alpha = 0.7f))
        ItemColaDeProcesos("B", "Bloqueado", Rojo_com_2.copy(alpha = 0.7f))
        ItemColaDeProcesos("C", "Completado", Verde_deriv_1.copy(alpha = 0.7f))
    }
}

@Composable
private fun ItemColaDeProcesos(simbolo: String, etiqueta: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            modifier = Modifier.size(30.dp),
            shape = MaterialTheme.shapes.small,
            color = color
        ) {
            Text(
                text = simbolo,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterVertically),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.size(4.dp))

        Text(
            text = etiqueta,
            fontSize = 12.sp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}