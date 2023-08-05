package com.example.quizzo.ui.home.play.fragment

import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentFinishArenaBinding
import com.example.quizzo.utils.PlayerStatus


class FinishArenaFragment : Fragment() {


    lateinit var viewBinding: FragmentFinishArenaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentFinishArenaBinding.inflate(layoutInflater, container, false)
        val bundle = this.arguments
        if (bundle != null) {
            val id =
                bundle.getInt("game_over_status", 0) // The second parameter is a default value.

            val totalCorrect = bundle.getInt("total_correct",0)
            setStatusUpdateUi(id,totalCorrect)
        }
        return viewBinding.root
    }

    private fun setStatusUpdateUi(id: Int, totalCorrect: Int) {

        val animator = ValueAnimator.ofInt(0, 10 * totalCorrect).apply {
            duration = 700
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                val progress = animation.animatedValue as Int
                viewBinding.progress.progress = progress.toFloat()
            }
            start()
        }
        viewBinding.totalCorrectPercent.text = "${(10 * totalCorrect)}%"

        when (id) {
            PlayerStatus.PLAYER_NO_TIME -> {

                viewBinding.parentBg.backgroundTintList =
                    ColorStateList.valueOf(requireActivity().getColor(R.color.red))
                val window: Window = activity!!.window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(
                    activity!!, R.color.red
                )


            }
            PlayerStatus.PLAYER_IS_DIED -> {
                viewBinding.playerStatusTextView.text = "Joningiz tugadi"
            }
            PlayerStatus.PLAYER_IS_ALIVE -> {

            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}