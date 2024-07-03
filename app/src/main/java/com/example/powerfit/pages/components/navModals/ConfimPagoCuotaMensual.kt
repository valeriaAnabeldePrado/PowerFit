package com.example.powerfit.pages.components.navModals

import android.os.Bundle
import android.util.Log
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
private const val ARG_PARAM_MONTO = "param_monto"
private const val ARG_PARAM_METODO = "param_metodo de pago"
private const val ARG_PARAM_DNI = "param_dni_Socio"
private const val ARG_PARAM_CUOTAS = "param_cuotas"
private const val SOCIO_O_MIEMBRO = "si es socio o miembro"


class ConfimPagoCuotaMensual : Fragment() {

    private var paramTitle: String? = null
    private var paramMonto: String? = null
    private var paramMetodo: String? = null
    private var paramDni: String? = null
    private var paramCuotas: String? = null
    private var totalApagar: Int = 3000
    private var paramSocioMiembro: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramTitle = it.getString(ARG_PARAM_TITLE)
            paramMonto = it.getString(ARG_PARAM_MONTO)
            paramMetodo = it.getString(ARG_PARAM_METODO)
            paramDni = it.getString(ARG_PARAM_DNI)
            paramCuotas = it.getString(ARG_PARAM_CUOTAS)
            paramSocioMiembro= it.getString(SOCIO_O_MIEMBRO)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_confim_pago_cuota_mensual, container, false)
        val btnConfirmPay: Button = view.findViewById(R.id.btn_confirm_pago)
        val textTitle: TextView = view.findViewById(R.id.subtitulo_mensual)
        textTitle.text = paramTitle
        val confirmPagoText: TextView = view.findViewById(R.id.confirmaciondefactura)
        Log.d(
            "mensaje",
            "NOMBRE,  NOMBRE Y APELLIDO, monto: $paramMonto, $paramSocioMiembro, DNI: $paramDni, Fecha de Pago:FECHA , Fecha de vencimiento: FECHA, Método de pago: $paramMetodo, Total con descuento: DESCUENTO"
        )
        confirmPagoText.text =
            "NOMBRE Y APELLIDO, monto: $paramMonto, $paramSocioMiembro, DNI: $paramDni, Fecha de Pago:FECHA , Fecha de vencimiento: FECHA, Método de pago: $paramMetodo, Total con descuento: DESCUENTO"
        val mainPage = activity as? MainActivity

        btnConfirmPay.setOnClickListener {
            mainPage?.replaceFragment(
                ReConfirPagoCuotaOk.newInstance(
                    paramTitle!!,
                    paramMonto.toString(),
                    paramMetodo.toString(),
                    paramDni.toString(),
                    totalApagar,
                    paramSocioMiembro.toString()
                )
            )
        }

        return view
    }

    companion object {


        @JvmStatic
        fun newInstance(
            paramTitle: String,
            paramMonto: String,
            paramMetodo: String,
            paramDni: String,
            paramCuotas: String,
            paramSocioMiembro: String
        ) =
            ConfimPagoCuotaMensual().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, paramTitle)
                    putString(ARG_PARAM_MONTO, paramMonto)
                    putString(ARG_PARAM_METODO, paramMetodo)
                    putString(ARG_PARAM_DNI, paramDni)
                    putString(ARG_PARAM_CUOTAS, paramCuotas)
                    putString(SOCIO_O_MIEMBRO, paramSocioMiembro)

                }
            }
    }
}