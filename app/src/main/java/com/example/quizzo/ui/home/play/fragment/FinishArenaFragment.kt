package com.example.quizzo.ui.home.play.fragment

import android.animation.ValueAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.*
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.quizzo.R
import com.example.quizzo.databinding.FragmentFinishArenaBinding
import com.example.quizzo.ui.home.HomeActivity
import com.example.quizzo.utils.PlayerStatus


class FinishArenaFragment : Fragment() {

    lateinit var viewBinding: FragmentFinishArenaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentFinishArenaBinding.inflate(inflater, container, false)
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

        viewBinding.finishButton.setOnClickListener {
            HomeActivity.open(requireActivity())
        }

        when (id) {
            PlayerStatus.PLAYER_NO_TIME -> {
                viewBinding.playerStatusTextView.text = "Vaqt tugadi"
                viewBinding.finishFeedback.text = "Keyingi urunishda tezroq harakat qiling!"
                viewBinding.parentBg.backgroundTintList =
                    ColorStateList.valueOf(requireActivity().getColor(R.color.times_up))
                viewBinding.view3.backgroundTintList = ColorStateList.valueOf(requireActivity().getColor(R.color.times_up))

                viewBinding.pbTimer.progressTintList =  ColorStateList.valueOf(requireActivity().getColor(R.color.times_up_for_progress))
                viewBinding.progress.backgroundProgressBarColor = requireActivity().getColor(R.color.times_up_for_progress)
                val window: Window = activity!!.window

                viewBinding.finishButton.setTextColor(ColorStateList.valueOf(requireActivity().getColor(R.color.times_up)))
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(
                    activity!!, R.color.times_up
                )


            }
            PlayerStatus.PLAYER_IS_DIED -> {
                viewBinding.playerStatusTextView.text = "O'yin tugadi"
                viewBinding.finishFeedback.text = "Keyingi o'yinlarda omad!"
                viewBinding.parentBg.backgroundTintList =
                    ColorStateList.valueOf(requireActivity().getColor(R.color.times_up))
                viewBinding.view3.backgroundTintList = ColorStateList.valueOf(requireActivity().getColor(R.color.times_up))

                viewBinding.pbTimer.progressTintList =  ColorStateList.valueOf(requireActivity().getColor(R.color.times_up_for_progress))
                viewBinding.progress.backgroundProgressBarColor = requireActivity().getColor(R.color.times_up_for_progress)
                val window: Window = activity!!.window

                viewBinding.finishButton.setTextColor(ColorStateList.valueOf(requireActivity().getColor(R.color.times_up)))
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(
                    activity!!, R.color.times_up
                )
            }
            PlayerStatus.PLAYER_IS_ALIVE -> {
                viewBinding.playerStatusTextView.text = "Tabriklaymiz"
                viewBinding.finishFeedback.text = "Yaxshi natija"
                viewBinding.parentBg.backgroundTintList =
                    ColorStateList.valueOf(requireActivity().getColor(R.color.finish_up))
                viewBinding.view3.backgroundTintList = ColorStateList.valueOf(requireActivity().getColor(R.color.finish_up))

                viewBinding.pbTimer.progressTintList =  ColorStateList.valueOf(requireActivity().getColor(R.color.finish_up_for_progress))
                viewBinding.progress.backgroundProgressBarColor = requireActivity().getColor(R.color.finish_up_for_progress)
                val window: Window = activity!!.window

                viewBinding.finishButton.setTextColor(ColorStateList.valueOf(requireActivity().getColor(R.color.finish_up)))
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = ContextCompat.getColor(
                    activity!!, R.color.finish_up
                )
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}