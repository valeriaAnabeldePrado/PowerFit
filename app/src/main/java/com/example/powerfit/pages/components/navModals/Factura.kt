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
import com.example.powerfit.pages.navigation.Home

private const val ARG_PARAM_MONTO = "param_monto"
private const val ARG_PARAM_METODO = "param_metodo de pago"
private const val ARG_PARAM_DNI = "param_dni_Socio"
private const val SOCIO_O_MIEMBRO = "si es socio o miembro"
private const val PARAM_NOMBRE_APELLIDO = "param_nombre_apellido"
private const val ARG_FECHA_DE_PAGO = "param_cuotas"


class Factura : Fragment() {

    private var paramMonto: String? = null
    private var paramMetodo: String? = null
    private var paramDni: String? = null
    private var paramSocioMiembro: String? = null
    private var paramnombApellido: String? = null
    private var paramFechaPago: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramMonto = it.getString(ARG_PARAM_MONTO)
            paramMetodo = it.getString(ARG_PARAM_METODO)
            paramDni = it.getString(ARG_PARAM_DNI)
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
        val view = inflater.inflate(R.layout.fragment_factura, container, false)


        val facturaName : TextView = view.findViewById(R.id.factura_name)
        val facturaDni : TextView = view.findViewById(R.id.factura_dni)
        val facturaNumSocio : TextView = view.findViewById(R.id.socio_numero)
        val facturaFechaPago : TextView = view.findViewById(R.id.factura_fecha_pago)
        val facturaMetodo : TextView = view.findViewById(R.id.factura_froma_pago)
        val facturaTotal : TextView = view.findViewById(R.id.factura_total)

        facturaName.setText("$paramnombApellido")
        facturaDni.setText("DNI: $paramDni")
        facturaNumSocio.setText("Tipo: $paramSocioMiembro")
        facturaFechaPago.setText("Fecha de pago: $paramFechaPago")
        facturaMetodo.setText("Forma de pago: $paramMetodo")
        facturaTotal.setText("Total pagado: $paramMonto")

        val btnVerFactura: Button = view.findViewById(R.id.boton_volver)
        val mainPage = activity as? MainActivity

        btnVerFactura.setOnClickListener {
            mainPage?.replaceFragment(Home())
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
         * @return A new instance of fragment Factura.
         */

        @JvmStatic
        fun newInstance(
            paramMonto: String,
            paramMetodo: String,
            paramDni: String,
            paramSocioMiembro: String,
            paramNombreyApellido : String,
            paramFechaPago: String,
        ) =
            Factura().apply {
                arguments = Bundle().apply {
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