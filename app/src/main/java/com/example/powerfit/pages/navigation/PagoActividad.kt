package com.example.powerfit.pages.navigation

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.powerfit.MainActivity
import com.example.powerfit.R
import com.example.powerfit.dataBase.DataBase
import com.example.powerfit.pages.components.navModals.ConfimPagoCuotaMensual
import com.example.powerfit.pages.components.navModals.PagoCuotaCard
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var spiner : Spinner? = null
private var btnCash : Button? = null
private var btnCard : Button? = null
private lateinit var dniMiebroActividad : TextView
private  lateinit var montoActividad : TextView


class PagoActividad : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var databasePrueba: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {

        databasePrueba = DataBase(requireContext())

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val actividades = arrayOf("Musculación", "Crossfit", "Funcional", "Zumba")
        val view = inflater.inflate(R.layout.fragment_pago_actividad, container, false)
        var numeroSocio = 0
        var esSocio = 0
        spiner = view.findViewById(R.id.spiner_option_actividad)

        dniMiebroActividad = view.findViewById(R.id.pago_actividad_dni_value)
        montoActividad = view.findViewById(R.id.pago_actividad_monto_value)



        btnCard = view.findViewById(R.id.btn_card_actividad)
        btnCash = view.findViewById(R.id.btn_cash_actividad)
        val mainPage = activity as? MainActivity

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, actividades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spiner?.adapter = adapter

        btnCash?.setOnClickListener {
            val noSocioActividadSelected = spiner?.selectedItem.toString()
            val datosPago = getPayActividadDataCash()
            val cantidadCuotas = ""
            val obtenerTipoPago = databasePrueba.getTipoPago(datosPago.dni)
            val socioExiste = databasePrueba.socioExiste(datosPago.dni)
            val nombreYapellido = databasePrueba.nombreyApellido(datosPago.dni)
            val fechadePago = obtenerFechaActual()
            if(socioExiste !=0){
                numeroSocio = socioExiste
                if(obtenerTipoPago == 0){
                    esSocio = 0
                    mainPage?.replaceFragment(ConfimPagoCuotaMensual.newInstance(datosPago.paramTitle, datosPago.montoCash, datosPago.metodo, datosPago.dni, cantidadCuotas, numeroSocio,fechadePago, esSocio,  nombreYapellido ))
                }else{
                    showDialog("Atención! Solo los no socios pueden pagar por actividad")
                }
            }else{
                showDialog("Atención! La persona no fue registrado, no se encuentra en la base de datos")
            }


        }



        btnCard?.setOnClickListener {
            val noSocioActividadSelected = spiner?.selectedItem.toString()
            val datosPago =getPayActividadDataCard()
            val obtenerTipoPago = databasePrueba.getTipoPago(datosPago.dni)
            val socioExiste = databasePrueba.socioExiste(datosPago.dni)
            val fechadePago = obtenerFechaActual()
            val nombreYapellido = databasePrueba.nombreyApellido(datosPago.dni)

            if(socioExiste !=0){
                numeroSocio = socioExiste
                if(obtenerTipoPago == 0){
                    esSocio = 0
                    mainPage?.replaceFragment(PagoCuotaCard.newInstance(datosPago.paramTitle, datosPago.montoCard, datosPago.metodo, datosPago.dni, numeroSocio,fechadePago, esSocio,  nombreYapellido))
                }else{
                    showDialog("Atención! Solo los no socios pueden pagar por actividad")
                }
            }else{
                showDialog("Atención! La persona no fue registrado, no se encuentra en la base de datos")
            }

        }

        return view
    }


    fun getPayActividadDataCard(): PayActividadCard {
        val montoCard = montoActividad.text.toString().toIntOrNull() ?: 0
        return PayActividadCard(
            dniMiebroActividad.text.toString(),
            montoCard
        )
    }
    fun getPayActividadDataCash(): PayActividadCash {
        val montoCash = montoActividad.text.toString().toIntOrNull() ?: 0
        return PayActividadCash(
            dniMiebroActividad.text.toString(),
            montoCash
        )
    }
    data class PayActividadCash(val dni: String, val montoCash: Int, val metodo : String = "Con Efectivo",val paramTitle : String = "Pago de actividad" )
    data class PayActividadCard(val dni: String, val montoCard: Int, val metodo : String = "Con Tarjeta",val paramTitle : String = "Pago de actividad" )
    override fun onDestroyView() {
        super.onDestroyView()
        spiner = null
        btnCash = null
        btnCard = null
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun obtenerFechaActual(): String {
        val fechaActual = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return fechaActual.format(formatter)
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
    private fun showDialog( mensaje : String) {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_box_warning)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        dialog.window?.setBackgroundDrawable(requireContext().getDrawable(R.drawable.modal_violet))

        val btnVolver: Button = dialog.findViewById(R.id.btn_cerrar_dialog)
        val text_dialog: TextView = dialog.findViewById(R.id.text_dialog)
        text_dialog.setText(mensaje)
        dialog.show()

        btnVolver.setOnClickListener {
            dialog.dismiss()
        }

    }
}