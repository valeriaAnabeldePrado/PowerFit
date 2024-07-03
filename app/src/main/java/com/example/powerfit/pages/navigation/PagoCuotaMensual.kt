package com.example.powerfit.pages.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.powerfit.MainActivity
import com.example.powerfit.R
import com.example.powerfit.dataBase.DataBase
import com.example.powerfit.pages.components.navModals.ConfimPagoCuotaMensual
import com.example.powerfit.pages.components.navModals.PagoCuotaCard


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var  inputDniSocio : EditText
private lateinit var inputMontoSocio : EditText
/**
 * A simple [Fragment] subclass.
 * Use the [PagoCuotaMensual.newInstance] factory method to
 * create an instance of this fragment.
 */
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
    //dniMiembro: String, monto: Double, fechaPago: String, tipoPago: Int getTipoPago
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_pago_cuota_mensual, container, false)
        val btnCard : Button = view.findViewById(R.id.btn_card)
        val btnCash : Button = view.findViewById(R.id.btn_cash)
       inputDniSocio  = view.findViewById(R.id.dni_socio_value)
        inputMontoSocio = view.findViewById(R.id.monto_socio_value)
        val numeroSocio = "NUMERO SOCIO"

        val mainPage = activity as? MainActivity


        btnCard.setOnClickListener {
            val payDataCard = getPayDataCard()
            mainPage?.replaceFragment(PagoCuotaCard.newInstance(payDataCard.paramTitle, payDataCard.montoCard, payDataCard.metodo, payDataCard.dni, numeroSocio))
        }

        btnCash.setOnClickListener {
            val payDataCash = getPayDataCash()
            val obtenerTipoPago = databasePrueba.getTipoPago(payDataCash.dni)
            Log.d("mensaje", "EL TIPO DE PAGO ES: $obtenerTipoPago")
            mainPage?.replaceFragment(ConfimPagoCuotaMensual.newInstance(payDataCash.paramTitle, payDataCash.montoCash, payDataCash.metodo, payDataCash.dni, paramCuotas = "0", numeroSocio))
        }


        return view
    }

    fun getPayDataCard(): PayDataCard {
        return PayDataCard(
            inputDniSocio.text.toString(),
            inputMontoSocio.text.toString(),
        )
    }
    fun getPayDataCash(): PayDataCash {
        return PayDataCash(
            inputDniSocio.text.toString(),
            inputMontoSocio.text.toString(),
        )
    }
    data class PayDataCash(val dni: String, val montoCash: String, val metodo : String = "Con Efectivo",val paramTitle : String = "Pago mensual de cuota" )
    data class PayDataCard(val dni: String, val montoCard: String, val metodo : String = "Con Tarjeta",val paramTitle : String = "Pago mensual de cuota" )

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
}