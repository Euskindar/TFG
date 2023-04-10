package com.i72pehej.cpuschedulerapp.usecases.results

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
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
import com.i72pehej.cpuschedulerapp.usecases.common.CommonRoundedButton

/**
 * @author Julen Perez Hernandez
 * Pantalla inicial en la que comenzar la navegacion por la app
 *
 * @param navController Control de navegacion
 */
@Composable
fun ResultsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.logos_version_uco),
                            contentDescription = "Icono principal de la App",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterVertically)
                                .size(90.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(id = R.string.app_name),
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            )
        }

    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.results_name))

            CommonRoundedButton(
                text = stringResource(id = R.string.common_buttonNext),
                onClick = { navController.navigate(AppScreens.GraphsScreen.route) }
            )
        }
    }
}