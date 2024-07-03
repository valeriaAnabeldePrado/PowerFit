package com.example.powerfit.pages.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powerfit.R

//En esta clase voy a realizar el adaptador de las cards que extiende de RecyclerView.Adapter para convertir una lista en un recicler
class MorososAdapter(private val morososList: MutableList<MorosoClass>) :
    RecyclerView.Adapter<MorososViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MorososViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MorososViewHolder(layoutInflater.inflate(R.layout.item_moroso, parent, false))
    }

    override fun getItemCount(): Int = morososList.size

    override fun onBindViewHolder(holder: MorososViewHolder, position: Int) {
        val item = morososList[position]
        holder.bind(item) { positionToRemove ->
            morososList.removeAt(positionToRemove)
            notifyItemRemoved(positionToRemove)
        }
    }
}