package com.example.powerfit.pages.navigation

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.powerfit.R
import com.example.powerfit.pages.LogInPage


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    //ATENCION: ACA ES DONDE TENGO QUE HACER LA PROGRAMACION DE LA INTERACCION, PORQUE ESTE ES EL METODO EN DONDE SE INFLA LA VISTA
    //PERO ANTES DEBO ASEGURARME QUE LA VISTA SEA INFLADA SI NO, NO TIENE SENTIDO PROGRAMAR EVENTOS EN UNA VISTA Q NO EXISTE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Crea la vista inflada para la navegacion lista
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view?.let {
            val btnLogOut: Button = it.findViewById(R.id.btn_log_out)
            btnLogOut.setOnClickListener {
                showDialog()
            }

        }

        return view
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_box)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.setBackgroundDrawable(requireContext().getDrawable(R.drawable.modal_violet))

        val btnVolver: Button = dialog.findViewById(R.id.btn_volver)
        val btnLogOut: Button = dialog.findViewById(R.id.btn_logOut)
        dialog.show()

        btnVolver.setOnClickListener {

            dialog.dismiss()
        }
        btnLogOut.setOnClickListener {
            logOut()
            dialog.dismiss()
        }
    }

    private fun logOut() {
        val intent = Intent(requireContext(), LogInPage::class.java)
        startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}