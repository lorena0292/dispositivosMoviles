package com.example.clinicapets

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapets.models.PetResponseItem
import com.example.clinicapets.adapters.PetsAdapter
import com.example.clinicapets.service.RetrofitServiceFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

   /* private val petsInit= listOf (
       // Pet("benji", SpeciePets.GATO, false),
       // Pet("benji", SpeciePets.PERRO, false)
        PetResponseItem("benji","perro"),
        PetResponseItem("kali","gat")
       )*/

    //Splash time
    private var splashScreenStays :Boolean = true
    private val DELAY:Long = 1500L

    private lateinit var btnComer: Button
    private lateinit var btnEdad: Button
    private lateinit var dog:ImageView
    private lateinit var cat:ImageView
    private lateinit var animalSelected: String
    private lateinit var txtNoSelected:TextView



    //Recycler pets
    private lateinit var rvPets: RecyclerView
    private lateinit var petsAdapter: PetsAdapter

    private val petsInit= mutableListOf<PetResponseItem>()


    override fun onCreate(savedInstanceState: Bundle?) {

        //Usamos el splash creado
        val screenSplash: SplashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Incrementamos el tiempo del Splash
        screenSplash.setKeepOnScreenCondition{splashScreenStays}
        Handler(Looper.getMainLooper()).postDelayed({ splashScreenStays = false }, DELAY)

        //Inicializamos componentes y listeners
        initComponents()
        initListeners()



        //Montamos el recycler de pets
        petsAdapter = PetsAdapter(petsInit)
          rvPets.layoutManager = LinearLayoutManager(this)
          rvPets.adapter=petsAdapter




        //Montamos el servicio para lanzar la petición contra el API
        val apiPets = RetrofitServiceFactory.getPetsRetrofit()

       lifecycleScope.launch {
         //   val data = apiPets.getPets("mascota")
            val data = apiPets.getPets("posts")
            //Relleno los datos desde la respuesta
            val petsData=data
            //Borro datos del RecyclerView
            petsInit.clear()
            petsInit.addAll(petsData)
            //Repinta RecyclerView
            petsAdapter.notifyDataSetChanged()
        }




/*

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

*/


    }

    private fun initComponents(){
        //Buscamos e inicializamos los recyclers
        rvPets = findViewById(R.id.rvPets)
        dog=findViewById(R.id.dog)
        cat=findViewById(R.id.cat)
        btnComer = findViewById(R.id.btnComer)
        btnEdad = findViewById(R.id.btnEdad)
        txtNoSelected=findViewById(R.id.txtAnimalNoSelected)

    }
    private fun initListeners(){
        //ingreso al formulario
        btnComer.setOnClickListener{

            //val imc:Double = calcularIMC()
            val intent = Intent(this,cuantoComer::class.java)

            if(animalSelected=="dog" || animalSelected=="cat"){
                intent.putExtra("animalSelected",animalSelected)
                startActivity(intent)

            }else{
                txtNoSelected.setVisibility(View.VISIBLE)
            }
        }
        //ingreso al formulario
        btnEdad.setOnClickListener{
            //val imc:Double = calcularIMC()
            val intent = Intent(this,resultado::class.java)

            if(animalSelected=="dog" || animalSelected=="cat") {
                intent.putExtra("animalSelected",animalSelected)
                startActivity(intent)

            }
            else {
                txtNoSelected.setVisibility(View.VISIBLE)
            }

        }

        dog.setOnClickListener {
        cambiaAnimalSeleccionado("dog")
            val intentComida = Intent(this,cuantoComer::class.java)
            val intentIMC = Intent(this,resultado::class.java)
            intent.putExtra("animalSelected","dog")
            txtNoSelected.setVisibility(View.GONE)

        }
        cat.setOnClickListener {
            cambiaAnimalSeleccionado("cat")
            val intentComida = Intent(this,cuantoComer::class.java)

            txtNoSelected.setVisibility(View.GONE)

        }


    }

    private fun cambiaAnimalSeleccionado(animal:String) {
        animalSelected=animal
        when (animal) {
            "dog" -> {
                animalSelected="dog"
                dog.setBackgroundResource(R.drawable.border)
                cat.setBackgroundColor(ContextCompat.getColor(this, R.color.animalNoSelected))
            }
            "cat" -> {
                animalSelected="cat"
                dog.setBackgroundColor(ContextCompat.getColor(this, R.color.animalNoSelected))
                cat.setBackgroundResource(R.drawable.border)
            }
            else -> {
                dog.setBackgroundColor(ContextCompat.getColor(this, R.color.animalNoSelected))
                cat.setBackgroundColor(ContextCompat.getColor(this, R.color.animalNoSelected))
            }
        }
    }

}