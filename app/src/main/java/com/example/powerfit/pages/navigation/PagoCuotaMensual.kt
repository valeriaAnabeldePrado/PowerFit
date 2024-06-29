package com.example.powerfit.pages.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.powerfit.MainActivity
import com.example.powerfit.R
import com.example.powerfit.pages.components.navModals.ConfimPagoCuotaMensual
import com.example.powerfit.pages.components.navModals.PagoCuotaCard


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PagoCuotaMensual.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagoCuotaMensual : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pago_cuota_mensual, container, false)
        val btnCard : Button = view.findViewById(R.id.btn_card)
        val btnCash : Button = view.findViewById(R.id.btn_cash)
        val mainPage = activity as? MainActivity

        btnCard.setOnClickListener {
            mainPage?.replaceFragment(PagoCuotaCard.newInstance("Pago mensual de cuota"))
        }

        btnCash.setOnClickListener {
            mainPage?.replaceFragment(ConfimPagoCuotaMensual.newInstance("Pago mensual de cuota"))
        }


        return view
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PagoCuotaMensual().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}