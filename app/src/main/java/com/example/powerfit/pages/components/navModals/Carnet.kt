package com.example.powerfit.pages.components.navModals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.powerfit.MainActivity
import com.example.powerfit.R
import com.example.powerfit.pages.navigation.Home
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val NAME_SOCIO = "nombre"
private const val LASTNAME_SOCIO = "apellido"
private const val DNI_SOCIO = "dni"
private const val MAIL_SOCIO = "mail"
private const val SOCIO_CODE = "num socio"


class Carnet : Fragment() {

    private var paramName: String? = null
    private var paramLastN: String? = null
    private var paramDni: String? = null
    private var paramMail: String? = null
    private var paramSocioCode: Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramName = it.getString(NAME_SOCIO)
            paramLastN = it.getString(LASTNAME_SOCIO)
            paramDni = it.getString(DNI_SOCIO)
            paramMail = it.getString(MAIL_SOCIO)
            paramSocioCode = it.getInt(SOCIO_CODE)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carnet, container, false)

        val nameCarnet : TextView= view.findViewById(R.id.carnet_name)
        val dniCarnet : TextView= view.findViewById(R.id.carnet_dni)
        val numSocio : TextView = view.findViewById(R.id.carnet_numero)

        val mailCarnet : TextView= view.findViewById(R.id.carnet_mail)

        val responseText: TextView = view.findViewById(R.id.response_text)

        nameCarnet.setText("$paramName $paramLastN")
        dniCarnet.text = paramDni
        mailCarnet.text = paramMail
        numSocio.text = paramSocioCode.toString()

        val btnVerSendCarnet: Button = view.findViewById(R.id.boton_volver_home)
        btnVerSendCarnet.setOnClickListener {
            responseText.text = "Carnet enviado exitosamente!"
            viewLifecycleOwner.lifecycleScope.launch {
                delay(3000)
                volver()
            }

        }

        return view
    }


    private fun volver() {
        val mainPage = activity as? MainActivity
        mainPage?.replaceFragment(Home())

    }

    companion object {

        @JvmStatic
        fun newInstance( nameSocio: String,
                         lastNaSocio: String,
                         dniSocio: String,
                         emailSocio: String,
                         paramSocioCode : Int
                        ) =
            Carnet().apply {
                arguments = Bundle().apply {
                    putString(NAME_SOCIO, nameSocio)
                    putString(LASTNAME_SOCIO, lastNaSocio)
                    putString(DNI_SOCIO, dniSocio)
                    putString(MAIL_SOCIO, emailSocio)
                    putInt(SOCIO_CODE, paramSocioCode)
                }
            }
    }
}