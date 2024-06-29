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


private const val ARG_PARAM_TITLE = "param"



class ReConfirPagoCuotaOk : Fragment() {

    private var paramTitle :String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramTitle = it.getString(ARG_PARAM_TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_re_confir_pago_cuota_ok, container, false)

        val btnVerFactura: Button = view.findViewById(R.id.boton_facturac)
        val mainPage = activity as? MainActivity
        val textTitle : TextView = view.findViewById(R.id.text_cota_OK)
        textTitle.text = paramTitle
        btnVerFactura.setOnClickListener {
            mainPage?.replaceFragment(Factura())
        }


        return view
    }

    companion object {

        @JvmStatic fun newInstance(paramTitle: String) =
                ReConfirPagoCuotaOk().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM_TITLE, paramTitle)

                    }
                }
    }
}