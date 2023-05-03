package com.i72pehej.cpuschedulerapp.usecases.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun TutorialScreen(navController: NavHostController) {
    CommonScaffold(
        navController,
        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.tutorial_name))

                CommonRoundedButton(
                    text = stringResource(id = R.string.common_buttonNext),
                    onClick = { navController.navigate(AppScreens.ResultsScreen.route) }
                )
            }
        }
    )
}