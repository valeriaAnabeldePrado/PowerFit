package com.example.powerfit.pages.components.navModals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.powerfit.R
import com.example.powerfit.pages.components.FragmentPageAdapter
import com.example.powerfit.pages.components.PagoCuotaMensualOne
import com.example.powerfit.pages.components.PagoCuotaMensualTwo

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PagoCuotaCard.newInstance] factory method to
 * create an instance of this fragment.
 */
class PagoCuotaCard : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_pago_cuota_card, container, false)

        val viewPageFragments : ViewPager2 = view.findViewById(R.id.viewPagerCard)
        val fragmentsGroup = listOf(PagoCuotaMensualOne(), PagoCuotaMensualTwo())

        val adapterPage = FragmentPageAdapter(childFragmentManager, lifecycle, fragmentsGroup)
        viewPageFragments.adapter = adapterPage



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PagoCuotaCard.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PagoCuotaCard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}