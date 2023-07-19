package com.i72pehej.cpuschedulerapp.usecases.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.i72pehej.cpuschedulerapp.util.appIconUCOSolo
import com.i72pehej.cpuschedulerapp.util.extensions.ThemeSwitcher
import com.i72pehej.cpuschedulerapp.util.listaDeProcesosGlobal

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===================================================================
 */

/**
 * Common scaffold para toda la app
 *
 * @param content Contenido que mostrar en pantalla
 */
@Composable
fun CommonScaffold(
    temaOscuro: Boolean,
    onActualizarTema: () -> Unit,
    scaffoldState: ScaffoldState,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommonTopAppBar(
                temaOscuro,
                onActualizarTema
            )
        },
        content = content,
    )
}

/**
 * ===================================================================
 */

/**
 * Funcion que crea la top bar comun de la app
 *
 * @param temaOscuro Control para el switch del tema de la app
 * @param onActualizarTema Funcion para actualizar el tema
 */
@Composable
fun CommonTopAppBar(
    temaOscuro: Boolean,
    onActualizarTema: () -> Unit
) {
    // Control del menu de ajustes desplegable
    var verMenuAjustes by remember { mutableStateOf(false) }

    // Barra superior de la pantalla
    TopAppBar(
        modifier = Modifier.height(30.dp),
        title = {
            Row {
                Icon(
                    painter = painterResource(id = appIconUCOSolo),
                    contentDescription = "Icono principal de la App",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .size(50.dp),
                )
            }
        },
        elevation = 20.dp,
        // Menu desplegable para ajustes basicos
        actions = {
            // Icono para limpiar la tabla de procesos
            IconButton(onClick = { limpiarProcesos() }) { Icon(imageVector = Icons.Filled.DeleteSweep, contentDescription = "Boton de Limpiar Tabla") }
            // Icono de ajustes que cambia el estado del menu
            IconButton(onClick = { verMenuAjustes = !verMenuAjustes }) { Icon(imageVector = Icons.Filled.Settings, contentDescription = "Boton de Ajustes") }
            // Menu desplegable
            DropdownMenu(
                expanded = verMenuAjustes,
                onDismissRequest = { verMenuAjustes = false }
            ) {
                DropdownMenuItem(
                    onClick = {},
                    modifier = Modifier
                        .height(25.dp)
                        .width(90.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ThemeSwitcher(darkTheme = temaOscuro, onClick = onActualizarTema)
                    }
                }
            }
        }
    )
}

/**
 * ===================================================================
 */

/**
 * Limpiar procesos
 *
 */
fun limpiarProcesos() {
    listaDeProcesosGlobal.clear()
}