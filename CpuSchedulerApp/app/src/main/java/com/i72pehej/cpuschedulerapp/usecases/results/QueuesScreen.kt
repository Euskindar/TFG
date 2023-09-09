package com.i72pehej.cpuschedulerapp.usecases.results

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.i72pehej.cpuschedulerapp.util.classes.Proceso
import com.i72pehej.cpuschedulerapp.util.infoResultadosGlobal

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
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Creacion del listado de colas
        crearListaColas()

        // Disposicion de la tabla resultante
        TablaColasDeProcesos()
    }
}

/**
 * ===========================================================================================
 */

// Listado de las colas en cada momento
val listaDeColas: MutableList<List<String>> = mutableListOf()

/**
 * Funcion que crea una lista con el orden de los procesos en la cola en cada momento
 */
fun crearListaColas() {
    // Limpiamos el listado para evitar sobrecarga de la lista
    listaDeColas.clear()

    // Obtenemos los indices de inicio y fin del bucle
    val primerIndice = infoResultadosGlobal.first().getMomento()
    val ultimoIndice = infoResultadosGlobal.last().getMomento()

    // Crearmos una copia de los estados para evitar trabajar con la original
    val listaEstados = infoResultadosGlobal.toMutableList()

    // Iteramos por cada columna (== momento) para obtener la lista de procesos en cola en ese momento
    for (columna in primerIndice until ultimoIndice) {
        // Sublista para obtener solo los elementos del momento actual
        var subListaEstados = listaEstados.filter { it.getMomento() == columna }

        // Eliminamos los procesos en estados que no corresponden a la cola de LISTOS
        subListaEstados = subListaEstados.filter { (it.getEstado() != Proceso.EstadoDeProceso.BLOQUEADO) && (it.getEstado() != Proceso.EstadoDeProceso.COMPLETADO) }

        // Sublista para obtener los nombres de los procesos del momento actual
        val subListaNombres = subListaEstados.map { it.getNombre() }

        // Agregamos a la lista de colas la lista de nombres de procesos
        listaDeColas.add(subListaNombres)
    }
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
                    modifier = Modifier.width(35.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                // Agregamos la columna para el listado de colas
                Text(
                    text = "Cola de Procesos",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Agregamos una fila para cada cola en cada momento
        itemsIndexed(listaDeColas) { index, item ->
            // Fila de colas
            Row(modifier = Modifier.padding(4.dp)) {
                // Agregamos el momento de la cola
                Text(
                    text = "$index |",
                    modifier = Modifier.width(35.dp),
                    textAlign = TextAlign.Center
                )

                // Agregamos la cola
                Text(text = item.toString(), modifier = Modifier.weight(1f))
            }
        }
    }
}