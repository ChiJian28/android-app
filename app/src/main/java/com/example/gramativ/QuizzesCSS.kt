package com.example.gramativ

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class QuizzesCSS : AppCompatActivity(), View.OnClickListener {

    lateinit var txtCSSUsername: TextView
    lateinit var progressBarCss: ProgressBar
    lateinit var tv_progressCss: TextView
    lateinit var tv_questionCss: TextView
    lateinit var tv_option_oneCss: TextView
    lateinit var tv_option_twoCss: TextView
    lateinit var tv_option_threeCss: TextView
    lateinit var tv_option_fourCss: TextView
    lateinit var btn_submitCss: Button
    lateinit var imgBtnCSSBackQuizzes : ImageButton
    lateinit var myRef: DatabaseReference

    var correctAnsCount = 0
    var hasSubmitted = false // Flag to track if the user has submitted an answer

    var mCurrentPosition: Int = 1 // Default and the first question position（total got 10 questions）
    var mQuestionsList: ArrayList<QuestionQuizzes>? = null
    var mSelectedOptionPosition: Int = 0    //we got four options, using numbers 1 to 4 to represent as each option（0 means that the user did not select any option

    val getStorage : QuizFunction = QuizImplement(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes_css)

        txtCSSUsername = findViewById(R.id.txtCSSUsername)
        progressBarCss = findViewById(R.id.progressBarCss)
        tv_progressCss = findViewById(R.id.tv_progressCss)
        tv_questionCss = findViewById(R.id.tv_questionCss)
        tv_option_oneCss = findViewById(R.id.tv_option_oneCss)
        tv_option_twoCss = findViewById(R.id.tv_option_twoCss)
        tv_option_threeCss = findViewById(R.id.tv_option_threeCss)
        tv_option_fourCss = findViewById(R.id.tv_option_fourCss)
        btn_submitCss = findViewById(R.id.btn_submitCss)
        imgBtnCSSBackQuizzes = findViewById(R.id.imgBtnCSSBackQuizzes)
        myRef = FirebaseDatabase.getInstance().reference

        val strUsername = intent.getStringExtra("Username")
        txtCSSUsername.setText(strUsername)

        imgBtnCSSBackQuizzes.setOnClickListener {
            val intent = Intent(this@QuizzesCSS, Quizzes::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        mQuestionsList = Constants.getCssQuestions()

        getStorage.processQuestions(mQuestionsList, mCurrentPosition, btn_submitCss, progressBarCss, tv_progressCss, tv_questionCss, tv_option_oneCss,tv_option_twoCss,tv_option_threeCss,tv_option_fourCss)

        // set event click listener = pass the ui to the current activity or fragment then using onClick function for processing（搭配onClick function来用）
        tv_option_oneCss.setOnClickListener(this)
        tv_option_twoCss.setOnClickListener(this)
        tv_option_threeCss.setOnClickListener(this)
        tv_option_fourCss.setOnClickListener(this)
        btn_submitCss.setOnClickListener(this)
    }

    //when user click on certain ui, the ui act as a parameters passed to onClick function for processing
    override fun onClick(v: View?) {
        if (v is TextView) {        //double check the user selected element/ui is a TextView
            when (v.id) {   //look into element's id(唯一表示符)
                R.id.tv_option_oneCss -> {
                    selectAnswer(tv_option_oneCss, 1)
                }

                R.id.tv_option_twoCss -> {
                    selectAnswer(tv_option_twoCss, 2)
                }

                R.id.tv_option_threeCss -> {
                    selectAnswer(tv_option_threeCss, 3)
                }

                R.id.tv_option_fourCss -> {
                    selectAnswer(tv_option_fourCss, 4)
                }

                R.id.btn_submitCss -> {

                    // if user submit without select any option
                    if (mSelectedOptionPosition == 0) {

                        mCurrentPosition++

                        when {

                            // ensure we haven't meet the questions limit
                            mCurrentPosition <= mQuestionsList!!.size -> {
                                getStorage.processQuestions(mQuestionsList, mCurrentPosition, btn_submitCss, progressBarCss, tv_progressCss, tv_questionCss, tv_option_oneCss,tv_option_twoCss,tv_option_threeCss,tv_option_fourCss)
                            }
                            else -> {   // if meet the questions limit, then jump to result activity
                                val strUsername = intent.getStringExtra("Username")

                                val intent = Intent(this@QuizzesCSS, QuizzesResult::class.java)
                                intent.putExtra("Username", strUsername)
                                intent.putExtra(Constants.CORRECT_ANSWERS, correctAnsCount)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)

                                if (correctAnsCount == 10){
                                    val streak = true
                                    if (strUsername != null) {
                                        myRef.child(strUsername).child("StreakCSS").setValue(streak.toString())
                                    }
                                }
                                startActivity(intent)
                            }
                        }
                    } else {    //if user submit with select the option
                        val question = mQuestionsList?.get(mCurrentPosition - 1)

                        // This is to check if the answer is wrong
                        if (question!!.answer != mSelectedOptionPosition) {
//                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                            getStorage.answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg,mQuestionsList, mCurrentPosition, btn_submitCss, progressBarCss, tv_progressCss, tv_questionCss, tv_option_oneCss,tv_option_twoCss,tv_option_threeCss,tv_option_fourCss)
                        }
                        else {
                            correctAnsCount++
                        }

                        // This is for correct answer
                        getStorage.answerView(question.answer, R.drawable.correct_option_border_bg,mQuestionsList, mCurrentPosition, btn_submitCss, progressBarCss, tv_progressCss, tv_questionCss, tv_option_oneCss,tv_option_twoCss,tv_option_threeCss,tv_option_fourCss)

                        getStorage.disableClickEvents(tv_option_oneCss,tv_option_twoCss,tv_option_threeCss,tv_option_fourCss)

                        //if we met the questions limit
                        if (mCurrentPosition == mQuestionsList!!.size) {
                            btn_submitCss.text = "FINISH"
                        } else {
                            btn_submitCss.text = "GO TO NEXT QUESTION"
                        }
                        mSelectedOptionPosition = 0     //reset to zero for next time quizzes
                    }
                }
            }
        }
    }


    // show user selected
    fun selectAnswer(selectedOption: TextView, selectedOptionNum: Int) {
        if (!hasSubmitted) { // Allow selecting answers only before submission
            getStorage.defaultOptionsView(mQuestionsList, mCurrentPosition, btn_submitCss, progressBarCss, tv_progressCss, tv_questionCss, tv_option_oneCss,tv_option_twoCss,tv_option_threeCss,tv_option_fourCss)
            selectedOption.setTextColor(Color.parseColor("#363A43")) // Set color for selected answer
            selectedOption.setTypeface(null, Typeface.BOLD) // Set the selected option text to bold

            selectedOption.background = ContextCompat.getDrawable(
                this,
                R.drawable.selected_option_border_bg
            )
            mSelectedOptionPosition = selectedOptionNum
        }
    }
}