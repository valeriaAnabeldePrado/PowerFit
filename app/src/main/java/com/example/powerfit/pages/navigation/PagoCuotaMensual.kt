package com.example.powerfit.pages.navigation

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

private lateinit var  inputDniSocio : EditText
private lateinit var inputMontoSocio : EditText



class PagoCuotaMensual : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var databasePrueba: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databasePrueba = DataBase(requireContext())
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_pago_cuota_mensual, container, false)
        val btnCard : Button = view.findViewById(R.id.btn_card)
        val btnCash : Button = view.findViewById(R.id.btn_cash)
       inputDniSocio  = view.findViewById(R.id.dni_socio_value)
        inputMontoSocio = view.findViewById(R.id.monto_socio_value)
        var numeroSocio = 0
        var esSocio = 0

        val mainPage = activity as? MainActivity


        btnCard.setOnClickListener {
            val payDataCard = getPayDataCard()
            val obtenerTipoPago = databasePrueba.getTipoPago(payDataCard.dni)
            val socioExiste = databasePrueba.socioExiste(payDataCard.dni)
            val fechadePago = obtenerFechaActual()
            val nombreYapellido = databasePrueba.nombreyApellido(payDataCard.dni)

            if(socioExiste != 0){
                numeroSocio = socioExiste
                if(obtenerTipoPago == 1){
                    esSocio = 1
                    val verifCuotaPagada = databasePrueba.verificarCuotaPagada(socioExiste,fechadePago)
                    if(!verifCuotaPagada){
                        mainPage?.replaceFragment(PagoCuotaCard.newInstance(payDataCard.paramTitle, payDataCard.montoCard, payDataCard.metodo, payDataCard.dni, numeroSocio,fechadePago, esSocio,  nombreYapellido))
                    } else{
                        showDialog("Atención! El socio ya pago la cuota anteriormente")
                    }

                }else{
                    showDialog("Atención! solo Los socios pueden pagar la cuota mensual")
                }
            }else{
                showDialog("Atención! El socio no fue registrado, no se encuentra en la base de datos")
            }
        }

        btnCash.setOnClickListener {
            val payDataCash = getPayDataCash()
            val obtenerTipoPago = databasePrueba.getTipoPago(payDataCash.dni)
            val socioExiste = databasePrueba.socioExiste(payDataCash.dni)
            val nombreYapellido = databasePrueba.nombreyApellido(payDataCash.dni)
            val fechadePago = obtenerFechaActual()
            if(socioExiste != 0){
                numeroSocio = socioExiste
                if(obtenerTipoPago == 1){
                    esSocio = 1
                    val verifCuotaPagada = databasePrueba.verificarCuotaPagada(socioExiste,fechadePago)
                    if(!verifCuotaPagada){
                        mainPage?.replaceFragment(ConfimPagoCuotaMensual.newInstance(payDataCash.paramTitle, payDataCash.montoCash, payDataCash.metodo, payDataCash.dni, paramCuotas = "0", numeroSocio, fechadePago, esSocio, nombreYapellido))
                    }else{
                        showDialog("Atención! El socio ya pago la cuota anteriormente")
                    }
                }else{
                    showDialog("Atención! solo Los socios pueden pagar la cuota mensual")
                }
            } else{
                showDialog("Atención! El socio no fue registrado, no se encuentra en la base de datos")
            }

        }


        return view
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun obtenerFechaActual(): String {
        val fechaActual = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return fechaActual.format(formatter)
    }
    fun getPayDataCard(): PayDataCard {
        val montoCard = inputMontoSocio.text.toString().toIntOrNull() ?: 0
        return PayDataCard(
            inputDniSocio.text.toString(),
            montoCard
        )
    }
    fun getPayDataCash(): PayDataCash {
        val montoCash = inputMontoSocio.text.toString().toIntOrNull() ?: 0
        return PayDataCash(
            inputDniSocio.text.toString(),
            montoCash
        )
    }
    data class PayDataCash(val dni: String, val montoCash: Int, val metodo : String = "Con Efectivo",val paramTitle : String = "Pago mensual de cuota" )
    data class PayDataCard(val dni: String, val montoCard: Int, val metodo : String = "Con Tarjeta",val paramTitle : String = "Pago mensual de cuota" )

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PagoCuotaMensual().apply {
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
