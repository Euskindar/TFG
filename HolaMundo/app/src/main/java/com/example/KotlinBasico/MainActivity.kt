package com.example.KotlinBasico

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.holamundo.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Leccion 1 Kotlin
        // variablesYConstantes()

        // Leccion 2 Kotlin
        // tiposDeDatos()

        // Leccion 3 Kotlin
        // sentenciasIF()

        // Leccion 4 Kotlin
        // sentenciasWHEN()

        // Leccion 5 Kotlin
        // arraisOArreglos()

        // Leccion 6 Kotlin
        // mapasODIccionarios()

        // Leccion 7 Kotlin
        // buclesForWhile()

        // Leccion 8 Kotlin
        // nullSafety()

        // Leccion 9 Kotlin
        // funciones()

        // Leccion 10 Kotlin
        clases()
    }

    // Leccion 1 aprendiendo Kotlin
    private fun variablesYConstantes() {

        // Variables = operaciones basicas C++
        var firstVariable = "HELLO WORLD!!"
        firstVariable += " !"
        println(firstVariable)

        // Constantes
        val firstConstant = 1
        println(firstConstant)
    }

    // Leccion 2 aprendiendo Kotlin
    private fun tiposDeDatos() {

        // Strings
        val string1 = "Hola"
        val string2 = "soy"
        val string3 = string1 + " " + string2 + " tonto!"
        println(string3)

        // Enteros (byte, short, int, long)
        val firstNum = 1
        val secondNum = 3
        val thirdNum = firstNum + secondNum
        println(thirdNum)

        // Decimales (float, double)
        val myDouble = 1.5
        val myFloat = 1.5f
        println(myDouble)
        println(myFloat)

        // Booleanos
        val myBool = true
        println(myBool)
    }

    // Leccion 3 aprendiendo Kotlin
    private fun sentenciasIF() {

        // Sentencia IF
        val numero = 11

        if (numero < 10) {
            println("$numero es menor que 10")
        }
        else if (numero == 10) {
            println("$numero es igual a 10")
        }
        // Sentencia ELSE
        else {
            println("$numero es mayor que 10")
        }
    }

    // Leccion 4 aprendiendo Kotlin
    private fun sentenciasWHEN() {

        // Sentencia WHEN => Equivalente a SWITCH
        val country = "Argentina"

        // Sentencia WHEN normal
        when(country) {
            "Esp" -> {
                println("Estamos en espanita!!")
            }
            "Francia" -> {
                println("Putos gabachos")
            }
            // Misma respuesta para varias opciones
            "Colombia", "Peru", "Argentina" -> {
                println("Devuelvan el oro!!")
            }
            // Sentencia else para el resto
            else -> {
                println("Pues no se sabe donde estas")
            }
        }

        // Sentencia when con valores numericos
        val age = 122

        when(age) {
            0, 1, 2 -> {
                println("eres un bebote")
            }
            // Usar rangos para no tener que poner todos los numeros
            in 3..10 -> {
                println("Eres un nene")
            }
            in 11..99 -> {
                println("Te vas a morir ya mismo 🤣 ❤ 😍")
            }
            else -> {
                println("No existes")
            }
        }
    }

    // Leccion 5 aprendiendo Kotlin
    private fun arraisOArreglos() {

        val name = "Julen"
        val surname = "Perez"
        val address = "calle hola noseq"
        val phone = "19082731928"

        // Creacion del array
        val myArray = arrayListOf<String>()

        // Agregarle los valores
        myArray.add(name)
        myArray.add(surname)
        myArray.add(address)
        myArray.add(phone)

        println(myArray)

        myArray.addAll(listOf("agrego", "lo", "que", "quiero"))

        println(myArray)

        // Acceso/Modif de datos del array
        val newPhone = myArray[3]
        myArray[3] = "129873"

        println(newPhone)
        println(myArray)

        // Eliminacion de datos del array
        myArray.remove(myArray.last())
        println(myArray)

        // Recorrer array
        myArray.forEach {
            println(it)
        }

        // Otras operaciones
        myArray.sort()
        myArray.clear()
    }

    // Leccion 6 aprendiendo Kotlin
    private fun mapasODIccionarios() {

        // Creacion de mapa/diccionario vacio
        // Funciona flow SQL, elementos CLAVE - VALOR
        var myMap: Map<String, Int> = mapOf()
        println(myMap)

        // Agregar elementos al crear
        myMap = mapOf("nombre" to 1,"nombre2" to 2,"nombre3" to 3,"nombre4" to 4)
        println(myMap)

        // Modificar valores o agregar mas
        var newMyMap: Map<String, Int> = mutableMapOf()
        newMyMap = mutableMapOf("nombre" to 1,"nombre2" to 2,"nombre3" to 3,"nombre4" to 4)
        newMyMap["Carlos"] = 9
        println(newMyMap)

        // Otra forma
        newMyMap.put("Carlos2", 8)
        println(newMyMap)

        // Acceso a valores
        println(newMyMap["Carlos2"])

        // Eliminar valores
        newMyMap.remove("Carlos2")
        println(newMyMap)
    }

    // Leccion 7 aprendiendo Kotlin
    private fun buclesForWhile() {

        var myArray = arrayListOf<String>()
        myArray.addAll(listOf("agrego", "lo", "que", "quiero"))

        var newMyMap: Map<String, Int> = mutableMapOf()
        newMyMap = mutableMapOf("nombre" to 1,"nombre2" to 2,"nombre3" to 3,"nombre4" to 4)

        // FOR

        // Recordar que array es ordenado
        for (myString in myArray) {
            println(myString)
        }

        // Recordar que los maps no son ordenados y puede variar el orden
        for (myElement in newMyMap) {
            println("${myElement.key} - ${myElement.value}")
        }

        // Nueva opcion de rangos UNTIL y STEP
        for (x in 0 until 10 step 2) {
            println(x)
        }

        // Nueva opcion DOWN TO (cuenta a la inversa)
        for (x in 10 downTo  0 step 2) {
            println(x)
        }

        // ***TRUCO***
        // Crear array de un rango numerico
        val myNumericArray = (0..20)
        myNumericArray.forEach { println(it) }

        // WHILE
        // Igual que C++ poco mas que decir
        var x = 0

        while (x < 10) {
            println(x)
            x++
        }
    }

    // Leccion 8 aprendiendo Kotlin
    private fun nullSafety() {

        // Este apartado consiste en la gestion de valores de variables NULL
        // var variableNull = NULL // Esto da error al compilar

        // Para declarar una varibale anulable, se especifica el tipo y se pone ?
        var variableNull: String? = "Hola que ase?"
        variableNull = null
        println(variableNull)

        // Para obligar que una variable no sea NULL, se le pone "!!"
        var variableNoNull: String? = "No soy null"
        // variableNoNull = null // Da error al estar despues obligando a que no sea null
        println(variableNoNull!!)

        // Para evitar problemas se comprueba que no sea null
        if (variableNoNull != null) {
            println("Se hacen cosas")
        }
        else {
            println("No se hacen cosas")
        }

        // Safe calls - herramientas para evitar estar siempre comprobando valores null
        println(variableNoNull?.length) // Con la ? se consigue que solo se ejecute la parte ".lenght" si no es null

        // Safe call - ejecutar codigo en caso de no null o en caso de null evitando el IF/ELSE
        variableNoNull?.let {
            println(it) // Solo se ejecuta cuando es != null
        } ?: run {
            println(variableNoNull) // Solo entra cuando es == null
        }

        // **TIP EXTRA**
        // A no ser que estemos totalmente seguros de que una variable NUNCA sera null,
        // utilizar siempre variables anulables con la ?, para forzar comprobacion y evitar dolores de cabeza
    }

    // Leccion 9 aprendiendo Kotlin
    private fun funciones() {

        sayHello()

        var name = "Julen"
        var age = 23
        funcionConParametros(name, age)

        var num1 = 12
        var num2 = 8
        println("El resultado es: " + (funcionConRetorno(num1, num2)))
    }

    // Funcion SIMPLE de ejemplo
    fun sayHello() {
        println("Hola!")
    }

    // Funciones con parametros de entrada
    fun funcionConParametros(name: String, age: Int) {
        println("Hola, $name. Eres to viejo con $age!")
    }

    // Funciones con parametros de retorno
    fun funcionConRetorno(num1: Int, num2: Int) : Int {
        var sum = num1 + num2
        println("He hecho la suma de $num1 y $num2.")

        return sum
    }

    // Leccion 10 aprendiendo Kotlin
    fun clases() {

        val perro1 = Perrete("Cuqui", 12, arrayOf(Perrete.Amos.JULEN, Perrete.Amos.BEGO), "Labrador")
        val perro2 = Perrete("Cuquita", 11, arrayOf(Perrete.Amos.ANTONIO, Perrete.Amos.LERIE), "Pitbull", arrayOf(perro1))

        println(perro1.nombre)
        perro1.amos.forEach {
            println(it)
        }

        println(perro2.nombre)
        perro2.amos.forEach {
            println(it)
        }

        // Acceso al listado de elementos anulable con los ?.
        println("Mi amigo es: ${perro2.friends?.first()?.nombre}")
    }
}
