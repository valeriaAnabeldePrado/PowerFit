package com.example.powerfit.pages.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.powerfit.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SociosFragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class SociosFragmentOne : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var nameSocio: EditText
    private lateinit var lastNSocio: EditText
    private lateinit var dniSocio: EditText
    private lateinit var mailSocio: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_socios_one, container, false)

        nameSocio = view.findViewById(R.id.name_form_value)
        lastNSocio = view.findViewById(R.id.lastN_form_value)
        dniSocio = view.findViewById(R.id.dni_form_value)
        mailSocio = view.findViewById(R.id.email_form_value)



        return view
    }

    fun getSocioData(): SocioData {
        return SocioData(
            nameSocio.text.toString(),
            lastNSocio.text.toString(),
            dniSocio.text.toString(),
            mailSocio.text.toString()
        )
    }

    data class SocioData(val name: String, val lastName: String, val dni: String, val email: String)
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SociosFragmentOne.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SociosFragmentOne().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}