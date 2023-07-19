package com.i72pehej.cpuschedulerapp.util.extensions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TablaProcesos(procesos: List<Proceso>) {
    // Si la lista de procesos no está vacía
    if (procesos.isNotEmpty()) {
        // Comprobar si tiene E/S
        val entradaSalida = procesos.any { it.getTiempoEntrada() > 0 }

        // Creamos una tabla utilizando LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            // Agregamos una fila para el encabezado de la tabla
            stickyHeader {
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

                    // En caso de tener E/S se coloca tambien en la tabla
                    if (entradaSalida) {
                        Text(
                            text = "E",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "S",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Agregamos una fila para cada proceso en la lista de procesos
            items(procesos) { proceso ->
                Row(modifier = Modifier.padding(4.dp)) {
                    Text(
                        proceso.getNombre(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.getLlegada().toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.getDuracion().toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    // Si tiene E/S
                    if (entradaSalida) {
                        Text(
                            if (proceso.getTiempoEntrada() > 0) proceso.getTiempoEntrada().toString() else "-",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            if (proceso.getTiempoSalida() > 0) proceso.getTiempoSalida().toString() else "-",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
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
 * Creacion de la tabla de los tiempos resultantes obtenidos
 *
 * @param procesos Lista de los procesos
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TablaTiemposResultados(procesos: List<Proceso>) {
    // Si la lista de procesos no está vacía
    if (procesos.isNotEmpty()) {
        // Comprobar si tiene E/S
        val entradaSalida = procesos.any { it.getTiempoEntrada() > 0 }

        // Creamos una tabla utilizando LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            // Agregamos una fila para el encabezado de la tabla
            stickyHeader {
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

                    // En caso de tener E/S se coloca tambien en la tabla
                    if (entradaSalida) {
                        Text(
                            text = "E",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "S",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        stringResource(id = R.string.nombre_tiempo_inicio),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.nombre_tiempo_fin),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.nombre_tiempo_estancia),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.nombre_tiempo_espera),
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
                        proceso.getNombre(),                    // Nombre
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.getLlegada().toString(),        // Llegada
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.getDuracion().toString(),       // Duracion
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    // Si tiene E/S
                    if (entradaSalida) {
                        Text(
                            if (proceso.getTiempoEntrada() > 0) proceso.getTiempoEntrada().toString() else "-",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            if (proceso.getTiempoSalida() > 0) proceso.getTiempoSalida().toString() else "-",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }

                    Text(
                        proceso.tiempoInicio().toString(),  // Inicio
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.tiempoFin().toString(),         // Fin
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.tiempoEstancia().toString(),    // Estancia
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.getTiempoEspera().toString(),   // Espera
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
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

/**
 * ===========================================================================================
 */

/**
 * Creacion de la tabla de procesos agregados
 *
 * @param infoRes Lista de los estados adquiridos por los procesos
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TablaResultadosGraficos(infoRes: List<InfoGraficoEstados>) {
    // Si la lista de procesos no está vacía
    if (infoRes.isNotEmpty()) {
        // Creamos una tabla utilizando LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            // Agregamos una fila para el encabezado de la tabla
            stickyHeader {
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
            items(infoRes) { info ->
                Row(modifier = Modifier.padding(4.dp)) {
                    Text(
                        info.proceso.getNombre(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        info.proceso.getLlegada().toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        info.proceso.getDuracion().toString(),
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