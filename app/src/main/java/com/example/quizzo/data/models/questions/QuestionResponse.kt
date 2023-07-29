package com.example.quizzo.data.models.questions

data class QuestionResponse(
    val question_text: String,
    val category: Int,
    val options: List<QuestionOptions>
)


data class QuestionOptions(
    val question: Int,
    val option_text: String,
    val is_correct: Boolean
)