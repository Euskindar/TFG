package com.i72pehej.cpuschedulerapp.usecases.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.i72pehej.cpuschedulerapp.util.buttonBorderWith
import com.i72pehej.cpuschedulerapp.util.buttonCornerRadius
import com.i72pehej.cpuschedulerapp.util.buttonDefaultPadding

/**
 * @author Julen Perez Hernandez
 * Boton base para las acciones basicas de la aplicacion
 *
 * @param text Texto del boton
 * @param onClick Accion al hacer click en el boton
 * @param modifier Modificadores de apariencia
 */
@Composable
fun CommonRoundedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(buttonDefaultPadding.dp),
        shape = RoundedCornerShape(buttonCornerRadius),
        border = BorderStroke(buttonBorderWith.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface
        )
    ) {
        Text(text)
    }
}