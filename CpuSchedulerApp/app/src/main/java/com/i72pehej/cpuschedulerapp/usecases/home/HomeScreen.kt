package com.i72pehej.cpuschedulerapp.usecases.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavHostController
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.navigation.CrearTabs
import com.i72pehej.cpuschedulerapp.usecases.common.CommonRoundedButton
import com.i72pehej.cpuschedulerapp.usecases.common.CommonScaffold
import com.i72pehej.cpuschedulerapp.util.Proceso
import com.i72pehej.cpuschedulerapp.util.algoritmo

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
fun HomeScreen(
    navController: NavHostController,
    temaOscuro: Boolean,
    onActualizarTema: () -> Unit
) {
    // Variable para guardar el estado del menu lateral
    val scaffoldState = rememberScaffoldState()

    // Control para abrir o cerrar el menu lateral
    val scope = rememberCoroutineScope()

    // Disposicion principal de la pantalla
    CommonScaffold(
        temaOscuro = temaOscuro,
        onActualizarTema = onActualizarTema,
        navController = navController,
        scope = scope,
        scaffoldState = scaffoldState,
//        content = { ContenidoHome(scaffoldPadding = it) }
        content = { CrearTabs() }
    )
}

/**
 * ===========================================================================================
 */

/**
 * Contenido de la pagina para introducir en el scaffold
 */
@Composable
fun ContenidoHome() {
    // Contenedor padre de los elementos a mostrar en la pagina
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = CenterHorizontally
    ) {
//                Text(text = stringResource(id = R.string.home_name))

        // Creamos una lista mutable de procesos, que utilizaremos para almacenar los procesos ingresados por el usuario
        val procesos = remember { mutableStateListOf<Proceso>() }

        // Contenedor de los elementos principales
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            // Creamos el formulario de ingreso de procesos, y le pasamos una función que se llamará cuando se agregue un proceso
            FormularioProceso { proceso -> procesos.add(proceso) }

            // Agregamos un espacio en blanco para separar el formulario de la tabla de procesos
            Spacer(modifier = Modifier.height(16.dp))

            // Creamos la tabla de procesos, y le pasamos la lista de procesos
            TablaProcesos(procesos = procesos)

            Spacer(modifier = Modifier.height(16.dp))

            // Agregamos el botón "Siguiente"
            Row(
                modifier = Modifier.weight(1f, false),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                CommonRoundedButton(
                    text = stringResource(id = R.string.common_buttonNext),
                    isEnabled = procesos.isNotEmpty(),
                    onClick = { llamarAlgoritmo(procesos) }
                )
            }
        }
    }
}

/**
 * ===========================================================================================
 */

/**
 * Llamada a la ejecucion de cada algoritmo dependiendo de la opcion seleccionada en el formulario
 *
 * @param procesos Listado de procesos con los que operar
 */
fun llamarAlgoritmo(procesos: SnapshotStateList<Proceso>) {
    when (algoritmo) {
        // FIFO
        0 -> {
            algoritmoFifo(procesos)
        }
        // RoundRobin
        // 1 -> algoritmoRoundRobin(procesos)
    }
}

/**
 * ===========================================================================================
 */

