package com.example.quizzo.ui.home.play.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizzo.R
import com.example.quizzo.data.models.questions.QuestionResponse
import com.example.quizzo.databinding.FragmentPlayingArenaBinding
import com.example.quizzo.sound.SoundPlayer
import com.example.quizzo.ui.home.play.viewmodel.PlayingArenaViewModel
import com.example.quizzo.ui.home.play.usecases.AnimationManager
import com.example.quizzo.ui.home.play.usecases.QuestionManager
import com.example.quizzo.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PlayingArenaFragment : Fragment() {

    private val playingArenaViewModel: PlayingArenaViewModel by sharedViewModel()

    private lateinit var soundPlayer: SoundPlayer
    private lateinit var viewBinding: FragmentPlayingArenaBinding
    private val questionManager = QuestionManager()
    private val animationManager = AnimationManager()

    val countDownTimer: CountDownTimer = object : CountDownTimer(20000, 1000) {
        override fun onTick(p0: Long) {
            val secondsRemaining = p0 / 1000
            viewBinding.pbTimer.max = 20
            val currentProgress = viewBinding.pbTimer.progress
            val progressBarAnimator = ObjectAnimator.ofInt(
                viewBinding.pbTimer,
                "progress",
                currentProgress,
                secondsRemaining.toInt()
            )
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
        viewBinding = FragmentPlayingArenaBinding.inflate(layoutInflater, container, false)
        soundPlayer = SoundPlayer(SoundPlayer.SoundType.BUTTON_SOUND, requireContext())
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupClickListeners()

    }

    private fun setupClickListeners() {
        with(viewBinding) {
            btnOption1.setOnClickListener { handleClick(0, it) }
            btnOption2.setOnClickListener { handleClick(1, it) }
            btnOption3.setOnClickListener { handleClick(2, it) }
            btnOption4.setOnClickListener { handleClick(3, it) }
        }
    }

    private fun handleClick(option: Int, view: View) {
        soundPlayer.playSound()
        animationManager.animateButton(view) {
            questionManager.answerQuestion(option)
            if (questionManager.isQuizFinished()) {
                val navController = findNavController()
                val builder = NavOptions.Builder()

                val slideUp = Slide(Gravity.BOTTOM)
                slideUp.duration = 300
                val slideDown = Slide(Gravity.TOP)
                slideDown.duration = 300

                val navOptions = builder
                    .setEnterAnim(R.animator.slide_in_bottom)
                    .build()

                navController.navigate(R.id.finishArenaFragment, null, navOptions)

                val times = questionManager.getTimesList()
                // Display the times
            } else {
                updateQuestion(questionManager.getCurrentQuestion())
            }
        }
    }


    private fun setupObservers() {
        playingArenaViewModel.questions.observe(viewLifecycleOwner) {
            when (it.state) {
                ResourceState.SUCCESS -> updateQuestions(it.data)
                else -> {}  // Handle error and loading state here
            }
        }
    }

    private fun updateQuestions(questions: List<QuestionResponse>?) {
        questions?.let {
            questionManager.setQuestions(it)
            updateQuestion(questionManager.getCurrentQuestion())
        }
    }

    private fun updateQuestion(response: QuestionResponse) {
        questionManager.startQuestion()
        with(viewBinding) {
            // Update UI here
            txtQuestion.text = response.question_text
            txtOption1.text = response.options[0].option_text
            txtOption2.text = response.options[1].option_text
            txtOption3.text = response.options[2].option_text
            txtOption4.text = response.options[3].option_text
        }
    }


}