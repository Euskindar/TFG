package com.i72pehej.cpuschedulerapp.usecases.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.i72pehej.cpuschedulerapp.R

/**
 * @author Julen Perez Hernandez
 * Pantalla inicial en la que comenzar la navegacion por la app
 */
@Composable
//fun GraphsScreen(navController: NavHostController) {
fun GraphsScreen() {
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
        Text(text = stringResource(id = R.string.graphs_name))
//
//                CommonRoundedButton(
//                    text = stringResource(id = R.string.common_buttonNext),
//                    onClick = { navController.navigate(AppScreens.HomeScreen.route) }
//                )
    }
//        }
//    )
}