package com.i72pehej.cpuschedulerapp.usecases.home

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
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.i72pehej.cpuschedulerapp.R
import com.i72pehej.cpuschedulerapp.navigation.CrearTabs
import com.i72pehej.cpuschedulerapp.usecases.algorithms.algoritmoFifo
import com.i72pehej.cpuschedulerapp.usecases.algorithms.algoritmoRoundRobin
import com.i72pehej.cpuschedulerapp.usecases.common.CommonRoundedButton
import com.i72pehej.cpuschedulerapp.usecases.common.CommonScaffold
import com.i72pehej.cpuschedulerapp.util.Proceso
import com.i72pehej.cpuschedulerapp.util.anchuraFormularioNombres
import com.i72pehej.cpuschedulerapp.util.anchuraFormularioTiempos
import com.i72pehej.cpuschedulerapp.util.crearProceso
import com.i72pehej.cpuschedulerapp.util.extensions.ConfirmacionBackPress
import com.i72pehej.cpuschedulerapp.util.extensions.TablaProcesos
import com.i72pehej.cpuschedulerapp.util.infoResultadosGlobal
import com.i72pehej.cpuschedulerapp.util.listaDeProcesosGlobal
import com.i72pehej.cpuschedulerapp.util.selectorAlgoritmo
import com.i72pehej.cpuschedulerapp.util.siguienteSeleccionado
import com.i72pehej.cpuschedulerapp.util.tiempoQuantum

/**
 * @author Julen Perez Hernandez
 */

/**
 * ===========================================================================================
 */

/**
 * Pantalla inicial en la que comenzar la navegacion por la app
 *
// * @param navController Control de navegacion
 * @param temaOscuro Control para el switch del tema de la app
 * @param onActualizarTema Funcion para actualizar el tema
 */
@Composable
fun HomeScreen(
//    navController: NavHostController,
    temaOscuro: Boolean,
    onActualizarTema: () -> Unit
) {
    // Inicializacion del control de salida por error al pulsar back
    ConfirmacionBackPress()

    // Variable para guardar el estado del contenido
    val scaffoldState = rememberScaffoldState()

    // Disposicion principal de la pantalla
    CommonScaffold(
        temaOscuro = temaOscuro,
        onActualizarTema = onActualizarTema,
        scaffoldState = scaffoldState,
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
        modifier = Modifier.fillMaxSize()
    ) {
        // Contenedor de los elementos principales
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
        ) {
            // Creamos el formulario de ingreso de procesos, y le pasamos una función que se llamará cuando se agregue un proceso
            FormularioProceso { proceso -> listaDeProcesosGlobal.add(proceso) }

            // Separador horizontal para la tabla
            Spacer(modifier = Modifier.width(8.dp))

            // Creamos la tabla de procesos, y le pasamos la lista de procesos
            TablaProcesos(procesos = listaDeProcesosGlobal)
        }
    }
}

/**
 * ===========================================================================================
 */

/**
 * Llamada a la ejecucion de cada algoritmo dependiendo de la opcion seleccionada en el formulario
 */
