package com.example.powerfit.pages.components.navModals

import android.annotation.SuppressLint
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
private const val ARG_PARAM_MONTO = "param_monto"
private const val ARG_PARAM_METODO = "param_metodo de pago"
private const val ARG_PARAM_DNI = "param_dni_Socio"
//private const val ARG_PARAM_TOTAL = "total a pagar"
private const val SOCIO_O_MIEMBRO = "si es socio o miembro"
private const val PARAM_NOMBRE_APELLIDO = "param_nombre_apellido"
private const val ARG_FECHA_DE_PAGO = "param_cuotas"


class ReConfirPagoCuotaOk : Fragment() {

    private var paramTitle: String? = null
    private var paramMonto: String? = null
    private var paramMetodo: String? = null
    private var paramDni: String? = null
    //private var paramTotalaPagar: Int? = null
    private var paramSocioMiembro: String? = null
    private var paramnombApellido: String? = null
    private var paramFechaPago: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramTitle = it.getString(ARG_PARAM_TITLE)
            paramMonto = it.getString(ARG_PARAM_MONTO)
            paramMetodo = it.getString(ARG_PARAM_METODO)
            paramDni = it.getString(ARG_PARAM_DNI)
            //paramTotalaPagar = it.getInt(ARG_PARAM_TOTAL)
            paramSocioMiembro= it.getString(SOCIO_O_MIEMBRO)
            paramnombApellido= it.getString(PARAM_NOMBRE_APELLIDO)
            paramFechaPago = it.getString(ARG_FECHA_DE_PAGO)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_re_confir_pago_cuota_ok, container, false)

        val btnVerFactura: Button = view.findViewById(R.id.boton_facturac)
        val mainPage = activity as? MainActivity
        val textTitle: TextView = view.findViewById(R.id.text_cota_OK)
        val textConfirmPagado: TextView = view.findViewById(R.id.text_confirm_pagado)

        textConfirmPagado.setText("El pago de $paramnombApellido fue generado correctamente! Ya puedes enviar la factura")
        textTitle.text = paramTitle
        btnVerFactura.setOnClickListener {
            mainPage?.replaceFragment(
                Factura.newInstance(
                    paramMonto.toString(),
                    paramMetodo.toString(),
                    paramDni.toString(),
                    paramSocioMiembro.toString(),
                    paramnombApellido.toString(),
                    paramFechaPago.toString()
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
            paramSocioMiembro: String,
            paramNombreyApellido : String,
            paramFechaPago: String,
        ) =
            ReConfirPagoCuotaOk().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, paramTitle)
                    putString(ARG_PARAM_MONTO, paramMonto)
                    putString(ARG_PARAM_METODO, paramMetodo)
                    putString(ARG_PARAM_DNI, paramDni)
                    putString(SOCIO_O_MIEMBRO, paramSocioMiembro)
                    putString(PARAM_NOMBRE_APELLIDO, paramNombreyApellido)
                    putString(ARG_FECHA_DE_PAGO, paramFechaPago)

                }
            }


    }
}