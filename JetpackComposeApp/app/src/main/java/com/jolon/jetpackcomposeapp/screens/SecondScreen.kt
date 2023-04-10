package com.jolon.jetpackcomposeapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Home screen para la primera pantalla de la app

// JC funciona con funciones Coposable asi que
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SecondScreen(navController: NavController, text: String?) {
    // Agregamos el componente JC Scaffold() => Sirve como elemento grafico simple base para estructurar la pantalla
    Scaffold(topBar = {
        // Elemento para crear una barra superior, en este caso le damos tambien la funcionalidad para Volver
        TopAppBar() {
            // Agregamos el icono de flechita para volver
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Flecha para VOLVER",
                modifier = Modifier.clickable { navController.popBackStack() })
            // Separamos del texto
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "SecondScreen")
        }
    }) {
        SecondBodyContent(navController, text)
    }
}

// Funcion para el cuerpo de la pantalla
@Composable
fun SecondBodyContent(navController: NavController, text: String?) {
    // Funcionamiento de ejemplo
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Estamos en la SEGUNDA pantalla")

        // Comprobamos que el nuevo elemento pasado entre pantallas no sea null
        text?.let { Text(text = it) }

        Button(onClick = {
            // Le pasamos a el componente que controla la navrgacion la ruta de la siguiente pantalla
//            navController.navigate(route = AppScreens.HomeScreen.ruta)

            // la siguiente funcion hace lo mismo, en este caso, que es volver a la anterior
            navController.popBackStack()
        }) {
            Text(text = "Volver a Inicio")
        }
    }
}