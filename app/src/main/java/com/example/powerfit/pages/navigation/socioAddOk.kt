package com.example.powerfit.pages.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.powerfit.R
import com.example.powerfit.pages.components.FragmentPageAdapter
import com.example.powerfit.pages.components.SociosFragmentOne
import com.example.powerfit.pages.components.SociosFragmentTwo


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var viewPager2: ViewPager2
private lateinit var fragmentOne: SociosFragmentOne
private lateinit var fragmentTwo: SociosFragmentTwo
/**
 * A simple [Fragment] subclass.
 * Use the [socioAddOk.newInstance] factory method to
 * create an instance of this fragment.
 */
class socioAddOk : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_socio_add_ok, container, false)

        fragmentOne = SociosFragmentOne()
        fragmentTwo = SociosFragmentTwo()

        viewPager2 = view.findViewById(R.id.viewPager2)
        val fragments = listOf(fragmentOne, fragmentTwo)
        val adapter = FragmentPageAdapter(childFragmentManager, lifecycle, fragments)
        viewPager2.adapter = adapter

        return view
    }
    fun passDataToNextFragment(): SociosFragmentOne.SocioData {
        val data = fragmentOne.getSocioData()
        return data
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PagoCuota.
         */

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            socioAddOk().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}