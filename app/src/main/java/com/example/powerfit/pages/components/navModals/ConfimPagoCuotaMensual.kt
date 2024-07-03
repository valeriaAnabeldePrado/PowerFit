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
import com.example.powerfit.dataBase.DataBase


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM_TITLE = "El Titulo"
private const val ARG_PARAM_MONTO = "param_monto"
private const val ARG_PARAM_METODO = "param_metodo de pago"
private const val ARG_PARAM_DNI = "param_dni_Socio"
private const val ARG_PARAM_CUOTAS = "param_cuotas"
private const val SOCIO_ID = "id socio"
private const val ARG_FECHA_DE_PAGO = "param_cuotas"
private const val ES_SOCIO_O_MIEMBRO = "socio o miembro"
private const val PARAM_NOMBRE_APELLIDO = "param_nombre_apellido"


class ConfimPagoCuotaMensual : Fragment() {

    private var paramTitle: String? = null
    private var paramMonto: Int? = null
    private var paramMetodo: String? = null
    private var paramDni: String? = null
    private var paramCuotas: String? = null
    private var paramId: Int? = null
    private var paramFechaPago: String? = null
    private var paramSociooMiembro: Int? = null
    private var paramnombApellido: String? = null

    private lateinit var databasePrueba: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databasePrueba = DataBase(requireContext())
        arguments?.let {
            paramTitle = it.getString(ARG_PARAM_TITLE)
            paramMonto = it.getInt(ARG_PARAM_MONTO)
            paramMetodo = it.getString(ARG_PARAM_METODO)
            paramDni = it.getString(ARG_PARAM_DNI)
            paramCuotas = it.getString(ARG_PARAM_CUOTAS)
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
        val view = inflater.inflate(R.layout.fragment_confim_pago_cuota_mensual, container, false)
        val btnConfirmPay: Button = view.findViewById(R.id.btn_confirm_pago)
        val textTitle: TextView = view.findViewById(R.id.subtitulo_mensual)
        textTitle.text = paramTitle
        val confirmPagoText: TextView = view.findViewById(R.id.confirmaciondefactura)
       val idMiembro = paramId.toString()
        val socioMiembro = paramSociooMiembro.toString()
        val montoDouble = paramMonto?.toDouble()
        confirmPagoText.text =
            "$paramnombApellido, monto: $paramMonto, $idMiembro, DNI: $paramDni, Fecha de Pago: $paramFechaPago , Método de pago: $paramMetodo, Total a pagar: $paramMonto"
        val mainPage = activity as? MainActivity

        btnConfirmPay.setOnClickListener {
            val pagado = databasePrueba.realizarPago(paramId!!, montoDouble!!, paramFechaPago.toString(), paramSociooMiembro!!, paramSociooMiembro!!)
            Log.d("mensaje","PAGO RESULTO: $pagado")
            mainPage?.replaceFragment(
                ReConfirPagoCuotaOk.newInstance(
                    paramTitle!!,
                    paramMonto.toString(),
                    paramMetodo.toString(),
                    paramDni.toString(),
                    socioMiembro,
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
            paramMonto: Int,
            paramMetodo: String,
            paramDni: String,
            paramCuotas: String,
            paramId: Int,
            paramFechaPago: String,
            paramEsSocioMiembro: Int,
            paramNombreyApellido : String
        ) =
            ConfimPagoCuotaMensual().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, paramTitle)
                    putInt(ARG_PARAM_MONTO, paramMonto)
                    putString(ARG_PARAM_METODO, paramMetodo)
                    putString(ARG_PARAM_DNI, paramDni)
                    putString(ARG_PARAM_CUOTAS, paramCuotas)
                    putInt(SOCIO_ID, paramId)
                    putString(ARG_FECHA_DE_PAGO, paramFechaPago)
                    putInt(ES_SOCIO_O_MIEMBRO, paramEsSocioMiembro)
                    putString(PARAM_NOMBRE_APELLIDO, paramNombreyApellido)
                }
            }
    }
}