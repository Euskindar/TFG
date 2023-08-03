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
import androidx.compose.foundation.lazy.items
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TablaColasDeProcesos()
        crearListaColas()
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
    // Obtenemos los valores de las claves de inicio y fin para tener un rango con el que trabajar
    val primerIndice = infoResultadosGlobal.first().getMomento()
    val ultimoIndice = infoResultadosGlobal.last().getMomento() - 1 // Ignoramos el COMPLETADO final

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
                for (nCols in primerIndice until ultimoIndice) {
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

        items(listaDeColas) {colaActual ->

            // Filas
            Row(modifier = Modifier.padding(4.dp)) {
                // Agregamos los nombres de los procesos
                Text(
                    text = "Titulo",
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
                for (cols in primerIndice until ultimoIndice) {
                    // Para cada momento, se compara si el proceso tiene evento y se coloca el simbolo correspondiente
                    // Texto a poner en la celda
                    Text(
                        text = colaActual.toString(),
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