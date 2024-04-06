package com.example.clinicapets

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
import kotlin.properties.Delegates


class cuantoComer : AppCompatActivity() {

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    lateinit var rbMini:RadioButton
    lateinit var rbPequenyo:RadioButton
    lateinit var rbMediano:RadioButton
    lateinit var rbGrande:RadioButton
    lateinit var txtNombre:TextView
    lateinit var txtEdad:TextView
    lateinit var rsEdad:RangeSlider
    var animalSelected:String?=null


    lateinit var nombre:String
    var edad by Delegates.notNull<Int>()
    var tamanyo by Delegates.notNull<Int>()


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

        initComponents()


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
            this.txtAnimalSelected.text= getString(R.string.strComerGato)
            imgAnimalSelected.setImageResource(R.drawable.cat)
            rbMini.text = getString(R.string.strGatoMini)
            rbPequenyo.text = getString(R.string.strGatoPequenyo)
            rbMediano.text = getString(R.string.strGatoMediano)
            rbGrande.text = getString(R.string.strGatoGrande)
        }
    }







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
        txtEdad=findViewById(R.id.textEdad)
    }

    private fun comprobarCampos():Boolean{
        return true
    }
    private fun initListeners(){
        // when Submit button is clicked
        button.setOnClickListener {

            val intent = Intent(this,resultado::class.java)



            // Displaying text of the checked radio button in the form of toast
            Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()

            txtNombre=findViewById(R.id.txtNombrePet)
            rsEdad=findViewById(R.id.txtNombrePet)


            if(comprobarCampos()){
                intent.putExtra("animalSelected",animalSelected)
                intent.putExtra("edad",edad)
                intent.putExtra("tamanyo",tamanyo)
                intent.putExtra("nombre",nombre)

                startActivity(intent)
            }
        }


        rsEdad.addOnChangeListener { rangeSlider, value, fromUser ->
            edad=value.toInt()
            txtEdad.text="Edad: {$edad.toString()} años"
        }
    }
}