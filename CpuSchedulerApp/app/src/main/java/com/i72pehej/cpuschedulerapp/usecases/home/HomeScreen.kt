package com.i72pehej.cpuschedulerapp.usecases.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.i72pehej.cpuschedulerapp.navigation.AppScreens
import com.i72pehej.cpuschedulerapp.usecases.common.CommonScaffold
import com.i72pehej.cpuschedulerapp.util.Proceso

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
        navController,
        content = { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(scaffoldPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                Text(text = stringResource(id = R.string.home_name))

//                CommonRoundedButton(
//                    text = stringResource(id = R.string.common_buttonNext),
//                    onClick = { navController.navigate(AppScreens.TutorialScreen.route) }
//                    onClick = { algoritmoFifo(crearProcesosDePrueba()) }
//                )

                // Creamos una lista mutable de procesos, que utilizaremos para almacenar los procesos ingresados por el usuario
                val procesos = remember { mutableStateListOf<Proceso>() }

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    // Creamos el formulario de ingreso de procesos, y le pasamos una función que se llamará cuando se agregue un proceso
                    FormularioProceso {
                        procesos.add(it)
                    }

                    // Agregamos un espacio en blanco para separar el formulario de la tabla de procesos
                    Spacer(modifier = Modifier.height(16.dp))

                    // TODO -> Asignar el espacio entre elementos a la seccion de la tabla

                    // Creamos la tabla de procesos, y le pasamos la lista de procesos
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        TablaProcesos(procesos = procesos)
                    }

                    // Agregamos el botón "Siguiente"
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, false),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = { navController.navigate(AppScreens.TutorialScreen.route) },
                            modifier = Modifier
                                .padding(16.dp)
//                                .align(End)
                        ) {
                            Text("Siguiente")
                        }
                    }
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
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormularioProceso(onSubmit: (Proceso) -> Unit) {
    // Definimos tres variables para almacenar los datos del proceso que esta siendo ingresado
    var nombre by remember { mutableStateOf("") }
    var tiempoLlegada by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }

    // Estado para almacenar los errores del formulario
    var errorFormulario by remember { mutableStateOf("") }

    // Función para validar los campos del formulario y agregar un proceso a la lista de procesos ingresados
    fun agregarProceso() {
        // Validar los campos del formulario
        errorFormulario = when {
            nombre.isBlank() -> "Ingrese un nombre"
            tiempoLlegada.isBlank() -> "Ingrese un tiempo de llegada"
            !tiempoLlegada.isDigitsOnly() -> "Ingrese un número entero para el tiempo de llegada"
            duracion.isBlank() -> "Ingrese una duración"
            !duracion.isDigitsOnly() -> "Ingrese un número entero para la duración"

            else -> {
                // Si los campos son válidos, agregamos un nuevo proceso
                val proceso = Proceso(
                    nombre = nombre,
                    tiempoLlegada = tiempoLlegada.toInt(),
                    duracion = duracion.toInt()
                )
                onSubmit(proceso)

                // Limpiamos los campos del formulario
                nombre = ""
                tiempoLlegada = ""
                duracion = ""

                // Reseteamos el estado del error del formulario
                ""
            }
        }
    }

    // Contenido de la pagina
    Column {
        // Agregamos un titulo al formulario
        Text("Nuevo proceso", fontSize = 20.sp)

        // Creamos tres campos de texto para ingresar los datos del proceso
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Memory,
                    contentDescription = "Icono Nombre Proceso"
                )
            },
            singleLine = true
        )

        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = tiempoLlegada,
                onValueChange = { tiempoLlegada = it },
                label = { Text("Llegada") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(fraction = 0.5f),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Icono tiempo de llegada"
                    )
                },
                singleLine = true
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = duracion,
                onValueChange = { duracion = it },
                label = { Text("Duración") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.HourglassBottom,
                        contentDescription = "Icono duracion de proceso"
                    )
                },
                singleLine = true
            )
        }

        val keyboardController = LocalSoftwareKeyboardController.current

        // Creamos un boton para agregar el proceso ingresado a la lista de procesos, y llamamos a la funcion onSubmit cuando se hace clic en el boton
        Button(
            onClick =
            {
                agregarProceso()
                keyboardController?.hide()
            },
            modifier = Modifier.align(End)
        ) { Text("+") }
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
//    if (procesos.isNotEmpty()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(8.dp)
    ) {
        // Agregamos una fila para el encabezado de la tabla
        item {
            Row(
                modifier = Modifier.background(MaterialTheme.colors.secondary),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Nombre", modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    "Tiempo de llegada", modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    "Duración", modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    color = MaterialTheme.colors.onPrimary
                )
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
//    }
//    else {
//        // Si la lista de procesos está vacía, mostramos un mensaje indicando que no hay procesos
//        Text("No hay procesos ingresados", modifier = Modifier.padding(8.dp))
//    }
}

/**
 * ===========================================================================================
 */