package com.jolon.jetpackcomposeapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jolon.jetpackcomposeapp.navigation.AppNavigation
import com.jolon.jetpackcomposeapp.ui.theme.JetpackComposeAppTheme


// Creo un listado de mesnajes predefinido para porbar
private val listaDeMensajes: List<MyMessage> = listOf(
    MyMessage(
        "Maburro! 0",
        "Qualisque indoctum velit epicurei tamquam placerat tibique."
    ),
    MyMessage(
        "Maburro! 1",
        "Civibus sollicitudin tota dui ex dictumst persequeris quaeque viderer. Mi liber malorum quas no efficitur disputationi dico detracto aliquet."
    ),
    MyMessage(
        "Maburro! 2",
        "Inani graeco commune aliquet his dis deserunt harum possim. Dissentiunt nonumes luptatum agam graece. Etiam morbi accommodare praesent faucibus mentitum ridens porttitor ceteros. Conceptam sapientem brute ludus nulla mus urbanitas nisi tristique vivamus. Malesuada natoque quo quam indoctum."
    ),
    MyMessage(
        "Maburro! 3",
        "Ne iusto detracto convallis habeo definitiones doctus oporteat amet integer. Consetetur harum theophrastus elitr rutrum eirmod honestatis. Parturient atqui principes netus hendrerit elementum tibique primis."
    ),
    MyMessage(
        "Maburro! 4",
        "Commune persecuti homero auctor sale ancillae elitr. Elitr omnesque iriure dicant ullamcorper similique antiopam. Sed oporteat cu propriae posse vocent vim sit bibendum est. Ullamcorper iisque vulputate vituperatoribus singulis ancillae errem. Vulputate ei quot movet detracto inciderint fabellas melius. Solet quaestio splendide tamquam feugait omittam veri ancillae."
    ),
    MyMessage(
        "Maburro! 5",
        "Consectetur quem magnis deterruisset lectus nonumes brute faucibus vestibulum morbi."
    ),
    MyMessage(
        "Maburro! 6",
        "Fastidii discere nunc nostrum sollicitudin hac accumsan simul deseruisse quisque. Idque egestas ligula quot fermentum vidisse ornare. Nibh dicant faucibus salutatus homero."
    ),
    MyMessage(
        "Maburro! 7",
        "Adipiscing lacus fermentum nostrum errem constituam sagittis fuisset mus. Platonem iudicabit justo iudicabit lorem numquam faucibus himenaeos. Aenean montes ornatus nihil qualisque feugait. Omnesque impetus petentium ut deserunt ornare ad postulant curabitur. Ignota nonumy mel cetero deterruisset antiopam constituto. Consetetur pericula labores evertitur voluptatum populo has eloquentiam rutrum gubergren."
    ),
    MyMessage(
        "Maburro! 8",
        "Pharetra putent urbanitas explicari scripta alia. Nonumy petentium alia voluptaria mauris. Menandri epicurei explicari pertinax nisi auctor. Graeco mucius quaeque mnesarchum atqui curabitur putent interdum altera."
    ),
    MyMessage(
        "Maburro! 9",
        "Risus primis suscipit commodo vix patrioque oporteat. Liber vulputate ex movet dolore nisi malesuada movet viverra repudiare. Unum causae honestatis intellegat conclusionemque porta ponderum reprimique mazim aeque."
    ),
    MyMessage(
        "Maburro! 10",
        "Elitr gravida quod legimus dissentiunt luctus nominavi dissentiunt reprehendunt ornare. Habeo vero corrumpit elit viverra detraxit. Pulvinar interdum nulla reprimique pharetra appetere his. Sit a gravida aenean dignissim ceteros. His fabellas ocurreret contentiones petentium interpretaris justo eum an. Conceptam dolores porta utamur an pharetra noluisse epicurei. Alterum veritus purus praesent meliore instructior postea."
    ),
    MyMessage(
        "Maburro! 11",
        "Penatibus cum dui porro cu mei intellegat mea. Posse duo eum dico curae malesuada minim iisque hinc."
    )
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lo de aqui se pondra en la pantalla
        setContent {
            JetpackComposeAppTheme {
                // App para porbar todos los elementos basicos
//                ListadoDeMensajes(listaDeMensajes)

                // App para probar el pasar de una pagina a otra
                AppNavigation()
            }
        }
    }
}