fun llamarAlgoritmo() {
    when (selectorAlgoritmo) {
        // FIFO
        0 -> {
            infoResultadosGlobal = algoritmoFifo()
        }
        // RoundRobin
        1 -> {
            infoResultadosGlobal = algoritmoRoundRobin()
        }
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
    // Variables para el menu para seleccionar el metodo a utilizar
    val algoritmosImplementados = listOf("FIFO", "RoundRobin", "etc...")

    var expandir by remember { mutableStateOf(value = false) }
    var algoritmoSeleccionado by remember { mutableStateOf(value = algoritmosImplementados[selectorAlgoritmo]) }

    // Control de estado para agregar los procesos en agregarProceso()
    var procesoAgregado by remember { mutableStateOf(false) }

    // Control de estado de ejecucion de funcion agregarProceso()
    var quantumSeleccionado by remember { mutableStateOf(false) }

    // Control para ocultar el teclado y perder el foco del formulario al terminar de agregar cada proceso
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    // Definimos tres variables para almacenar los datos del proceso que esta siendo ingresado
    var nombre by remember { mutableStateOf("") }
    var tiempoLlegada by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var quantum by remember { mutableStateOf(tiempoQuantum) }

    // Estado para almacenar los errores del formulario
    var errorFormulario by remember { mutableStateOf("") }
    var errorNombre by remember { mutableStateOf(false) }
    var errorLlegada by remember { mutableStateOf(false) }
    var errorDuracion by remember { mutableStateOf(false) }

    var errorFormularioQuantum by remember { mutableStateOf("") }
    var errorQuantum by remember { mutableStateOf(false) }

    // Control de visibilidad de campo de quantum
    val quantumEnabled: Boolean
    val quantumVisibilityAlpha: Float
    if (selectorAlgoritmo == 1) {
        quantumEnabled = true
        quantumVisibilityAlpha = 1f
    } else {
        quantumEnabled = false
        quantumVisibilityAlpha = 0f
    }

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

        // Comprobacion de formulario completo correctamente
        errorFormulario = when {
            nombre.isBlank() -> stringResource(R.string.error_nombre)
            tiempoLlegada.isBlank() -> stringResource(R.string.error_llegada_blank)
            !tiempoLlegada.isDigitsOnly() -> stringResource(R.string.error_llegada_digit)
            duracion.isBlank() -> stringResource(R.string.error_duracion_blank)
            !duracion.isDigitsOnly() -> stringResource(R.string.error_duracion_digit)

            // Si los campos son válidos, agregamos un nuevo proceso
            else -> {
                onSubmit(
                    crearProceso(
                        nombre = nombre,
                        tiempoLlegada = tiempoLlegada.toInt(),
                        duracion = duracion.toInt()
                    )
                )

                // Limpiamos los campos del formulario
                nombre = ""
                tiempoLlegada = ""
                duracion = ""

                // Reseteamos el estado del error del formulario
                ""
            }
        }
    }

    // Funcion interna para controlar que se haya seleccionado un quantum
    @Composable
    fun comprobarQuantum() {
        // Control de errores solo para RR
        if (selectorAlgoritmo == 1) {
            errorQuantum = when {
                quantum.isBlank() -> true
                !quantum.isDigitsOnly() -> true
                else -> false
            }

            // Creacion de mensaje de error en el campo del quantum para RR
            errorFormularioQuantum = when {
                quantum.isBlank() -> stringResource(R.string.error_quantum_blank)
                !quantum.isDigitsOnly() -> stringResource(R.string.error_quantum_digit)
                else -> {
                    ""
                }
            }
        }
    }

    // Contenedor para la primera columna de campos del formulario
    Column {
        // Separador para alinear los campos
        Spacer(modifier = Modifier.height(8.dp))

        // Menu desplegable para seleccion de algoritmo
        ExposedDropdownMenuBox(
            modifier = Modifier.width(anchuraFormularioNombres.dp),
            expanded = expandir,
            onExpandedChange = { expandir = !expandir }
        ) {
            TextField(
                readOnly = true,
                value = algoritmoSeleccionado,
                onValueChange = { },
                label = { Text("Método", fontSize = 12.sp) },
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
                            selectorAlgoritmo = posicion
                        }
                    ) { Text(text = opcionSeleccionada) }
                }
            }
        }

        // Creamos el campo de texto para ingresar el nombre del proceso
        OutlinedTextField(
            value = nombre,
            onValueChange = {
                // Control de cantidad de caracteres
                if (it.length <= 3) nombre = it
            },
            label = {
                Text(text = stringResource(id = R.string.formulario_nombre))
                // Mensaje visual para que el usuario conozca la cantidad de caracteres permitidos
                Text(
                    text = "${nombre.length} / 3",
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),   // Primera letra en mayuscula
            keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Right) }),
            modifier = Modifier.width(anchuraFormularioNombres.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Memory,
                    contentDescription = "Icono Nombre Proceso"
                )
            },
            singleLine = true,
            isError = errorNombre
        )

        // Creamos un boton para agregar el proceso ingresado a la lista de procesos, y llamamos a la funcion onSubmit cuando se hace clic en el boton
        Button(
            modifier = Modifier.padding(start = 15.dp, top = 10.dp),
            onClick =
            {
                procesoAgregado = true
                // Limpiar el foco para ocultar teclado y deseleccionar el campo del formulario
                keyboardController?.hide()
                focusManager.clearFocus()
            },
        ) { Text(text = "+", fontWeight = FontWeight.Bold, fontSize = 15.sp) }

        // Mensaje de error
        if (errorFormulario.isNotBlank()) {
            Text(
                text = errorFormulario,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(anchuraFormularioNombres.dp)
            )
        } else if (errorFormularioQuantum.isNotBlank()) {
            Text(
                text = errorFormularioQuantum,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(anchuraFormularioNombres.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Agregamos el botón "Siguiente" que llame a la funcion correspondiente al metodo seleccionado
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom
        ) {
            CommonRoundedButton(
                text = stringResource(id = R.string.common_buttonNext),
                isEnabled = listaDeProcesosGlobal.isNotEmpty(),
                onClick = {
                    // Llama a la funcion que controla el algoritmo a ejecutar
                    llamarAlgoritmo()

                    // Control de estado para indicar la comprobacion de errores en el campo de quantum
                    quantumSeleccionado = true

                    // Cambio de estado para indicar la pulsacion del boton y cambiar a la pagina siguiente
                    siguienteSeleccionado.value = true
                },
                modifier = Modifier
                    .width(anchuraFormularioNombres.dp)
                    .align(CenterHorizontally)
            )
        }
    }

    // Separador para los campos de la segunda columna
    Spacer(modifier = Modifier.width(8.dp))

    // Contenedor para la segunda columna de campos del formulario
    Column {
        // Campo para introducir el quantum para Round Robin
        OutlinedTextField(
            enabled = quantumEnabled,
            value = quantum,
            onValueChange = {
                // Control de cantidad de caracteres
                if (it.length <= 2) {
                    quantum = it
                    tiempoQuantum = it
                }
            },
            label = { Text(stringResource(id = R.string.formulario_quantum)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier
                .width(anchuraFormularioTiempos.dp)
                .alpha(quantumVisibilityAlpha),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Update,
                    contentDescription = "Icono quantum de proceso en RR"
                )
            },
            singleLine = true,
            isError = errorQuantum
        )

        // Campo para introducir el tiempo de llegada
        OutlinedTextField(
            value = tiempoLlegada,
            onValueChange = {
                // Control de cantidad de caracteres
                if (it.length <= 2) tiempoLlegada = it
            },
            label = { Text(stringResource(id = R.string.formulario_llegada)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Next) }),
            modifier = Modifier.width(anchuraFormularioTiempos.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = "Icono tiempo de llegada"
                )
            },
            singleLine = true,
            isError = errorLlegada
        )

        // Campo para introducir la duracion del proceso
        OutlinedTextField(
            value = duracion,
            onValueChange = { duracion = it },
            label = { Text(stringResource(id = R.string.formulario_duracion)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier.width(anchuraFormularioTiempos.dp),
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

    // Si es correcto se agrega el proceso y reinicia estado
    if (procesoAgregado) {
        agregarProceso()
        procesoAgregado = false
    }

    // Si es correcto se pasa a la siguiente pagina y se reinicia el estado
    if (quantumSeleccionado) {
        comprobarQuantum()

        // TODO -> Pasar a la pagina de resultados

        quantumSeleccionado = false
    }
}