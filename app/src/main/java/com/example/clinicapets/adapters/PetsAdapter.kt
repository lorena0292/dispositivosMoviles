package com.example.clinicapets.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clinicapets.models.PetResponseItem
import com.example.clinicapets.views.PetsViewHolder
import com.example.clinicapets.R

class PetsAdapter(private val pets: List<PetResponseItem>): RecyclerView.Adapter<PetsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pet,parent,false)
        return PetsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return pets.size
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        holder.render(pets[position])
    }
}