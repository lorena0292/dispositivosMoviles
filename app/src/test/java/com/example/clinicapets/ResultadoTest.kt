package com.example.clinicapets

import org.junit.Test


import org.junit.Assert.*


class ResultadoTest{

     var tamanyo:Int=0

    var res: Resultado = Resultado()

    @Test
    fun getTamanyoTest(){
       tamanyo= res.getTamanyo()
        assertEquals(0,0)

    }
}