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
import com.example.powerfit.pages.navigation.PagoCuotaMensual

//El subTitulo que se pasa como parametro
private const val ARG_PARAM_TITLE = "param_title"
private const val ARG_PARAM_MONTO = "param_monto"
private const val ARG_PARAM_METODO = "param_metodo de pago"
private const val ARG_PARAM_DNI = "param_dni_Socio"
private const val SOCIO_O_MIEMBRO = "si es socio o miembro"


private var spiner: Spinner? = null
private var btnPagar: Button? = null

class PagoCuotaMensualTwo : Fragment() {
    private var paramTitle: String? = null
    private var paramMonto: String? = null
    private var paramMetodo: String? = null
    private var paramDni: String? = null
    private var paramSocioMiembro: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //El parametro a utilizar
            paramTitle = it.getString(ARG_PARAM_TITLE)
            paramMonto = it.getString(ARG_PARAM_MONTO)
            paramMetodo = it.getString(ARG_PARAM_METODO)
            paramDni = it.getString(ARG_PARAM_DNI)
            paramSocioMiembro= it.getString(SOCIO_O_MIEMBRO)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pago_cuota_mensual_two, container, false)

        val datosPay = PagoCuotaMensual


        spiner = view.findViewById(R.id.spiner_option)
        btnPagar = view.findViewById(R.id.btn_pagar_cuota)
        val mainPage = activity as? MainActivity
        val cuotas = arrayOf( "1 Cuota", "3 Cuotas", "6 Cuotas")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cuotas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spiner?.adapter = adapter

        btnPagar?.setOnClickListener {
            val selectedCuota = spiner?.selectedItem.toString()
            mainPage?.replaceFragment(ConfimPagoCuotaMensual.newInstance(paramTitle!!, paramMonto.toString(),paramMetodo.toString(), paramDni.toString(), selectedCuota, paramSocioMiembro.toString() ))
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
        fun newInstance(
            paramTitle: String,
            paramMonto: String,
            paramMetodo: String,
            paramDni: String,
            paramSocioMiembro: String

        ) =
            PagoCuotaMensualTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, paramTitle)
                    putString(ARG_PARAM_MONTO, paramMonto)
                    putString(ARG_PARAM_METODO, paramMetodo)
                    putString(ARG_PARAM_DNI, paramDni)
                    putString(SOCIO_O_MIEMBRO, paramSocioMiembro)

                }
            }
    }
}