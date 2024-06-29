package com.example.powerfit.pages.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.powerfit.MainActivity
import com.example.powerfit.R
import com.example.powerfit.pages.components.navModals.ConfimPagoCuotaMensual
import com.example.powerfit.pages.components.navModals.PagoCuotaCard

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var spiner : Spinner? = null
private var btnCash : Button? = null
private var btnCard : Button? = null
/**
 * A simple [Fragment] subclass.
 * Use the [PagoActividad.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagoActividad : Fragment() {
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

        val actividades = arrayOf("Musculación", "Crossfit", "Funcional", "Zumba")
        val view = inflater.inflate(R.layout.fragment_pago_actividad, container, false)
        spiner = view.findViewById(R.id.spiner_option_actividad)
        btnCard = view.findViewById(R.id.btn_card_actividad)
        btnCash = view.findViewById(R.id.btn_cash_actividad)
        val mainPage = activity as? MainActivity

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, actividades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spiner?.adapter = adapter

        btnCash?.setOnClickListener {
            mainPage?.replaceFragment(ConfimPagoCuotaMensual.newInstance("Pago de actividad"))
        }
        btnCard?.setOnClickListener {
            mainPage?.replaceFragment(PagoCuotaCard.newInstance("Pago de actividad"))
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        spiner = null
        btnCash = null
        btnCard = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PagoActividad.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PagoActividad().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}