package com.i72pehej.cpuschedulerapp.usecases.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.navigation.AppScreens
import com.i72pehej.cpuschedulerapp.util.appIcon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { CommonTopAppBar(navController, scope, scaffoldState) },
        content = content,
        // Menu de navegacion lateral
        drawerContent = { MenuLateral() }
    )
}

/**
 * ===================================================================
 */

/**
 * Menu lateral para agregar distintos apartados
 */
@Composable
fun MenuLateral() {
    val menu_list = listOf("Basura 1", "Basura 2", "Basura 3", "Basura 4")

    // Contenido del menu
    Column {
        Image(
            painter = painterResource(id = appIcon),
            contentDescription = "Icono principal de la app",
            modifier = Modifier.fillMaxWidth(),
        )

        // Separador para estetica
        Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(15.dp))

        // Listado de elementos del menu
        menu_list.forEach { item ->
            // TODO ->  Cambiar TextButton por IconButton al cual se le agrega el texto para hacer la navegacion mas amigable
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}

/**
 * ===================================================================
 */

/**
 * Funcion que crea la top bar comun de la app
 *
 * @param navController Control de navegacion
 * @param scope Control de apertura del menu lateral
 * @param scaffoldState Control de estado del menu
 */
@Composable
fun CommonTopAppBar(
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
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
                    onClick = { topIconButtonHome() },
                ) {
                    Icon(
                        painter = painterResource(id = appIcon),
                        contentDescription = "Icono principal de la App",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .size(90.dp),
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
        },
        // Menu lateral de navegacion
        navigationIcon = {
            IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Icono de Menu")
            }
        },
        elevation = 10.dp,
    )
}

/**
 * ===================================================================
 */