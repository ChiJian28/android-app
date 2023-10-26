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

class QuizzesJs : AppCompatActivity(), View.OnClickListener {

    lateinit var txtJsUsername: TextView
    lateinit var progressBarJs: ProgressBar
    lateinit var tv_progressJs: TextView
    lateinit var tv_questionJs: TextView
    lateinit var tv_option_oneJs: TextView
    lateinit var tv_option_twoJs: TextView
    lateinit var tv_option_threeJs: TextView
    lateinit var tv_option_fourJs: TextView
    lateinit var btn_submitJs: Button
    lateinit var imgBtnJsBackQuizzes : ImageButton
    lateinit var myRef: DatabaseReference

    var correctAnsCount = 0
    var hasSubmitted = false // Flag to track if the user has submitted an answer

    var mCurrentPosition: Int = 1 // Default and the first question position（total got 10 questions）
    var mQuestionsList: ArrayList<QuestionQuizzes>? = null
    var mSelectedOptionPosition: Int = 0    //we got four options, using numbers 1 to 4 to represent as each option（0 means that the user did not select any option

    val getStorage : QuizFunction = QuizImplement(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes_js)

        txtJsUsername = findViewById(R.id.txtJsUsername)
        progressBarJs = findViewById(R.id.progressBarJs)
        tv_progressJs = findViewById(R.id.tv_progressJs)
        tv_questionJs = findViewById(R.id.tv_questionJs)
        tv_option_oneJs = findViewById(R.id.tv_option_oneJs)
        tv_option_twoJs = findViewById(R.id.tv_option_twoJs)
        tv_option_threeJs = findViewById(R.id.tv_option_threeJs)
        tv_option_fourJs = findViewById(R.id.tv_option_fourJs)
        btn_submitJs = findViewById(R.id.btn_submitJs)
        imgBtnJsBackQuizzes = findViewById(R.id.imgBtnJsBackQuizzes)
        myRef = FirebaseDatabase.getInstance().reference

        val strUsername = intent.getStringExtra("Username")
        txtJsUsername.setText(strUsername)

        imgBtnJsBackQuizzes.setOnClickListener {
            val intent = Intent(this@QuizzesJs, Quizzes::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        mQuestionsList = Constants.getJsQuestions()

        getStorage.processQuestions(mQuestionsList, mCurrentPosition, btn_submitJs, progressBarJs, tv_progressJs, tv_questionJs, tv_option_oneJs,tv_option_twoJs,tv_option_threeJs,tv_option_fourJs)

        // set event click listener = pass the ui to the current activity or fragment then using onClick function for processing（搭配onClick function来用）
        tv_option_oneJs.setOnClickListener(this)
        tv_option_twoJs.setOnClickListener(this)
        tv_option_threeJs.setOnClickListener(this)
        tv_option_fourJs.setOnClickListener(this)
        btn_submitJs.setOnClickListener(this)
    }

    //when user click on certain ui, the ui act as a parameters passed to onClick function for processing
    override fun onClick(v: View?) {
        if (v is TextView) {        //double check the user selected element/ui is a TextView
            when (v.id) {   //look into element's id(唯一表示符)
                R.id.tv_option_oneJs -> {
                    selectAnswer(tv_option_oneJs, 1)
                }

                R.id.tv_option_twoJs -> {
                    selectAnswer(tv_option_twoJs, 2)
                }

                R.id.tv_option_threeJs -> {
                    selectAnswer(tv_option_threeJs, 3)
                }

                R.id.tv_option_fourJs -> {
                    selectAnswer(tv_option_fourJs, 4)
                }

                R.id.btn_submitJs -> {

                    // if user submit without select any option
                    if (mSelectedOptionPosition == 0) {

                        mCurrentPosition++

                        when {

                            // ensure we haven't meet the questions limit
                            mCurrentPosition <= mQuestionsList!!.size -> {
                                getStorage.processQuestions(mQuestionsList, mCurrentPosition, btn_submitJs, progressBarJs, tv_progressJs, tv_questionJs, tv_option_oneJs,tv_option_twoJs,tv_option_threeJs,tv_option_fourJs)
                            }
                            else -> {   // if meet the questions limit, then jump to result activity
                                val strUsername = intent.getStringExtra("Username")

                                val intent = Intent(this@QuizzesJs, QuizzesResult::class.java)
                                intent.putExtra("Username", strUsername)
                                intent.putExtra(Constants.CORRECT_ANSWERS, correctAnsCount)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)

                                if (correctAnsCount == 10){
                                    val streak = true
                                    if (strUsername != null) {
                                        myRef.child(strUsername).child("StreakJs").setValue(streak.toString())
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
                            getStorage.answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg,mQuestionsList, mCurrentPosition, btn_submitJs, progressBarJs, tv_progressJs, tv_questionJs, tv_option_oneJs,tv_option_twoJs,tv_option_threeJs,tv_option_fourJs)
                        }
                        else {
                            correctAnsCount++
                        }

                        // This is for correct answer
                        getStorage.answerView(question.answer, R.drawable.correct_option_border_bg,mQuestionsList, mCurrentPosition, btn_submitJs, progressBarJs, tv_progressJs, tv_questionJs, tv_option_oneJs,tv_option_twoJs,tv_option_threeJs,tv_option_fourJs)

                        getStorage.disableClickEvents(tv_option_oneJs,tv_option_twoJs,tv_option_threeJs,tv_option_fourJs)

                        //if we met the questions limit
                        if (mCurrentPosition == mQuestionsList!!.size) {
                            btn_submitJs.text = "FINISH"
                        } else {
                            btn_submitJs.text = "GO TO NEXT QUESTION"
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
            getStorage.defaultOptionsView(mQuestionsList, mCurrentPosition, btn_submitJs, progressBarJs, tv_progressJs, tv_questionJs, tv_option_oneJs,tv_option_twoJs,tv_option_threeJs,tv_option_fourJs)
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