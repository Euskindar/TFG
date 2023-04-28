package com.i72pehej.cpuschedulerapp.usecases.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.usecases.common.CommonRoundedButton
import com.i72pehej.cpuschedulerapp.usecases.common.CommonScaffold
import com.i72pehej.cpuschedulerapp.util.Proceso
import com.i72pehej.cpuschedulerapp.util.crearProcesosDePrueba

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===========================================================================================
 */

/**
 * Pantalla inicial en la que comenzar la navegacion por la app
 *
 * @param navController Control de navegacion
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    CommonScaffold(
        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = stringResource(id = R.string.home_name))

                CommonRoundedButton(
                    text = stringResource(id = R.string.common_buttonNext),
//                    onClick = { navController.navigate(AppScreens.TutorialScreen.route) }
                    onClick = { algoritmoFifo(crearProcesosDePrueba()) }
                )


                // Creamos una lista mutable de procesos, que utilizaremos para almacenar los procesos ingresados por el usuario
                val procesos = remember { mutableStateListOf<Proceso>() }

                Column(modifier = Modifier.padding(16.dp)) {
                    // Creamos el formulario de ingreso de procesos, y le pasamos una función que se llamará cuando se agregue un proceso
                    FormularioProceso {
                        procesos.add(it)
                    }

                    // Agregamos un espacio en blanco para separar el formulario de la tabla de procesos
                    Spacer(modifier = Modifier.height(16.dp))

                    // Creamos la tabla de procesos, y le pasamos la lista de procesos
                    TablaProcesos(procesos = procesos)
                }

            }
        }
    )
}

/**
 * ===========================================================================================
 */

/**
 * Formulario de ingreso de procesos
 *
 * @param onSubmit Se encarga de agregar el proceso a la lista de procesos y limpiar el formulario.
 */
@Composable
fun FormularioProceso(onSubmit: (Proceso) -> Unit) {
    // Definimos tres variables para almacenar los datos del proceso que esta siendo ingresado
    var nombre by remember { mutableStateOf("") }
    var tiempoLlegada by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }

    Column {
        // Agregamos un titulo al formulario
        Text("Nuevo proceso", fontSize = 20.sp)

        // Creamos tres campos de texto para ingresar los datos del proceso
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = tiempoLlegada,
            onValueChange = { tiempoLlegada = it },
            label = { Text("Tiempo de llegada") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = duracion,
            onValueChange = { duracion = it },
            label = { Text("Duracion") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Creamos un boton para agregar el proceso ingresado a la lista de procesos, y llamamos a la funcion onSubmit cuando se hace clic en el boton
        Button(
            onClick = {
                val proceso = Proceso(
                    nombre = nombre,
                    tiempoLlegada = tiempoLlegada.toInt(),
                    duracion = duracion.toInt()
                )
                onSubmit(proceso)
                nombre = ""
                tiempoLlegada = ""
                duracion = ""
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("+ Agregar")
        }
    }
}

/**
 * ===========================================================================================
 */

/**
 * Creacion la tabla de procesos
 *
 * @param procesos Lista de los procesos
 */
@Composable
fun TablaProcesos(procesos: List<Proceso>) {
    // Si la lista de procesos no está vacía, creamos una tabla utilizando LazyColumn
    if (procesos.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            // Agregamos una fila para el encabezado de la tabla
            item {
                Row(modifier = Modifier.background(Color.Gray)) {
                    Text("Nombre", modifier = Modifier
                        .weight(1f)
                        .padding(4.dp))
                    Text("Tiempo de llegada", modifier = Modifier
                        .weight(1f)
                        .padding(4.dp))
                    Text("Duración", modifier = Modifier
                        .weight(1f)
                        .padding(4.dp))
                }
            }
            // Agregamos una fila para cada proceso en la lista de procesos
            items(procesos) { proceso ->
                Row(modifier = Modifier.padding(4.dp)) {
                    Text(proceso.nombre, modifier = Modifier.weight(1f))
                    Text(proceso.tiempoLlegada.toString(), modifier = Modifier.weight(1f))
                    Text(proceso.duracion.toString(), modifier = Modifier.weight(1f))
                }
            }
        }
    } else {
        // Si la lista de procesos está vacía, mostramos un mensaje indicando que no hay procesos
        Text("No hay procesos ingresados", modifier = Modifier.padding(8.dp))
    }
}

/**
 * ===========================================================================================
 */