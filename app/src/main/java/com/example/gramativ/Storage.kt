package com.example.gramativ

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

interface QuizFunction {
    fun processQuestions(
        questionsList: ArrayList<QuestionQuizzes>?,
        currentPosition: Int,
        btn_submit: Button,
        progressBar: ProgressBar,
        progressTextView: TextView,
        questionTextView: TextView,
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    )

    fun defaultOptionsView(
        questionsList: ArrayList<QuestionQuizzes>?,
        currentPosition: Int,
        btn_submit: Button,
        progressBar: ProgressBar,
        progressTextView: TextView,
        questionTextView: TextView,
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    )


    fun answerView(
        answer: Int,
        drawableView: Int,
        questionsList: ArrayList<QuestionQuizzes>?,
        currentPosition: Int,
        btn_submit: Button,
        progressBar: ProgressBar,
        progressTextView: TextView,
        questionTextView: TextView,
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    )

    fun disableClickEvents(
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    )

    fun enableClickEvents(
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    )
}

class QuizImplement(private val context: Context) : QuizFunction {


    // display questions
    override fun processQuestions(
        questionsList: ArrayList<QuestionQuizzes>?,
        currentPosition: Int,
        btn_submit: Button,
        progressBar: ProgressBar,
        progressTextView: TextView,
        questionTextView: TextView,
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    ) {

        if (questionsList == null) {
            return
        }

        defaultOptionsView(questionsList, currentPosition, btn_submit, progressBar, progressTextView, questionTextView, optionOneTextView, optionTwoTextView, optionThreeTextView, optionFourTextView)

        val question = questionsList.get(currentPosition - 1)

        if (currentPosition == questionsList.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUBMIT"
        }

        progressBar.progress = currentPosition
        progressTextView.text = "$currentPosition/${progressBar.max}"

        questionTextView.text = question.question
        optionOneTextView.text = question.opt1
        optionTwoTextView.text = question.opt2
        optionThreeTextView.text = question.opt3
        optionFourTextView.text = question.opt4

        enableClickEvents(optionOneTextView, optionTwoTextView, optionThreeTextView, optionFourTextView)
    }

    // reset/refresh to default view
    override fun defaultOptionsView(
        questionsList: ArrayList<QuestionQuizzes>?,
        currentPosition: Int,
        btn_submit: Button,
        progressBar: ProgressBar,
        progressTextView: TextView,
        questionTextView: TextView,
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    ) {
        // 创建array，then push ui 进去 以便等下遍历每个元素
        val options = ArrayList<TextView>()
        options.add(0, optionOneTextView)
        options.add(1, optionTwoTextView)
        options.add(2, optionThreeTextView)
        options.add(3, optionFourTextView)
//        val buttonList = listOf(tv_option_one, tv_option_two, tv_option_three, tv_option_four)    //second way to create array

        // 通过iterate array来遍历每个ui
        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                context,
                R.drawable.default_option_border_bg
            )
        }
    }

    // show correct answer and wrong answer
    override fun answerView(
        answer: Int,
        drawableView: Int,
        questionsList: ArrayList<QuestionQuizzes>?,
        currentPosition: Int,
        btn_submit: Button,
        progressBar: ProgressBar,
        progressTextView: TextView,
        questionTextView: TextView,
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    ) {
        when (answer) {

            1 -> {
                optionOneTextView.background = ContextCompat.getDrawable(
                    context,
                    drawableView
                )
            }
            2 -> {
                optionTwoTextView.background = ContextCompat.getDrawable(
                    context,
                    drawableView
                )
            }
            3 -> {
                optionThreeTextView.background = ContextCompat.getDrawable(
                    context,
                    drawableView
                )
            }
            4 -> {
                optionFourTextView.background = ContextCompat.getDrawable(
                    context,
                    drawableView
                )
            }
        }
    }

    // Disable click events for options and submit button
    override fun disableClickEvents(
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    ) {
        optionOneTextView.isClickable = false
        optionTwoTextView.isClickable = false
        optionThreeTextView.isClickable = false
        optionFourTextView.isClickable = false
    }

    // Enable click events for options and submit button
    override fun enableClickEvents(
        optionOneTextView: TextView,
        optionTwoTextView: TextView,
        optionThreeTextView: TextView,
        optionFourTextView: TextView
    ) {
        optionOneTextView.isClickable = true
        optionTwoTextView.isClickable = true
        optionThreeTextView.isClickable = true
        optionFourTextView.isClickable = true
    }


}

