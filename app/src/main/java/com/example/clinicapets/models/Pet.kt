package com.example.clinicapets.models


enum class SpeciePets {
    PERRO, GATO
}

data class Pet(var nombre:String, var specie:SpeciePets, var isSelected:Boolean=false)
