package com.example.powerfit.pages.components

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.powerfit.MainActivity
import com.example.powerfit.R
import com.example.powerfit.dataBase.DatabasePrueba
import com.example.powerfit.pages.components.navModals.SocioAdd
import com.example.powerfit.pages.navigation.socioAddOk

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var domicilio: EditText
private lateinit var fechaNac: EditText
private lateinit var telefono: EditText


class SociosFragmentTwo : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var databasePrueba: DatabasePrueba


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val view = inflater.inflate(R.layout.fragment_socios_two, container, false)
        //Inicio database

        databasePrueba = DatabasePrueba(requireContext())

        domicilio = view.findViewById(R.id.add_frag_domicilio)
        fechaNac = view.findViewById(R.id.add_frag_fecha)
        telefono = view.findViewById(R.id.tel_form_value)

        val textError : TextView = view.findViewById(R.id.add_socios_error_text)
       val buttonAddNavigation: Button = view.findViewById(R.id.btn_add)
        val checkSocio : CheckBox = view.findViewById(R.id.check_es_socio)
        val checkApto : CheckBox = view.findViewById(R.id.check_apto_fisico)

       val activityPagina = activity as? MainActivity
        val socioaddOk = parentFragment as? socioAddOk


        buttonAddNavigation.setOnClickListener{
            val dato = socioaddOk?.passDataToNextFragment()

            val nameSocio = dato?.name.toString()
            val lastSocio = dato?.lastName.toString()
            val dniSocio = dato?.dni.toString()
            val telefonoInt = try {
                telefono.text.toString().toInt()
            } catch (e: NumberFormatException) {
                Log.d("mensaje", "ERROR $e")
            }
            val esSocio = if (checkSocio.isChecked) true else  false
            val emailSocio = dato?.email.toString()
            val adressSocio = domicilio.text.toString()
            val dateSocio = fechaNac.text.toString()
            val tieneApto = if(checkApto.isChecked) true else  false



            val nombresUsuarios = databasePrueba.obtenerNombresUsuario()
            for (nombre in nombresUsuarios) {
                Log.d("NombresMiembros", nombre)
            }

            //val miembroAgregado = dataBase.nuevoMiembro(nameSocio,lastSocio, dniSocio, telefonoInt, esSocio, emailSocio, adressSocio, dateSocio, tieneApto)
val miembroAgregado =0
            //Log.d("muestra socio", "MUESTRA SOCIO: $nameSocio, $lastSocio, $dniSocio, $emailSocio")

            if(nameSocio != "" && adressSocio != "" && lastSocio != "" && dniSocio != "" && emailSocio != "" && dateSocio != "" && esSocio && tieneApto){
                if(miembroAgregado != 1){
                    socioaddOk?.passDataToNextFragment()
                    activityPagina?.replaceFragment(SocioAdd.newInstance(nameSocio,lastSocio, dniSocio, emailSocio, adressSocio, dateSocio, miembroAgregado))
                } else{
                    textError.setText("Ups, el socio ya existe en nuestra base de datos!")
                    Handler(Looper.getMainLooper()).postDelayed({
                        textError.text = ""
                    }, 4000)
                }

            }else{
                textError.setText("Debes ingresar todos los datos y chequear si es socio")
                Handler(Looper.getMainLooper()).postDelayed({
                    textError.text = ""
                }, 4000)
            }


        }

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SociosFragmentTwo.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SociosFragmentTwo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}