/**
 * Formulario de ingreso de procesos
 *
 * @param onSubmit Se encarga de agregar el proceso a la lista de procesos y limpiar el formulario.
 */
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun FormularioProceso(onSubmit: (Proceso) -> Unit) {
    // Definimos tres variables para almacenar los datos del proceso que esta siendo ingresado
    var nombre by remember { mutableStateOf("") }
    var tiempoLlegada by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }

    // Estado para almacenar los errores del formulario
    var errorFormulario by remember { mutableStateOf("") }
    var errorNombre by remember { mutableStateOf(false) }
    var errorLlegada by remember { mutableStateOf(false) }
    var errorDuracion by remember { mutableStateOf(false) }

    // Función para validar los campos del formulario y agregar un proceso a la lista de procesos ingresados
    @Composable
    fun agregarProceso() {
        // Validar los campos del formulario
        errorNombre = when {
            nombre.isBlank() -> true
            else -> false
        }

        errorLlegada = when {
            tiempoLlegada.isBlank() -> true
            !tiempoLlegada.isDigitsOnly() -> true
            else -> false
        }

        errorDuracion = when {
            duracion.isBlank() -> true
            !duracion.isDigitsOnly() -> true
            else -> false
        }

        errorFormulario = when {
            nombre.isBlank() -> stringResource(R.string.error_nombre)
            tiempoLlegada.isBlank() -> stringResource(R.string.error_llegada_blank)
            !tiempoLlegada.isDigitsOnly() -> stringResource(R.string.error_llegada_digit)
            duracion.isBlank() -> stringResource(R.string.error_duracion_blank)
            !duracion.isDigitsOnly() -> stringResource(R.string.error_duracion_digit)

            // Si los campos son válidos, agregamos un nuevo proceso
            else -> {
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
        // Menu para seleccionar el metodo a utilizar
        val algoritmosImplementados = listOf("FIFO", "RoundRobin", "etc...")
        val defAlgorithm = 0

        var expandir by remember { mutableStateOf(value = false) }
        var algoritmoSeleccionado by remember { mutableStateOf(value = algoritmosImplementados[defAlgorithm]) }

        ExposedDropdownMenuBox(
            expanded = expandir,
            onExpandedChange = { expandir = !expandir }
        ) {
            TextField(
                readOnly = true,
                value = algoritmoSeleccionado,
                onValueChange = { },
                label = { Text("Método") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandir) },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandir,
                onDismissRequest = { expandir = false }
            ) {
                algoritmosImplementados.forEachIndexed { posicion, opcionSeleccionada ->
                    DropdownMenuItem(
                        onClick = {
                            algoritmoSeleccionado = opcionSeleccionada
                            expandir = false

                            // Guardado de la opcion seleccionada
                            algoritmo = posicion
                        }
                    ) { Text(text = opcionSeleccionada) }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Agregamos un titulo al formulario
        Text(stringResource(id = R.string.nuevo_proceso), fontSize = 20.sp)

        // Creamos tres campos de texto para ingresar los datos del proceso
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text(stringResource(id = R.string.formulario_nombre)) },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Memory,
                    contentDescription = "Icono Nombre Proceso"
                )
            },
            singleLine = true,
            isError = errorNombre
        )

        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = tiempoLlegada,
                onValueChange = { tiempoLlegada = it },
                label = { Text(stringResource(id = R.string.formulario_llegada)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(fraction = 0.5f),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = "Icono tiempo de llegada"
                    )
                },
                singleLine = true,
                isError = errorLlegada
            )

            Spacer(modifier = Modifier.width(10.dp))

            OutlinedTextField(
                value = duracion,
                onValueChange = { duracion = it },
                label = { Text(stringResource(id = R.string.formulario_duracion)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.HourglassBottom,
                        contentDescription = "Icono duracion de proceso"
                    )
                },
                singleLine = true,
                isError = errorDuracion
            )
        }

        // Mensaje de error
        if (errorFormulario.isNotBlank()) {
            Text(
                text = errorFormulario,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.align(CenterHorizontally)
            )
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Control de estado de ejecucion de funcion agregarProceso()
        var procesoAgregado by remember {
            mutableStateOf(false)
        }

        // Control para ocultar el teclado y perder el foco del formulario al terminar de agregar cada proceso
        val keyboardController = LocalSoftwareKeyboardController.current
        val focusManager = LocalFocusManager.current

        // Creamos un boton para agregar el proceso ingresado a la lista de procesos, y llamamos a la funcion onSubmit cuando se hace clic en el boton
        Button(
            onClick =
            {
                procesoAgregado = true
                // Limpiar el foco para ocultar teclado y deseleccionar el campo del formulario
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            modifier = Modifier.align(End)
        ) { Text("+") }

        // Si es correcto se agrega el proceso y reinicia estado
        if (procesoAgregado) {
            agregarProceso()
            procesoAgregado = false
        }
    }
}

/**
 * ===========================================================================================
 */

/**
 * Creacion de la tabla de procesos agregados
 *
 * @param procesos Lista de los procesos agregados
 */
@Composable
fun TablaProcesos(procesos: List<Proceso>) {
    // Si la lista de procesos no está vacía
    if (procesos.isNotEmpty()) {
        // Creamos una tabla utilizando LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            // Agregamos una fila para el encabezado de la tabla
            item {
                Row(
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(4.dp)
                ) {
                    // Agregamos cada columna a la fila de encabezado con un peso de 1f para que tengan el mismo ancho
                    Text(
                        stringResource(id = R.string.formulario_nombre),
                        modifier = Modifier.weight(1f), // Le damos un peso de 1f para que tenga el mismo ancho que las otras columnas.
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.formulario_llegada),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        stringResource(id = R.string.formulario_duracion),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Agregamos una fila para cada proceso en la lista de procesos
            items(procesos) { proceso ->
                Row(modifier = Modifier.padding(4.dp)) {
                    Text(
                        proceso.nombre,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.tiempoLlegada.toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        proceso.duracion.toString(),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    } else {
        // Si la lista de procesos está vacía, mostramos un mensaje indicando que no hay procesos
        Text(
            stringResource(id = R.string.tabla_vacia),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
    }
}

/**
 * ===========================================================================================
 */