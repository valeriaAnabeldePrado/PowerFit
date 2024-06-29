package com.example.powerfit.pages.components.navModals

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.powerfit.R
import com.example.powerfit.pages.components.FragmentPageAdapter
import com.example.powerfit.pages.components.PagoCuotaMensualOne
import com.example.powerfit.pages.components.PagoCuotaMensualTwo

//El subTitulo que se pasa como parametro
private const val ARG_PARAM_TITLE = "param_title"

class PagoCuotaCard : Fragment() {

    private var paramTitle: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //El parametro a utilizar
            paramTitle = it.getString(ARG_PARAM_TITLE)
        }
    }
 private var isCardFront = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pago_cuota_card, container, false)
        val cardFlip: FrameLayout? = view.findViewById(R.id.card_flip)
        val frontCard: FrameLayout? = view.findViewById(R.id.card_face_front)
        val backCard: FrameLayout? = view.findViewById(R.id.card_face_back)
        val viewPageFragments: ViewPager2 = view.findViewById(R.id.viewPagerCard)
        val fragmentsGroup = listOf(PagoCuotaMensualOne(), PagoCuotaMensualTwo.newInstance(paramTitle!!))
        val adapterPage = FragmentPageAdapter(childFragmentManager, lifecycle, fragmentsGroup)
        viewPageFragments.adapter = adapterPage


        cardFlip?.setOnClickListener {
            it.isEnabled = false
            isCardFront = !isCardFront
            if (isCardFront) {
                flipCard(requireContext(), frontCard!!, backCard!!)
            } else {
                flipCard(requireContext(), backCard!!, frontCard!!)
            }
        }
        //Cambio el texto de parametro
        val textTitle : TextView = view.findViewById(R.id.text_title_param)
        textTitle.text = paramTitle
        return view
    }
    private fun flipCard(context: Context, visibleView: View, inVisibleView: View) {
        try {
            visibleView.visibility = View.VISIBLE
            val scale = context.resources.displayMetrics.density
            val cameraDist = 8000 * scale
            visibleView.cameraDistance = cameraDist
            inVisibleView.cameraDistance = cameraDist
            val flipOutAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_out) as Animator
            flipOutAnimatorSet.setTarget(inVisibleView)
            val flipInAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_in) as AnimatorSet
            flipInAnimatorSet.setTarget(visibleView)

            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()
            flipInAnimatorSet.doOnEnd {
                inVisibleView.visibility = View.GONE
                view?.findViewById<FrameLayout>(R.id.card_flip)?.isEnabled = true
            }
        } catch (e: Exception) {
            Log.e(TAG, "flipCard $e")
        }
    }

    companion object {


        @JvmStatic
        fun newInstance(paramTitle: String) =
            PagoCuotaCard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_TITLE, paramTitle)
                }
            }
    }
}