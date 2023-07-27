package com.i72pehej.cpuschedulerapp.util.extensions

import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.i72pehej.cpuschedulerapp.util.anchuraFormularioTiempos
import com.i72pehej.cpuschedulerapp.util.infoResultadosGlobal
import com.i72pehej.cpuschedulerapp.util.listaDeProcesosGlobal

/**\
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

/**
 * Funcion que limpia las listas de procesos y estados para resetear las tablas
 *
 * @param showDialog Booleano para mostrar o no el dialogo de confirmacion
 */
@Composable
fun LimpiarProcesos(showDialog: MutableState<Boolean>) {
    // Si se ha pulsado el boton de confirmacion, se limpian los procesos
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Confirmacion") },
            text = { Text(text = "Se van a borrar los procesos agregados. ¿Quiere seguir?") },
            confirmButton = {
                Button(onClick = {
                    showDialog.value = false
                    listaDeProcesosGlobal.clear()
                    infoResultadosGlobal.clear()
                }) { Text(text = "Si") }
            },
            dismissButton = { Button(onClick = { showDialog.value = false }) { Text(text = "No") } },
            modifier = Modifier.width(300.dp)
        )
    }
}

/**
 * ===================================================================
 */

/**
 * Funcion para seleccionar un proceso para eliminarlo de la lista
 *
 * @param showDialog Booleano para mostrar o no el cuadro de dialogo
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EliminarProcesoTabla(showDialog: MutableState<Boolean>) {
    if (showDialog.value) {
        // Varibales para gestionar la alerta
        var expandir by remember { mutableStateOf(value = false) }
        var procesoSelect by remember { mutableStateOf(listaDeProcesosGlobal.first().getNombre()) }

        // Cuadro de dialogo
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = "Seleccione el proceso a eliminar") },
            text = {
                // Menu desplegable para seleccion del proceso a eliminar
                ExposedDropdownMenuBox(
                    modifier = Modifier.width(anchuraFormularioTiempos.dp),
                    expanded = expandir,
                    onExpandedChange = { expandir = it }
                ) {
                    TextField(
                        readOnly = true,
                        value = procesoSelect,
                        onValueChange = { },
                        label = { Text("Proceso", fontSize = 12.sp) },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandir) },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )
                    ExposedDropdownMenu(
                        expanded = expandir,
                        onDismissRequest = { expandir = false }
                    ) {
                        listaDeProcesosGlobal.forEach { procesoSeleccionado ->
                            DropdownMenuItem(
                                onClick = {
                                    // Se selecciona el proceso
                                    procesoSelect = procesoSeleccionado.getNombre()
                                    expandir = false
                                }
                            ) { Text(text = procesoSeleccionado.getNombre()) }
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = {
                    showDialog.value = false

                    // Eliminar el proceso seleccionado
                    listaDeProcesosGlobal.removeAt(listaDeProcesosGlobal.indexOf(listaDeProcesosGlobal.find { it.getNombre() == procesoSelect }))
                }) { Text(text = "Eliminar") }
            },
            dismissButton = { Button(onClick = { showDialog.value = false }) { Text(text = "Cancelar") } },
            modifier = Modifier.width(300.dp)
        )
    }
}