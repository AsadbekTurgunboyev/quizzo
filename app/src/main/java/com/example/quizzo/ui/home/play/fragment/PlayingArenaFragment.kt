package com.example.quizzo.ui.home.play.fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.quizzo.R
import com.example.quizzo.data.models.questions.QuestionResponse
import com.example.quizzo.databinding.FragmentPlayingArenaBinding
import com.example.quizzo.sound.SoundPlayer
import com.example.quizzo.ui.home.play.usecases.AnimationManager
import com.example.quizzo.ui.home.play.usecases.LivesManager
import com.example.quizzo.ui.home.play.usecases.QuestionManager
import com.example.quizzo.ui.home.play.viewmodel.PlayingArenaViewModel
import com.example.quizzo.utils.PlayerStatus
import com.example.quizzo.utils.ResourceState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PlayingArenaFragment : Fragment() {

    private val playingArenaViewModel: PlayingArenaViewModel by sharedViewModel()

    private lateinit var soundPlayer: SoundPlayer
    private lateinit var viewBinding: FragmentPlayingArenaBinding

    private val livesManager: LivesManager = LivesManager()
    private val animationManager = AnimationManager()
    private val questionManager = QuestionManager(livesManager)



    val animator = ValueAnimator.ofInt(20000, 0).apply {
        duration = 20000
        interpolator = LinearInterpolator()
        addUpdateListener { animation ->


            val progress = animation.animatedValue as Int
            requireActivity().runOnUiThread {
                viewBinding.txtTimeLeft.text = "${(progress / 1000)} sec"
            }
            if ((progress / 1000)<2){
               navigateFinishArena(PlayerStatus.PLAYER_NO_TIME, questionManager.getTotalCorrect())

            }
            viewBinding.pbTimer.progress = progress / 200
        }

    }

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

        viewBinding = FragmentPlayingArenaBinding.inflate(inflater, container, false)

        soundPlayer = SoundPlayer(SoundPlayer.SoundType.BUTTON_SOUND, requireContext())
        animator.start()
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
    private fun updateLivesView() {
        val livesViews = mapOf(
            1 to viewBinding.live1,
            2 to viewBinding.live2,
            3 to viewBinding.live3
        )
        val lostLives = LivesManager.MAX_LIVES - livesManager.getRemainingLives()
        for (i in 1..lostLives) {
            livesViews[i]?.visibility = View.GONE
        }
        if (livesManager.getRemainingLives() == 0) {
            navigateFinishArena(PlayerStatus.PLAYER_IS_DIED, questionManager.getTotalCorrect())
        }
    }

    private fun handleClick(option: Int, view: View) {
        animator.start()
        soundPlayer.playSound()
        animationManager.animateButton(view) {
            questionManager.answerQuestion(option)
            if (questionManager.isQuizFinished()) {
                navigateFinishArena(PlayerStatus.PLAYER_IS_ALIVE, questionManager.getTotalCorrect())
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

                ResourceState.SUCCESS -> {
                    updateQuestions(it.data)
                }
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
        viewBinding.txtCurrrentQuestionCount.text = questionManager.getCurrentQuestionNumber()
        updateLivesView()

        questionManager.startQuestion()
        with(viewBinding) {
            // Update UI here
            txtQuestion.text = response.question_text

            response.options.forEachIndexed { index, option ->
                when(index) {
                    0 -> txtOption1.text = option.option_text
                    1 -> txtOption2.text = option.option_text
                    2 -> txtOption3.text = option.option_text
                    3 -> txtOption4.text = option.option_text
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        animator.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        animator.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        animator.cancel()
    }

    private fun navigateFinishArena(status: Int, totalCorrect: Int){
        animator.cancel()
        val navController = findNavController()
        val builder = NavOptions.Builder()

        val bundle = Bundle()
        bundle.putInt("game_over_status",status)
        bundle.putInt("total_correct",totalCorrect)
        val slideUp = Slide(Gravity.BOTTOM)
        slideUp.duration = 300
        val slideDown = Slide(Gravity.TOP)
        slideDown.duration = 300

        val navOptions = builder
            .setEnterAnim(R.animator.slide_in_bottom)
            .build()

        navController.navigate(R.id.finishArenaFragment, bundle, navOptions)
    }
}