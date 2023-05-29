package com.i72pehej.cpuschedulerapp.util.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.util.Proceso

/**\
 * @author Julen Perez Hernandez
 */

/**
 * ===========================================================================================
 */

/**
 * Creacion de la tabla de procesos agregados
 *
 * @param procesos Lista de los procesos agregados
 */
@Composable
fun TablaProcesos(procesos: List<Proceso>) {
    // Si la lista de procesos no está vacía
    if (procesos.isNotEmpty()) {
        // Creamos una tabla utilizando LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            // Agregamos una fila para el encabezado de la tabla
            item {
                Row(
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(4.dp)
                ) {
                    // Agregamos cada columna a la fila de encabezado con un peso de 1f para que tengan el mismo ancho
                    Text(
                        stringResource(id = R.string.formulario_nombre),
                        modifier = Modifier.weight(1f), // Le damos un peso de 1f para que tenga el mismo ancho que las otras columnas.
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.formulario_llegada),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.formulario_duracion),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Agregamos una fila para cada proceso en la lista de procesos
            items(procesos) { proceso ->
                Row(modifier = Modifier.padding(4.dp)) {
                    Text(
                        proceso.nombre,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.tiempoLlegada.toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.duracion.toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    } else {
        // Si la lista de procesos está vacía, mostramos un mensaje indicando que no hay procesos
        Text(
            stringResource(id = R.string.tabla_vacia),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
    }
}

/**
 * ===========================================================================================
 */

/**
 * Creacion de la tabla de resultados
 *
 * @param procesos Lista de los procesos agregados
 */
@Composable
fun TablaResultados(procesos: List<Proceso>) {
    // Si la lista de procesos no está vacía
    if (procesos.isNotEmpty()) {
        // Creamos una tabla utilizando LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            // Agregamos una fila para el encabezado de la tabla
            item {
                Row(
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(4.dp)
                ) {
                    // Agregamos cada columna a la fila de encabezado con un peso de 1f para que tengan el mismo ancho
                    Text(
                        stringResource(id = R.string.formulario_nombre),
                        modifier = Modifier.weight(1f), // Le damos un peso de 1f para que tenga el mismo ancho que las otras columnas.
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.formulario_llegada),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.formulario_duracion),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Agregamos una fila para cada proceso en la lista de procesos
            items(procesos) { proceso ->
                Row(modifier = Modifier.padding(4.dp)) {
                    Text(
                        proceso.nombre,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.tiempoLlegada.toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.duracion.toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    } else {
        // Si la lista de procesos está vacía, mostramos un mensaje indicando que no hay procesos
        Text(
            stringResource(id = R.string.tabla_vacia),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
    }
}