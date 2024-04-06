package com.example.clinicapets

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider
import java.util.logging.Logger
import kotlin.math.log
import kotlin.properties.Delegates


class cuantoComer : AppCompatActivity() {

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    lateinit var rbMini:RadioButton
    lateinit var rbPequenyo:RadioButton
    lateinit var rbMediano:RadioButton
    lateinit var rbGrande:RadioButton
    lateinit var txtNombre:TextView
    lateinit var textErrorNombre:TextView
    lateinit var textErrorTamanyo:TextView
    lateinit var textEdad:TextView
    lateinit var rsEdad:RangeSlider
    private var animalSelected:String?=null


    private var nombre:String?=null
    private var edad:Int=0
    private var tamanyo:Int= 0


    private lateinit var button: Button
    private lateinit var txtAnimalSelected:TextView
    private lateinit var imgAnimalSelected:ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuanto_comer)
        initComponents()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            }
        //Obtain animal from main_activity
        val paquete: Bundle? = intent.extras
        animalSelected = paquete?.getString("animalSelected")


        when (animalSelected) {
        "dog"-> {
            this.txtAnimalSelected.text= getString(R.string.strComerPerro)
           imgAnimalSelected.setImageResource(R.drawable.dog)
            imgAnimalSelected.setColorFilter(getColor(R.color.blue))
            rbMini.text=getString(R.string.strPerroMini)
            rbPequenyo.text=getString(R.string.strPerroPequenyo)
            rbMediano.text=getString(R.string.strPerroMediano)
            rbGrande.text=getString(R.string.strPerroGrande)
        }
        "cat"->{
            txtAnimalSelected.text= getString(R.string.strComerGato)
            imgAnimalSelected.setImageResource(R.drawable.cat)
            rbMini.text = getString(R.string.strGatoMini)
            rbPequenyo.text = getString(R.string.strGatoPequenyo)
            rbMediano.text = getString(R.string.strGatoMediano)
            rbGrande.text = getString(R.string.strGatoGrande)
        }

    }
        initListeners()

    }
    private fun initComponents(){
        //Assigning textView of animal
        txtAnimalSelected = findViewById(R.id.txtAnimalSelected)
        //Assigning imgView of animal
        imgAnimalSelected = findViewById(R.id.imgAnimalSelected)
        // Assigning id of RadioGroup
        radioGroup = findViewById(R.id.radioGroup)
        // Getting the checked radio button id
        // from the radio group
        val selectedOption: Int = radioGroup!!.checkedRadioButtonId


        // Assigning id of Submit button
        button = findViewById(R.id.btnCalcular)
        rbMini= findViewById(R.id.rbMini)
        rbPequenyo= findViewById(R.id.rbPequenyo)
        rbMediano= findViewById(R.id.rbMediano)
        rbGrande= findViewById(R.id.rbGrande)
        rsEdad=findViewById(R.id.rngEdad)
        textEdad=findViewById(R.id.textEdad)
        textEdad.text="Edad: ${edad.toString()} años"
        txtNombre=findViewById(R.id.txtNombrePet)
        textErrorNombre=findViewById(R.id.textErrorNombre)
        textErrorTamanyo=findViewById(R.id.textErrorTamanyo)
    }


    private fun initListeners(){
        // Button Calcular Listener
        button.setOnClickListener {

            val intent = Intent(this,resultado::class.java)

            // Displaying text of the checked radio button in the form of toast
         //   Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()

            nombre=txtNombre.text.toString()

            // Displaying text of the checked radio button in the form of toast
          //  Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()

            if(comprobarCampos()){
                intent.putExtra("animalSelected",animalSelected)
                intent.putExtra("edad",edad)
                intent.putExtra("tamanyo",tamanyo)
                intent.putExtra("nombre",nombre)

                startActivity(intent)
            }

        }

        //RangeSlider Edad Listener
        rsEdad.addOnChangeListener{slider, value, fromUser ->
            edad=value.toInt()
            if(edad==0){
                textEdad.text = "Edad: Menos de 1 año"
            }
            else {
                textEdad.text = "Edad: ${edad.toString()} años"
            }
        }


        radioGroup?.setOnCheckedChangeListener { group, checkedId ->

            textErrorTamanyo.setVisibility(View.GONE)
            when(checkedId){
                R.id.rbMini-> {
                    tamanyo = 0
                }
                R.id.rbPequenyo-> {
                    tamanyo = 1
                }
                R.id.rbMediano-> {
                    tamanyo = 2
                }
                R.id.rbGrande-> {
                    tamanyo = 3
                }
            }



        }



    }
    private fun comprobarCampos():Boolean{

        if (txtNombre.text.toString().isEmpty()){
            textErrorNombre.setVisibility(View.VISIBLE)
            return false
        }
        else{
            textErrorNombre.setVisibility(View.GONE)
        }

        if (tamanyo==0){
            textErrorTamanyo.setVisibility(View.VISIBLE)
            return false
        }
        else{
            textErrorTamanyo.setVisibility(View.GONE)
        }
        return true
    }
}