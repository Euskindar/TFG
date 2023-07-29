package com.i72pehej.cpuschedulerapp.util.extensions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.util.classes.InfoGraficoEstados
import com.i72pehej.cpuschedulerapp.util.classes.Proceso
import com.i72pehej.cpuschedulerapp.util.infoResultadosGlobal

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
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                stringResource(id = R.string.tabla_vacia),
                modifier = Modifier.padding(8.dp),
            )
        }
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
    // Si la lista de estados no está vacía
    if (infoResultadosGlobal.isNotEmpty()) {
        // Comprobar si tiene E/S
        val entradaSalida = procesos.any { it.getTiempoEntrada() > 0 }

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
                        proceso.tiempoInicio().toString(),          // Inicio
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.tiempoFin().toString(),             // Fin
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
//                        "${proceso.getTiempoEstancia()} = ${proceso.tiempoFin()} - ${proceso.getLlegada()}",    // Estancia
                        proceso.getTiempoEstancia().toString(),    // Estancia
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
//                        "${proceso.getTiempoEspera()} = ${proceso.getTiempoEstancia()} - ${proceso.getDuracion()}",       // Espera
                        proceso.getTiempoEspera().toString(),       // Espera
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
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
        // Variable que almacena el valor maximo que tendra la linea de tiempos
        val maxMomento = infoRes.last().getMomento()

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
                    // Agregamos una columna inicial para los procesos
                    Text(
                        text = "",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    // Agregamos el numero de columnas correspondientes a cada momento de la linea de tiempos
                    for (nCols in infoRes.first().getMomento() until maxMomento) {
                        Text(
                            text = "$nCols",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Sublista con la primera aparicion de cada uno de los procesos con nombres distintos para crear las filas
            val listaNombres = infoRes.distinctBy { it.getNombre() }

            // Agregamos una fila para cada proceso en la lista de procesos
            items(listaNombres) { nombreActual ->
                // Filas
                Row(modifier = Modifier.padding(4.dp)) {
                    // Agregamos los nombres de los procesos
                    Text(
                        text = nombreActual.getNombre(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    // Separador vertical
                    Divider(
                        modifier = Modifier
                            .height(21.dp)
                            .width(1.dp), color = MaterialTheme.colors.secondary
                    )

                    // Variables para guardar el simbolo para colocar
                    var simbolo: String
                    var simboloAnterior = ""

                    // Recorremos las columnas de tiempos
                    for (cols in infoRes.first().getMomento() until maxMomento) {

                        // Recorremos la lista de estados
//                        for (itEstados in infoRes.indices) {
//
//                            // Si se tiene un evento en el momento de la columna y la fila del proceso con el que estamos trabajando -> Comprobamos estados
//                            if ((infoRes[itEstados].getMomento() == cols) && (nombreActual.getNombre() == infoRes[itEstados].getNombre())) {
//
//                                // Valor a colocar en la celda dependiendo del estado del proceso
//                                when (infoRes[itEstados].getEstado()) {
//                                    Proceso.EstadoDeProceso.LISTO -> {
//                                        simboloAnterior = "E"
//                                        "E" // En ESPERA
//                                    }
//
//                                    Proceso.EstadoDeProceso.EJECUCION -> {
//                                        simboloAnterior = "x"
//                                        "x" // En EJECUCION
//                                    }
//
//                                    Proceso.EstadoDeProceso.BLOQUEADO -> {
//                                        simboloAnterior = "B"
//                                        "B" // BLOQUEADO por E/S
//                                    }
//
//                                    Proceso.EstadoDeProceso.COMPLETADO -> {
//                                        simboloAnterior = ""
//                                        "" // COMPLETADO (celda vacia)
//                                    }
//                                }.also { simbolo = it }
//                            }
//                        }

                        // En caso de no haber evento en el momento, se repite el simbolo anterior
                        simbolo = simboloAnterior

                        // Texto a poner en la celda
                        Text(
                            text = simbolo,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}