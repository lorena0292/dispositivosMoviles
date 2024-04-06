package com.example.clinicapets

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.clinicapets.models.tamanyoAnimal

class resultado : AppCompatActivity() {

    private var animalSelected:String?=null
    private lateinit var imgAnimalSelected: ImageView
    private var nombre: String? = null
    private var edad: Int? = 0
    private var tamanyo: Int? = 0
    private lateinit var tipoTamanyo : tamanyoAnimal
    private var kilosComida = intArrayOf(0, 0)

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
        tamanyo = paquete?.getInt("tamanyo")

        initComponents()

    }

    fun initComponents() {

        //update kilos
        kilosComida()
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
        txtResultado.text = "Debería de comer entre ${kilosComida[0]} y ${kilosComida[1]} kilos diarios"
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
               tipoTamanyo=tamanyoAnimal.MINI
            }
            1->{
                tipoTamanyo=tamanyoAnimal.PEQUEÑO
            }
            2->{
                tipoTamanyo=tamanyoAnimal.MEDIANO
            }
            3->{
                tipoTamanyo=tamanyoAnimal.GRANDE
            }


        }
    }
    fun kilosComida() {
        if (edad!! > 1) {
            when (tamanyo) {
                0 -> {
                    kilosComida[0] = 50
                    kilosComida[1] = 90
                }

                1 -> {
                    kilosComida[0] = 90
                    kilosComida[1] = 190

                }

                2 -> {
                    kilosComida[0] = 190
                    kilosComida[1] = 310
                }

                3 -> {
                    kilosComida[0] = 500
                    kilosComida[1] = 590
                }
            }

        } else {
            when (tamanyo) {
                0 -> {
                    kilosComida[0] = 10
                    kilosComida[1] = 30
                }

                1 -> {
                    kilosComida[0] = 30
                    kilosComida[1] = 70

                }

                2 -> {
                    kilosComida[0] = 70
                    kilosComida[1] = 120
                }

                3 -> {
                    kilosComida[0] = 120
                    kilosComida[1] = 300
                }
            }
        }

    }
}