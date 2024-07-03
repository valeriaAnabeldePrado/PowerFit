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
private const val SOCIO_ID = "id socio"
private const val ARG_FECHA_DE_PAGO = "param_cuotas"
private const val ES_SOCIO_O_MIEMBRO = "socio o miembro"
private const val PARAM_NOMBRE_APELLIDO = "param_nombre_apellido"


private var spiner: Spinner? = null
private var btnPagar: Button? = null

class PagoCuotaMensualTwo : Fragment() {
    private var paramTitle: String? = null
    private var paramMonto: Int? = null
    private var paramMetodo: String? = null
    private var paramDni: String? = null
    private var paramId: Int? = null
    private var paramFechaPago: String? = null
    private var paramSociooMiembro: Int? = null
    private var paramnombApellido: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //El parametro a utilizar
            paramTitle = it.getString(ARG_PARAM_TITLE)
            paramMonto = it.getInt(ARG_PARAM_MONTO)
            paramMetodo = it.getString(ARG_PARAM_METODO)
            paramDni = it.getString(ARG_PARAM_DNI)
            paramId= it.getInt(SOCIO_ID)
            paramFechaPago = it.getString(ARG_FECHA_DE_PAGO)
            paramSociooMiembro= it.getInt(ES_SOCIO_O_MIEMBRO)
            paramnombApellido= it.getString(PARAM_NOMBRE_APELLIDO)

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
            mainPage?.replaceFragment(ConfimPagoCuotaMensual.newInstance(paramTitle!!, paramMonto!!,paramMetodo.toString(), paramDni.toString(), selectedCuota, paramId!!,paramFechaPago.toString(),paramSociooMiembro!!, paramnombApellido.toString() ))
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
            paramMonto: Int,
            paramMetodo: String,
            paramDni: String,
            paramId: Int,
            paramFechaPago: String,
            paramEsSocioMiembro: Int,
            paramNombreyApellido : String

        ) =
            PagoCuotaMensualTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, paramTitle)
                    putInt(ARG_PARAM_MONTO, paramMonto)
                    putString(ARG_PARAM_METODO, paramMetodo)
                    putString(ARG_PARAM_DNI, paramDni)
                    putInt(SOCIO_ID, paramId)
                    putString(ARG_FECHA_DE_PAGO, paramFechaPago)
                    putInt(ES_SOCIO_O_MIEMBRO, paramEsSocioMiembro)
                    putString(PARAM_NOMBRE_APELLIDO, paramNombreyApellido)

                }
            }
    }
}