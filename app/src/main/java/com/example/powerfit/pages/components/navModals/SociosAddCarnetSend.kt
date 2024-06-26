package com.example.powerfit.pages.components.navModals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.powerfit.MainActivity
import com.example.powerfit.R

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var btnSendCarnet : Button? = null

/**
 * A simple [Fragment] subclass.
 * Use the [SociosAddCarnetSend.newInstance] factory method to
 * create an instance of this fragment.
 */
class SociosAddCarnetSend : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_socios_add_carnet_send, container, false)
        btnSendCarnet = view.findViewById(R.id. btn_ver_carnet)
        val mainPage = activity as? MainActivity

        btnSendCarnet?.setOnClickListener {
            mainPage?.replaceFragment(Carnet())
        }

        return view

    }
    override fun onDestroyView() {
        super.onDestroyView()
        btnSendCarnet = null
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SociosAddCarnetSend.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SociosAddCarnetSend().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}