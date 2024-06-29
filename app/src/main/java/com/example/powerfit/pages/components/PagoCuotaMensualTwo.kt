package com.example.powerfit.pages.components

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

//El subTitulo que se pasa como parametro
private const val ARG_PARAM_TITLE = "param_title"
private var spiner: Spinner? = null
private var btnPagar: Button? = null

class PagoCuotaMensualTwo : Fragment() {
    private var paramTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //El parametro a utilizar
            paramTitle = it.getString(ARG_PARAM_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_pago_cuota_mensual_two, container, false)
         spiner  = view.findViewById(R.id.spiner_option)
         btnPagar  = view.findViewById(R.id.btn_pagar_cuota)
        val mainPage = activity as? MainActivity
        val cuotas = arrayOf("Seleccionar las cuotas","1 Cuota", "3 Cuotas", "6 Cuotas")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cuotas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spiner?.adapter = adapter

        btnPagar?.setOnClickListener {
            mainPage?.replaceFragment(ConfimPagoCuotaMensual.newInstance(paramTitle!!))
        }

        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Limpia las referencias a los objetos UI para prevenir fugas de memoria
        spiner = null
        btnPagar = null
    }
    companion object {

        @JvmStatic
        fun newInstance(paramTitle: String) =
            PagoCuotaMensualTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, paramTitle)
                }
            }
    }
}