package com.i72pehej.cpuschedulerapp.usecases.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.navigation.AppScreens
import com.i72pehej.cpuschedulerapp.usecases.common.CommonRoundedButton
import com.i72pehej.cpuschedulerapp.usecases.common.CommonScaffold

/**
 * @author Julen Perez Hernandez
 * Pantalla inicial en la que comenzar la navegacion por la app
 *
 * @param navController Control de navegacion
 */
@Composable
//fun ResultsScreen(navController: NavHostController) {
fun ResultsScreen() {
    // Variable para guardar el estado del menu lateral
    val scaffoldState = rememberScaffoldState()

    // Control para abrir o cerrar el menu lateral
    val scope = rememberCoroutineScope()

    // Disposicion principal de la pantalla
//    CommonScaffold(
//        navController = navController,
//        scope = scope,
//        scaffoldState = scaffoldState,
//        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize(),
//                    .padding(scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.results_name))
//
//                CommonRoundedButton(
//                    text = stringResource(id = R.string.common_buttonNext),
//                    onClick = { navController.navigate(AppScreens.GraphsScreen.route) }
//                )
            }
//        }
//    )
}