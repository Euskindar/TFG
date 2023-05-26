package com.i72pehej.cpuschedulerapp.usecases.common

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.i72pehej.cpuschedulerapp.util.appIconColor
import com.i72pehej.cpuschedulerapp.util.extensions.ThemeSwitcher
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
    temaOscuro: Boolean,
    onActualizarTema: () -> Unit,
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            CommonTopAppBar(
                navController,
                scope,
                scaffoldState,
                temaOscuro,
                onActualizarTema
            )
        },
        content = content,
        // Menu de navegacion lateral
        drawerContent = { MenuLateral(navController, scope, scaffoldState) }
    )
}

/**
 * ===================================================================
 */

/**
 * Menu lateral en el que agregar distintos apartados
 */
@Composable
fun MenuLateral(
    navController: NavHostController,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    val menuList = listOf("Tutorial", "FAQ")

    // Contenido del menu
    Column {
        Image(
            painter = painterResource(id = appIconColor),
            contentDescription = "Icono principal de la app",
            modifier = Modifier.fillMaxWidth(),
        )

        // Separador para estetica
        Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.padding(15.dp))

        // Listado de elementos del menu
        menuList.forEachIndexed { position, item ->
            when (position) {
                // Seleccionado el primer elemento de la lista
                0 -> TextButton(onClick = {
                        navController.navigate(AppScreens.TutorialScreen.route) {
                            launchSingleTop = true
                        }
                        scope.launch { scaffoldState.drawerState.close() }
                    }) {
                        Text(
                            text = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp),
                            fontSize = 16.sp
                        )
                    }

                // Seleccionado el segundo elemento de la lista
                1 -> TextButton(onClick = {
                    navController.navigate(AppScreens.TutorialScreen.route) {
                        launchSingleTop = true
                    }
                    scope.launch { scaffoldState.drawerState.close() }
                }) {
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
    scaffoldState: ScaffoldState,
    temaOscuro: Boolean,
    onActualizarTema: () -> Unit
) {
    // Funcion para volver a la pantalla de Home
    fun topIconButtonHome() {
        // Eliminar la pagina anterior de la navegacion
        navController.popBackStack()

        // Navegar a la pantalla de inicio
        navController.navigate(AppScreens.HomeScreen.route)
    }

    // Control del boton de back para cerrar menu lateral
    BackHandler(scaffoldState.drawerState.isOpen) {
        scope.launch { scaffoldState.drawerState.close() }
    }

    // Control del menu de ajustes desplegable
    var verMenuAjustes by remember { mutableStateOf(false) }

    // Barra superior de la pantalla
    TopAppBar(
        title = {
            Row {
                IconButton(
                    onClick = { topIconButtonHome() },
                ) {
                    Icon(
                        painter = painterResource(id = appIconColor),
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
        // Menu desplegable para ajustes basicos
        actions = {
            // Icono de ajustes que cambia el estado del menu
            IconButton(onClick = { verMenuAjustes = !verMenuAjustes }) {
                Icon(imageVector = Icons.Filled.Settings, contentDescription = "Boton de Ajustes")
            }
            // Menu desplegable
            DropdownMenu(
                expanded = verMenuAjustes,
                onDismissRequest = { verMenuAjustes = false }
            ) {
                DropdownMenuItem(
                    onClick = { /*TODO*/ },
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