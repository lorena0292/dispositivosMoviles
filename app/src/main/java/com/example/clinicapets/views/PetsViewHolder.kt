package com.example.clinicapets.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapets.R
import com.example.clinicapets.models.PetResponseItem

class PetsViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    private val txtNombrePet: TextView =view.findViewById(R.id.txtNombrePet)
    private val txtCuerpo: TextView =view.findViewById(R.id.textViewBody)

    fun render(pet: PetResponseItem) {
        // txtNombrePet.text = pet.nombre
        txtNombrePet.text = pet.title
        txtCuerpo.text = pet.body
    }
}