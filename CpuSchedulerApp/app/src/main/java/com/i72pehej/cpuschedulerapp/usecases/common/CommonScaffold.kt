package com.i72pehej.cpuschedulerapp.usecases.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.navigation.AppScreens
import com.i72pehej.cpuschedulerapp.util.appIcon

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
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = { CommonTopAppBar(navController) },
        content = content
    )
}

/**
 * ===================================================================
 */

/**
 * Funcion que crea la top bar comun de la app
 */
@Composable
fun CommonTopAppBar(navController: NavHostController) {
    // Funcion para volver a la pantalla de Home
    fun topIconButtonHome() {
        // Eliminar la pagina anterior de la navegacion
        navController.popBackStack()

        // Navegar a la pantalla de inicio
        navController.navigate(AppScreens.HomeScreen.route)
    }

    TopAppBar(
        title = {
            Row {
                IconButton(
                    onClick = { topIconButtonHome() }
                ) {
                    Icon(
                        painter = painterResource(id = appIcon),
                        contentDescription = "Icono principal de la App",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .size(90.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        },
        elevation = 10.dp
    )
}

/**
 * ===================================================================
 */