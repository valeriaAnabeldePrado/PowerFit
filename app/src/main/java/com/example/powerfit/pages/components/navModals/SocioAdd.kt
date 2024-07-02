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
private const val NAME_SOCIO = "nombre"
private const val LASTNAME_SOCIO = "apellido"
private const val DNI_SOCIO = "dni"
private const val MAIL_SOCIO = "mail"
private const val ADRESS_SOCIO = "direccion"
private const val DATE_SOCIO = "fecha nacimiento"
private const val SOCIO_CODE = "num socio"


class SocioAdd : Fragment() {

    private var paramName: String? = null
    private var paramLastN: String? = null
    private var paramDni: String? = null
    private var paramMail: String? = null
    private var paramAdress: String? = null
    private var paramDate: String? = null
    private var paramSocioCode: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramName = it.getString(NAME_SOCIO)
            paramLastN = it.getString(LASTNAME_SOCIO)
            paramDni = it.getString(DNI_SOCIO)
            paramMail = it.getString(MAIL_SOCIO)
            paramAdress = it.getString(ADRESS_SOCIO)
            paramSocioCode = it.getInt(SOCIO_CODE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_socio_add, container, false)
        val msjText : TextView = view.findViewById(R.id.msj_add_ok)
        Log.d("mensaje", "TEXTO $paramName, $paramLastN, $paramDate, $paramDni")
        msjText.setText("Los datos de $paramName fueron cargados correctamente! Código número $paramSocioCode. Ya podes enviarle su carnet")
        val btnEnviar : Button = view.findViewById(R.id.btn_enviar_carnet)
        val mainActivityPage = activity as? MainActivity

        btnEnviar.setOnClickListener{
            mainActivityPage?.replaceFragment(Carnet.newInstance(paramName.toString(), paramLastN.toString(), paramDni.toString(), paramMail.toString(), paramSocioCode!!))
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(
            nameSocio: String,
            lastNaSocio: String,
            dniSocio: String,
            emailSocio: String,
            adressSocio: String,
            dateSocio: String,
            paramSocioCode : Int
        ) =
            SocioAdd().apply {
                arguments = Bundle().apply {
                    putString(NAME_SOCIO, nameSocio)
                    putString(LASTNAME_SOCIO, lastNaSocio)
                    putString(DNI_SOCIO, dniSocio)
                    putString(MAIL_SOCIO, emailSocio)
                    putString(ADRESS_SOCIO, adressSocio)
                    putString(DATE_SOCIO, dateSocio)
                    putInt(SOCIO_CODE, paramSocioCode)
                }
            }
    }
}