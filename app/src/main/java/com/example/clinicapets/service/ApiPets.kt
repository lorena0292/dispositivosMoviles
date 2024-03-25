package com.example.clinicapets.service

import com.example.clinicapets.models.PetResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiPets {
    @GET
    suspend fun getPets(@Url url: String): PetResponse

    //Una petición con parámetros en la query sería
    /*
        @GET("www.api.es/api/v1")
        suspend fun getCanalesYoutubeQuery(
            @Path("limit") limit:Int,
            @Query("api_key") apikey:String,
            @Query("region") region:String
        )
    */
}

    object RetrofitServiceFactory {
        //Funcion que crea un objeto Retrofit(aliaspets) a partir de una URl + una factoría de conversión + la Interfaz de la petición
        fun getPetsRetrofit(): ApiPets {
            return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
              //  .baseUrl("http://192.168.0.108:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiPets::class.java)
        }


}