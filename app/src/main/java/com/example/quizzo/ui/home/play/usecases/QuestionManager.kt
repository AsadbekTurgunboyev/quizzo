package com.example.quizzo.ui.home.play.usecases

import com.example.quizzo.data.models.questions.QuestionResponse
import com.example.quizzo.utils.ConversionUtils

class QuestionManager {
    private var questions: List<QuestionResponse> = emptyList()
    private var currentQuestionIndex = 0
    private var totalCorrect = 0
    private var startTime: Long = 0
    private val timeTakenList = mutableListOf<Int>()

    fun setQuestions(questions: List<QuestionResponse>) {
        this.questions = questions
    }
    fun startQuestion() {
        startTime = System.currentTimeMillis()
    }

    fun getTimesList(): List<Int> = timeTakenList
    fun getCurrentQuestion(): QuestionResponse = questions[currentQuestionIndex]

    fun nextQuestion() {
        val endTime = System.currentTimeMillis()
        val timeTaken = endTime - startTime


        if (!isQuizFinished()) {
            currentQuestionIndex++
        }
    }
    fun answerQuestion(option: Int) {
        val endTime = System.currentTimeMillis()
        val timeTaken = endTime - startTime

        if (isCorrect(option)) {
            totalCorrect++
            timeTakenList.add(ConversionUtils.convertFromMillToSeconds(timeTaken))
        }

        if (!isQuizFinished()) {
            currentQuestionIndex++
        }
    }

    fun isQuizFinished() = currentQuestionIndex >= questions.size

    private fun isCorrect(option: Int) = getCurrentQuestion().options[option].is_correct

    fun incrementTotalCorrect() {
        totalCorrect++
    }

    fun getTotalCorrect() = totalCorrect
}
