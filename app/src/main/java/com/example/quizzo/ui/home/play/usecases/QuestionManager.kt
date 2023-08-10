package com.example.quizzo.ui.home.play.usecases

import com.example.quizzo.data.models.questions.QuestionResponse
import com.example.quizzo.utils.ConversionUtils

class QuestionManager(private val livesManager: LivesManager) {
    private var questions: List<QuestionResponse> = emptyList()
    private var currentQuestionIndex = 0
    private var totalCorrect = 0
    private var totalIncorrect = 0
    private var startTime: Long = 0
    private val timeTakenList = mutableListOf<Int>()

    fun setQuestions(questions: List<QuestionResponse>) {
        this.questions = questions
    }
    fun startQuestion() {
        startTime = System.currentTimeMillis()
    }

    fun getCurrentQuestionNumber(): String = "$currentQuestionIndex / ${questions.size}"
    fun getTimesList(): List<Int> = timeTakenList
    fun getCurrentQuestion(): QuestionResponse = questions[currentQuestionIndex]

    private fun nextQuestion() {
        if (!isQuizFinished()) {
            currentQuestionIndex++
        }
    }
    fun answerQuestion(option: Int) {
        val endTime = System.currentTimeMillis()
        val timeTaken = endTime - startTime

        if (isCorrect(option)) {
            incrementTotalCorrect(timeTaken)
        }else{
            livesManager.decrementLife()
            incrementTotalInCorrect()
        }
        nextQuestion()
    }

    fun isQuizFinished() = currentQuestionIndex >= questions.size

    private fun isCorrect(option: Int) = getCurrentQuestion().options[option].is_correct

    private fun incrementTotalCorrect(timeTaken: Long) {
        totalCorrect++
        timeTakenList.add(ConversionUtils.convertFromMillToSeconds(timeTaken))
    }
    private fun incrementTotalInCorrect(){
        totalIncorrect++
    }

    fun getTotalInCorrect() = totalIncorrect
    fun getTotalCorrect() = totalCorrect
}
