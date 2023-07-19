package com.i72pehej.cpuschedulerapp.util.extensions

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @author Julen Perez Hernandez
 */

/**
 * Boton que visualiza el cambio de tema de la app
 *
 * @param darkTheme Booleano para controlar el cambio de tema
 * @param size Tamaño del boton que cambia el tema (el contenedor padre tiene el doble = size*2)
 * @param iconSize Tamaño de los iconos
 * @param padding Padding entre el contenedor base y el boton
 * @param borderWidth Anchura del borde del contenedor padre
 * @param parentShape Forma del contenedor padre
 * @param toggleShape Forma del boton
 * @param animationSpec Animacion que realiza el boton para intercambiar entre temas
 * @param onClick Accion que realiza el boton
 */
@Composable
fun ThemeSwitcher(
    darkTheme: Boolean = false,
    size: Dp = 30.dp,
    iconSize: Dp = size / 3,
    padding: Dp = 4.dp,
    borderWidth: Dp = 1.dp,
    parentShape: RoundedCornerShape = CircleShape,
    toggleShape: RoundedCornerShape = CircleShape,
    animationSpec: AnimationSpec<Dp> = tween(durationMillis = 300),
    onClick: () -> Unit
) {
    // Control de la posicion del boton que indica el tema en el que estamos
    val offset by animateDpAsState(
        targetValue = if (darkTheme) 0.dp else size,
        animationSpec = animationSpec
    )

    // Contenedor padre que da forma al contenedor exterior
    Box(
        modifier = Modifier
            .width(size * 2)
            .height(size)
            .clip(shape = parentShape)
            .clickable { onClick() }
            .background(MaterialTheme.colors.primaryVariant)
    ) {
        // Contenedor hijo que da forma al boton circular que cambia entre temas
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = padding)
                .clip(shape = toggleShape)
                .background(MaterialTheme.colors.primary)
        )

        // Fila para los iconos de los temas claro y oscuro
        Row(
            modifier = Modifier.border(
                border = BorderStroke(
                    width = borderWidth,
                    color = MaterialTheme.colors.primary
                ),
                shape = parentShape
            )
        ) {
            // Contenedor del icono del tema oscuro
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.Nightlight,
                    contentDescription = "Icono de tema oscuro",
                    // Cambio de tonos dependiendo del tema
                    tint = if (darkTheme) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary
                )
            }

            // Contenedor del icono del tema claro
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = Icons.Default.LightMode,
                    contentDescription = "Icono de tema claro",
                    tint = if (darkTheme) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant
                )
            }
        }
    }
}