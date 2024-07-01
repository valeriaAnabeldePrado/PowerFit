package com.example.powerfit.pages.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.powerfit.R
import com.example.powerfit.pages.components.MorososAdapter
import com.example.powerfit.pages.components.MorososList


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListaMorosos : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_lista_morosos, container, false)
        initRecyclerView(view)





        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recicler_container)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = MorososAdapter(MorososList.morososListado)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListaMorosos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListaMorosos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}