package com.example.clinicapets

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

//Esta clase obtiene el tipo de animal, nombre, edad y tamaño y calcula la cantidad e alimento diario
class Resultado : AppCompatActivity() {

    private var animalSelected:String?=null
    private lateinit var imgAnimalSelected: ImageView
    private var nombre: String? = null
    private var edad: Int? = 0
    private var tamanyo: Int = 0
    private lateinit var tipoTamanyo : TamanyoAnimal
    private var gramosComida = intArrayOf(0, 0)

    private lateinit var txtResultado: TextView
    private lateinit var txtNombre: TextView
    private lateinit var txtEdad: TextView
    private lateinit var txtTamanyo: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resutado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//Obtain animal from main_activity
        val paquete: Bundle? = intent.extras
        animalSelected = paquete?.getString("animalSelected")
        nombre = paquete?.getString("nombre")
        edad = paquete?.getInt("edad")
        tamanyo = paquete?.getInt("tamanyo")!!

        initComponents()

    }

    fun initComponents() {

        //update gramos
        gramosComida()
        //si es un gato son 10 gramos menos
        if(animalSelected=="cat"){
            gramosComida[0]-=10
            gramosComida[1]-=10
        }
        //update tamanyo


        imgAnimalSelected = findViewById(R.id.logoAnimal)
        txtNombre = findViewById(R.id.txtNombreAnimal)
        txtEdad = findViewById(R.id.txtEdad)
        txtTamanyo = findViewById(R.id.txtTamanyo)
        txtResultado = findViewById(R.id.txtResultado)
        tamanyo()
        cambiarLogo()
        txtNombre.text = nombre
        txtEdad.text = "Edad:\n ${edad} años"
        txtTamanyo.text = "Tamaño: ${tipoTamanyo}"
        txtResultado.text = "Debería de comer entre ${gramosComida[0]} y ${gramosComida[1]} gramos diarios"
    }

    fun cambiarLogo(){
        when (animalSelected) {
            "dog"-> {
                imgAnimalSelected.setColorFilter(getColor(R.color.blue))
                imgAnimalSelected.setImageResource(R.drawable.dog)


            }
            "cat"->{
                imgAnimalSelected.setColorFilter(getColor(R.color.blue))
                imgAnimalSelected.setImageResource(R.drawable.cat)

            }

        }
    }

    fun tamanyo(){
        when(tamanyo){

            0->{
               tipoTamanyo= TamanyoAnimal.MINI
            }
            1->{
                tipoTamanyo= TamanyoAnimal.PEQUEÑO
            }
            2->{
                tipoTamanyo= TamanyoAnimal.MEDIANO
            }
            3->{
                tipoTamanyo= TamanyoAnimal.GRANDE
            }


        }
    }

    fun getTamanyo():Int{
        return tamanyo
    }
    fun gramosComida() {
        if (edad!! > 1) {
            when (tamanyo) {
                0 -> {
                    gramosComida[0] = 50
                    gramosComida[1] = 90
                }

                1 -> {
                    gramosComida[0] = 90
                    gramosComida[1] = 190

                }

                2 -> {
                    gramosComida[0] = 190
                    gramosComida[1] = 310
                }

                3 -> {
                    gramosComida[0] = 500
                    gramosComida[1] = 590
                }
            }

        } else {
            when (tamanyo) {
                0 -> {
                    gramosComida[0] = 10
                    gramosComida[1] = 30
                }

                1 -> {
                    gramosComida[0] = 30
                    gramosComida[1] = 70

                }

                2 -> {
                    gramosComida[0] = 70
                    gramosComida[1] = 120
                }

                3 -> {
                    gramosComida[0] = 120
                    gramosComida[1] = 300
                }
            }
        }

    }
}