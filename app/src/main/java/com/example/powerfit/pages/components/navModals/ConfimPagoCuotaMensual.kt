package com.example.powerfit.pages.components.navModals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.powerfit.MainActivity
import com.example.powerfit.R


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM_TITLE = "El Titulo"

class ConfimPagoCuotaMensual : Fragment() {

    private var paramTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramTitle = it.getString(ARG_PARAM_TITLE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_confim_pago_cuota_mensual, container, false)
       val btnConfirmPay: Button = view.findViewById(R.id.btn_confirm_pago)
        val textTitle : TextView = view.findViewById(R.id.subtitulo_mensual)
        textTitle.text= paramTitle
       val mainPage = activity as? MainActivity

        btnConfirmPay.setOnClickListener {
            mainPage?.replaceFragment(ReConfirPagoCuotaOk.newInstance(paramTitle!!))
        }

        return view
    }

    companion object {


        @JvmStatic
        fun newInstance(paramTitle : String) =
            ConfimPagoCuotaMensual().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, paramTitle)

                }
            }
    }
}