// Crear data class para modularizar mas los textos que siempre estan teneiendo el mismo formato
data class MyMessage(val titulo: String, val cuerpo: String)

@Composable
fun ListadoDeMensajes(messages: List<MyMessage>) {
    // En vez de utilizar el elemento Column() que en una lista no tiene mucho sentido ya que puede haber elementos que no se puedan visualizar en pantalla
    // , se usa LazyColumn() que solo renderiza los elementos que se ven, ahorrando asi recuersos del dispositivo
    LazyColumn {
        items(messages) { mensaje ->
            Greeting(mensaje)
        }
    }
}

@Composable // Para crear funciones que funcionen con Jetpack
fun Greeting(txt: MyMessage) {
    // Esto es para agregar estilos y demas ya predefinidos
    MaterialTheme {
        // Para poder distriobuir los elementos y que no esten uno encima del otro
        Row(
            // Modificadores varios
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Agregar una imagen del Resource Manager de la barra lateral izquierda
            Image(
                painter = painterResource(id = R.drawable.uco_vertical),
                contentDescription = "Logo UCO vertical",
                modifier = Modifier
                    .height(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.onBackground)
            )

            // Espaciar elementos
            Spacer(modifier = Modifier.height(20.dp))

            // Nuevo elemento para organizar componentes EN FILAS
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Agregar diferentes textos
                MyTexts(MyMessage(txt.titulo, txt.cuerpo))
            }
        }
    }
}

@Composable
fun MyTexts(message: MyMessage) {
    // Para controlar si el elemento esta expandido o no
    // En JetpackCompose para guardar los estados en tiempo de ejecucion hay que utilizar el modificador Remember{}
    // Para ademas indicar que debe recargar la interfaz con los cambios de la varibale, se debe colocar el MutableStateOf()
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(start = 8.dp)
        .clickable {
            // Se ejecuta este bloque de codigo al hacer clic en el elemento

            // Invertimos el valor de expanded cuando hacemos click
            expanded = !expanded
        }) {
        MyText(message)
        MyTextBody(
            message,
            // Modificamos la propiedad lines para que solo se muestre la primera linea
            if (expanded) Int.MAX_VALUE else 1
        )
    }
}


@Composable
fun MyText(message: MyMessage) {
    Text(
        message.titulo,
        color = MaterialTheme.colors.primaryVariant,
        style = MaterialTheme.typography.h4
    )
}

@Composable
// Se le asigna un parametro nuevo lines que recibe un entero y siempre sera su valor maximo
// esto lo hacemos para poder mostrar siempre el numero maximo de lineas del texto
fun MyTextBody(message: MyMessage, lines: Int = Int.MAX_VALUE) {
    Text(
        message.cuerpo,
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.body2,
        maxLines = lines
    )
}

// Modo claro
@Preview(name = "MODO CLARO", showSystemUi = true)

// Modo oscuro
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "MODO OSCURO")
@Composable
fun DefaultPreview() {
    JetpackComposeAppTheme {
        // Para crear una scrollbar PERO POCO OPTIMO, PARA ESO SE CREA EL LAZYCOLUMN()
//        val scrollState = rememberScrollState(initial = 1)

//        Column(modifier = Modifier.verticalScroll(scrollState)) {
//        }
        // Listado de elementos colapsable
//        ListadoDeMensajes(messages = listaDeMensajes)

        // Prueba de navegacion
        AppNavigation()
    }
}