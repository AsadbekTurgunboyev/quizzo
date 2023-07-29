package com.example.quizzo.ui.home.play

import android.animation.*
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import com.example.quizzo.R
import com.example.quizzo.data.models.questions.QuestionResponse
import com.example.quizzo.databinding.FragmentPlayingArenaBinding
import com.example.quizzo.sound.SoundPlayer
import com.example.quizzo.utils.Resource
import com.example.quizzo.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PlayingArenaFragment : Fragment() {

    private val playingArenaViewModel: PlayingArenaViewModel by sharedViewModel()

    lateinit var soundPlayer: SoundPlayer
    private lateinit var viewBinding: FragmentPlayingArenaBinding
    var questionList : List<QuestionResponse> = emptyList()
    var questionPosition = 0
    var totalCorrect = 0

    val countDownTimer: CountDownTimer = object : CountDownTimer(20000,1000) {
        override fun onTick(p0: Long) {
            val secondsRemaining = p0 / 1000
            viewBinding.pbTimer.max = 20
            val currentProgress = viewBinding.pbTimer.progress
            val progressBarAnimator = ObjectAnimator.ofInt(viewBinding.pbTimer, "progress", currentProgress, secondsRemaining.toInt())
            progressBarAnimator.duration = 1000  // Animate for 1 second
            progressBarAnimator.interpolator = DecelerateInterpolator()
            progressBarAnimator.start()


        }

        override fun onFinish() {

        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewBinding = FragmentPlayingArenaBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        soundPlayer = SoundPlayer(SoundPlayer.SoundType.BUTTON_SOUND,requireContext())
        playingArenaViewModel.questions.observe(viewLifecycleOwner){
            getQuestions(it)
        }

        with(viewBinding){
            btnOption1.setOnClickListener{clickOptions(0,it)}
            btnOption2.setOnClickListener{clickOptions(1,it)}
            btnOption3.setOnClickListener{clickOptions(2,it)}
            btnOption4.setOnClickListener{clickOptions(3,it)}
        }

//        setQuestion(questionList[questionPosition])

    }

    private fun animateButton(view: View, onEnd: () -> Unit) {
        val bounce = AnimatorSet()

        val animX = ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 0.95f, 1.0f)
        animX.duration = 500

        val animY = ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 0.95f, 1.0f)
        animY.duration = 500

        val color = ValueAnimator.ofArgb(Color.parseColor("#FFFFFF"), requireActivity().getColor(R.color.primary))
        color.duration = 300
        color.addUpdateListener { animator ->
            view.backgroundTintList = ColorStateList.valueOf(animator.animatedValue as Int)
        }

        bounce.playTogether(animX, animY, color)
        bounce.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                onEnd()

            }

        })
        bounce.start()
    }

    private fun getQuestions(resource: Resource<List<QuestionResponse>>?) {
        resource?.let {
            when(it.state){
                ResourceState.SUCCESS->{
                    if (it.data !=null){
                        questionList = it.data
                        setQuestion(questionList[questionPosition])
                    }
                }
                ResourceState.ERROR ->{}
                ResourceState.LOADING ->{}
            }
        }
    }


    private fun clickOptions(number: Int, view: View){
        soundPlayer.playSound()
        animateButton(view) {
            questionPosition += 1
            if (questionPosition >= questionList.size){
                Toast.makeText(requireContext(), "$totalCorrect", Toast.LENGTH_SHORT).show()
            }else{
                setQuestion(questionList[questionPosition])
            }
        }
        view.backgroundTintList = ColorStateList.valueOf(requireActivity().getColor(R.color.primary))
        if (questionList[questionPosition].options[number].is_correct){
            totalCorrect ++
        }
    }

   @SuppressLint("SetTextI18n")
   private fun setQuestion(response: QuestionResponse){
       countDownTimer.start()
       with(viewBinding){
           txtCurrrentQuestionCount.text = "Savol ${questionPosition+1} /${questionList.size}"
           btnOption1.backgroundTintList = ColorStateList.valueOf(requireActivity().getColor(R.color.white))
           btnOption2.backgroundTintList = ColorStateList.valueOf(requireActivity().getColor(R.color.white))
           btnOption3.backgroundTintList = ColorStateList.valueOf(requireActivity().getColor(R.color.white))
           btnOption4.backgroundTintList = ColorStateList.valueOf(requireActivity().getColor(R.color.white))

            txtQuestion.text = response.question_text
            txtOption1.text = response.options[0].option_text
            txtOption2.text = response.options[1].option_text
            txtOption3.text = response.options[2].option_text
            txtOption4.text = response.options[3].option_text
        }
    }

}