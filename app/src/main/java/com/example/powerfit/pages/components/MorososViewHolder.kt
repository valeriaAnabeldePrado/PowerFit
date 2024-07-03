package com.example.powerfit.pages.components

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.powerfit.R
fun TextView.getIntText(): Int? {
    return this.text.toString().toIntOrNull()
}

class MorososViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val morosoName: TextView = view.findViewById(R.id.item_text_name_moroso)
    val morosoTelefono: TextView = view.findViewById(R.id.item_telf_name_moroso)
    val morosoMail: TextView = view.findViewById(R.id.item_text_mail_moroso)
    val morosoFecha: TextView = view.findViewById(R.id.item_text_fecha_moroso)
    private val notifyButton: Button = view.findViewById(R.id.appCompatButton)

    fun bind(morosoClass: MorosoClass, onClick: (Int) -> Unit) {
        morosoName.text = morosoClass.nameMoroso
        morosoMail.text = morosoClass.mailMoroso
        morosoFecha.text = morosoClass.dateLimitMoroso
        var morosoTelefonoNum: Int? = morosoTelefono.getIntText()
        morosoTelefonoNum = morosoClass.phoneMorso

        notifyButton.setOnClickListener {
            Toast.makeText(it.context, "Mensaje enviado!", Toast.LENGTH_SHORT).show()
            onClick(adapterPosition)
        }
    }
}