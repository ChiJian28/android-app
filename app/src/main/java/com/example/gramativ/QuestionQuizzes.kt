package com.example.gramativ

// store some information
data class QuestionQuizzes(
    val id: Int,
    val question: String,
    val opt1: String,
    val opt2: String,
    val opt3: String,
    val opt4: String,
    val answer: Int
)