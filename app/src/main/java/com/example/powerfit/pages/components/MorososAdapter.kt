package com.example.powerfit.pages.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.powerfit.R

//En esta clase voy a realizar el adaptador de las cards que extiende de RecyclerView.Adapter para convertir una lista en un recicler
class MorososAdapter(private val morososList: List<MorosoClass>) :
    RecyclerView.Adapter<MorososViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MorososViewHolder {
//esta funcion devuelve el item al viewHolder por cada item de la lista
        val layoutInflater = LayoutInflater.from(parent.context)
        return MorososViewHolder(layoutInflater.inflate(R.layout.item_moroso, parent, false))
    }

    //esta funcion me obtiene la cantidad de datos que hay en la lista
    override fun getItemCount(): Int = morososList.size

    //Este metodo pasa por cada uno de los items y llama al render para asignar los valores de la lista a los valores del layout
    override fun onBindViewHolder(holder: MorososViewHolder, position: Int) {
        val item = morososList[position]
        holder.render(item)
    }
}