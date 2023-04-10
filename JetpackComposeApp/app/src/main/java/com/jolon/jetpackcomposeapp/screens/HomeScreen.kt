package com.jolon.jetpackcomposeapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.jolon.jetpackcomposeapp.navigation.AppScreens

// Home screen para la primera pantalla de la app

// JC funciona con funciones Coposable asi que
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {
    // Agregamos el componente JC Scaffold() => Sirve como elemento grafico simple base para estructurar la pantalla
    Scaffold(topBar = {
        // Elemento para crear una barra superior
        TopAppBar() {
            Text(text = "HomeScreen")
        }
    }) {
        BodyContent(navController)
    }
}

// Funcion para el cuerpo de la pantalla
@Composable
fun BodyContent(navController: NavController) {
    // Funcionamiento de ejemplo
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Estamos en la pantalla de INICIO")
        Button(onClick = {
            // Le pasamos a el componente que controla la navegacion la ruta de la siguiente pantalla
            // Le concateno los argumentos que quiero pasar a la segunda pantalla con "/" para indicar que es parte del path
            navController.navigate(route = AppScreens.SecondScreen.ruta + "/A tomar por culo")
        }) {
            Text(text = "Navega")
        }
    }
}