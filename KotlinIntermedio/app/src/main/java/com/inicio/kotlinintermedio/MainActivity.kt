package com.inicio.kotlinintermedio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.thread

// Leccion 7 - typeAliases
// Uso en tipos normales
typealias MyMapList = MutableMap<Int, ArrayList<String>>
// Uso en funciones
typealias MyFun = (Int, String, MyMapList) -> Boolean
// Uso en clases anidadas
typealias MyNestedClass = MyNestedInnerClass.MyNestedClass

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Leccion 1 - Enum classes
        // enumClasses()

        // Leccion 2 - Clases anidadas / Clases internas
        // clasesAnidadas()

        // Leccion 3 - Herencia de clases
        // herenciaDeClases()

        // Leccion 4 - Interfaces
        // interfaces()

        // Leccion 5 - Modificadores de Visibilidad
        // modificadoresDeVisibilidad()

        // Leccion 6 - Data classes o Clases de datos
        // dataClasses()

        // Leccion 7 - Type Aliases
        // typeAliases()

        // Leccion 8 - Destructuring Declarations o Desestructuracion
        // destructuringDeclarations()

        // Leccion 9 - Extensions o Extensiones para ampliar las clases
        // extensions()

        // Leccion 10 - Lambdas
        // lambdas()
    }

    // ---------------------------------------------------
    // Leccion 1 Kotlin Intermedio

    // Creacion de clase numerada
    /*Para indicar paramentros*/
    enum class Direcciones(val dirNum: Int) {
        NORTE(1), SUR(-1), ESTE(2), OESTE(-2);    // ";" para separar y agregar mas funcionalidad

        fun description(): String {
            return when (this) {
                NORTE -> "Vas parriba"
                ESTE -> "Vas pa la derecha"
                SUR -> "Vas pabajo"
                OESTE -> "Vas pa la izquierda"
                // En caso de que falte alguna de las opciones disponibles
                else -> "Vas mas perdido que el barco del arroz"
            }
        }
    }

    private fun enumClasses() {
        var userDirection: Direcciones? = null
        println("Direccion : $userDirection")

        userDirection = Direcciones.ESTE
        println("Direccion : $userDirection")

        // Propiedades por defecto de los enum
        println("Name: ${userDirection.name}")
        println("Ordinal: ${userDirection.ordinal}")

        // Uso de funciones dentro del enum
        println(userDirection.description())

        // Inicializacion con parametros
        println(userDirection.dirNum)
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 2 Kotlin Intermedio
    private fun clasesAnidadas() {

        // Acceso a clase anidada
        val myNestedClass = MyNestedInnerClass.MyNestedClass()
        val suma = myNestedClass.sum(2, 3)
        println("El resultado es: $suma")

        // Acceso a clase interna
        val myInnerClass = MyNestedInnerClass().MyInnerClass()
        val sumarUno = myInnerClass.sumOne(10)
        println("El siguiente numero es $sumarUno.")
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 3 Kotlin Intermedio
    private fun herenciaDeClases() {

        val programador = Programmer("Julen", 23, arrayOf("Kotlin", "C++"))
        programador.work()
        programador.printLenguajes()
        programador.goToWork()
        programador.conducir()

        val disenador = Designer("Julen2", 24)
        disenador.work()
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 4 Kotlin Intermedio
    private fun interfaces() {

        val gamer = MyInheritanceClass.Persona("Jolon", 22)
        gamer.stream()
        gamer.play()
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 5 Kotlin Intermedio

    // Si no se especifica la visibilidad, esta siempre sera PUBLIC
    private fun modificadoresDeVisibilidad() {

        // val visibility = Visibility()
        // visibility.name = "Paco"
        // visibility.sayMyName()

        //val visibilityPrivate = VisibilityPrivate()
        //visibilityPrivate.sayMyNamePrivate() // Solo permite acceso a subclases
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 6 Kotlin Intermedio
    private fun dataClasses() {

        val julen = Worker("Julen", 23, "Programador")

        val manu = Worker()

        // Operaciones de tratamiento de las data classes

        // EQUALS() y HASHCODE()  ==> Compara los dos elementos data classes
        if (julen.equals(manu)) {
            println("Son super bros")
        } else {
            println("Agua y aceite pegan mas")
        }

        // TOSTRING() ==>  Representacion en string de la info del data class
        println(julen.toString())

        // COPY() ==> Copia y posibilita la modificacion de alguna caracteristica
        val julen2 = julen.copy(age = 24)
        println(julen2.toString())

        // componentN ==> Modularizacion de una clase en componentes mas peques

        // Nos ahorramos tener que llamara a julen.name y julen.age por separado
        val (name, age, work) = julen
        println(name)
        println(age)
        println(work)
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 7 Kotlin Intermedio

    // Suponemos esta variable del tipo siguiente, bastante complejo
    private var myMap: MutableMap<Int, ArrayList<String>> = mutableMapOf()

    private fun typeAliases() {

        // Si quieremos hacer una variable del mismo tipo anterior, hay que copiar igual
        val myNewMap: MutableMap<Int, ArrayList<String>> = mutableMapOf()
        myNewMap[1] = arrayListOf("julen", "manu")
        myNewMap[2] = arrayListOf("mas cosas", "una mas")

        myMap = myNewMap

        // Para hacer ese trabajo con variables con tipos tan complejos existen los type alias
        // Toca ir al principio del fichero para declararlo

        // Ahora se puede hacer lo mismo que antes pero con el alias
        val myNewMap2: MyMapList = mutableMapOf()

        // Los type alias sirven tambien para delcarar tipos de una funcion o clases anidadas
        // Ejemplos en la parte superior del fichero
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 8 Kotlin Intermedio
    private fun destructuringDeclarations() {

        // Reutilizamos la clase anterior para ampliar el concepto utilizado en el componentN
        val (name, age, work) = Worker(name = "Julen", age = 24, work = "Programador")
        println("$name, $age, $work")

        // Acceso mediante las componentes
        val julen = Worker()
        println(julen.component1())

        // Desestructuracion en funciones
        // La respuesta de una funcion tambien se puede desestructurar igual que lo anterior
        val (name1, age1) = MyFunNew()
        println("$name1, $age1")

        // Desestructuracion en mapas
        val myMap = mapOf(1 to "Julen", 2 to "Bego", 3 to "Leire")
        for ((id, name) in myMap) {
            // println("${it.key}, ${it.value}") // Esto es correcto pero se puede optimizar
            // usando la desestructuracion queda asi
            println("$id, $name")
        }

        // En caso de que se quiera acceder a una propiedad pero no a la anterior, se utiliza:
        // UNDERSCORE o subrayado ==> Se pone una barrabaja en la posicion de la propiedad que queramos ignorar
        val (name2, _, work2) = Worker("nuevo2", 45, "no hace nada")
        println("$name2, $work2")
    }

    private fun MyFunNew(): Worker {
        val nuevo = Worker("Nuevo", 100, "Abuelo")
        return nuevo
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 9 Kotlin Intermedio
    private fun extensions() {

        // Esto sirve para utilizar las librerias y ampliarlas con las funconalidades que deseemos

        // Se ha creado el fichero Extensions donde hemos creado un nuevo formato de Date
        val myDate = Date()
        println(myDate.nuevoFormato())

        // Extensiones con valores nulos
        val myDateNullable: Date? = null
        println(myDateNullable.nuevoFormatoNull())

        // Extension de propiedades (tambien permiten ser nullable)
        println(myDate.formatSize)
        println(myDateNullable.formatSize)
    }
    // ---------------------------------------------------

    // ---------------------------------------------------
    // Leccion 10 Kotlin Intermedio
    private fun lambdas() {

        // Usar funciones dentro de funciones de forma pro

        // Esto es una lambda, se ha utilizado una funcion y hemos obtenido un resultado sin declararlo explicitamente
        val myIntList = arrayListOf<Int>(0, 1, 2, 3, 4, 5)
        val myListResult = myIntList.filter { myInt ->

            // Se puede ejecutar el codigo que se desee
            println(myInt)

            // Para devolver algo entre medias se usa el return@filter
            if (myInt == 2) {
                return@filter true
            }

            // Siempre se devuelve en la ultima linea
            myInt > 3
        }
        println(myListResult)

        // Podemos crear nuestras propias lambdas

        // Primero creamos nuestras funciones (simples)
        val mySumFun = fun(int1: Int, int2: Int): Int = int1 + int2
        val myMultFun = fun(int1: Int, int2: Int): Int = int1 * int2

        // Llamada a la funcion lambda que hemos creado
        println(myOperateFun(5, 10, mySumFun))
        println(myOperateFun(5, 10, myMultFun))

        // Podemos complicarlo lo que deseemos o definir nuevas funciones sin tenerlas que definir antes
        // pero siempre siguiendo la logica de la lambda creada (myOperateFun() en este caso)
        println(myOperateFun(5, 10) { x, y -> x - y }) // recibe dos enteros y los resta

        // Callbacks
        myAsincFun("Julen") { println(it) }
    }

    // Para ser una lambda debe recibir una funcion
    private fun myOperateFun(int1: Int, int2: Int, myFunLambda: (Int, Int) -> Int): Int {
        return myFunLambda(int1, int2)
    }

    // Uso de lambdas para funciones asincronas o callbacks
    private fun myAsincFun(
        name: String,
        hello: (String) -> Unit
    ) { // El Unit, es equivalente a un valor de tipo VOID

        val myNewString = "ola $name"

        // Para simular una asincronia vamos a hacer que se espere 5s
        thread {
            Thread.sleep(5000)
            hello(myNewString)
        }
    }
    // ---------------------------------------------------
}