package com.i72pehej.cpuschedulerapp.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Toc
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.i72pehej.cpuschedulerapp.navigation.HomeTabs.*
import com.i72pehej.cpuschedulerapp.usecases.home.ContenidoHome
import com.i72pehej.cpuschedulerapp.usecases.results.GraphsScreen
import com.i72pehej.cpuschedulerapp.usecases.results.ResultsScreen
import kotlinx.coroutines.launch

/**
 * @author Julen Perez Hernandez
 */

// Alias para la funcion correspondiente a los elementos de cada pantalla
typealias NuevoTab = @Composable () -> Unit

/**
 * Constructor para el control de los tabs de la pantalla Home
 *
 * @constructor Crea una clase para la gestion de los tabs
 */
sealed class HomeTabs(
    var icono: ImageVector,
    var titulo: String,
    var pantalla: NuevoTab
) {
    object TabInicio :
        HomeTabs(icono = Icons.Default.Home, titulo = "Inicio", { ContenidoHome() })

    object TabResultados :
        HomeTabs(icono = Icons.Default.Toc, titulo = "Resultados", { ResultsScreen() })

    object TabGraficos :
        HomeTabs(icono = Icons.Filled.Equalizer, titulo = "Gáficos", { GraphsScreen() })
}

/**
 * ===========================================================================================
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CrearTabs() {// Lista para almacenar los elementos de los tabs
    val tabs = listOf(TabInicio, TabResultados, TabGraficos)

    // Control del paginador que contiene los tabs
    val pagerState = rememberPagerState()

    // Contenido del paginador
    Column {
        Tabs(tabs, pagerState)
        Tabs_content(tabs, pagerState)
    }
}

/**
 * ===========================================================================================
 */

/**
 * Control del cambio de estado del pager state
 *
 * @param tabs Lista de los elementos a colocar en cada tab
 * @param pagerState Controlador del estado de los tabs
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs_content(tabs: List<HomeTabs>, pagerState: PagerState) {
    // Paginador que muestra los tabs horizontalmente y permite desplazarse entre ellos
    HorizontalPager(state = pagerState, count = tabs.size) { page: Int ->
        tabs[page].pantalla()
    }
}

/**
 * ===========================================================================================
 */

/**
 * Funcion para crear el contenedor de los tabs
 *
 * @param tabs Lista de los elementos a colocar en cada tab
 * @param pagerState Controlador del estado de los tabs
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<HomeTabs>, pagerState: PagerState) {
    // Control de contexto
    val scope = rememberCoroutineScope()

    // Fila de los elementos del paginador
    TabRow(selectedTabIndex = pagerState.currentPage, indicator = { tabPositions ->
        TabRowDefaults.Indicator(
            modifier = Modifier.pagerTabIndicatorOffset(
                pagerState = pagerState,
                tabPositions = tabPositions
            )
        )
    }) {
        // Creacion de cada elemento
        tabs.forEachIndexed { index, homeTabs ->
            LeadingIconTab(
                selected = pagerState.currentPage == index,
                // Control del click para que se anime la transicion a la pagina indicada
                onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                icon = {
                    Icon(
                        imageVector = homeTabs.icono,
                        contentDescription = "Icono del tab asociado"
                    )
                },
                text = { Text(text = homeTabs.titulo) }
            )
        }
    }
}