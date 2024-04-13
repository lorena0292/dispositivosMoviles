package com.example.clinicapets

import org.junit.Assert.*
import org.junit.Test


class CuantoComerTest{
     var animal: String?=null
    @Test
   fun animalSeleccionadoOk(){
       animal= CuantoComer().getAnimalSeleccionado()
        animal?.let { assertTrue(it.isNotEmpty()) }


   }

}