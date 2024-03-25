package com.example.clinicapets

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapets.models.PetResponseItem
import com.example.clinicapets.adapters.PetsAdapter
import com.example.clinicapets.models.Pet
import com.example.clinicapets.models.SpeciePets
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
    private var splashScreenStays :Boolean = true;
    private val DELAY:Long = 1500L;

    private lateinit var btnComer: Button
    private lateinit var btnEdad: Button


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


        //Buscamos e inicializamos los recyclers
     rvPets = findViewById(R.id.rvPets)

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


        btnComer = findViewById(R.id.btnComer)

        //ingreso al formulario
        btnComer.setOnClickListener{
            //val imc:Double = calcularIMC()
            val intent = Intent(this,cuantoComer::class.java)
            // intent.putExtra("IMC",imc)
            startActivity(intent)
        }



        btnEdad = findViewById(R.id.btnEdad)

        //ingreso al formulario
        btnEdad.setOnClickListener{
            //val imc:Double = calcularIMC()
            val intent = Intent(this,calculaEdad::class.java)
            // intent.putExtra("IMC",imc)
            startActivity(intent)
        }





    }
}