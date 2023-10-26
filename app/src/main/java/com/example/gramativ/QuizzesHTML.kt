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

class QuizzesHTML : AppCompatActivity(), View.OnClickListener {

    lateinit var txtHTMLUsername: TextView
    lateinit var progressBarHtml: ProgressBar
    lateinit var tv_progressHtml: TextView
    lateinit var tv_questionHtml: TextView
    lateinit var tv_option_oneHtml: TextView
    lateinit var tv_option_twoHtml: TextView
    lateinit var tv_option_threeHtml: TextView
    lateinit var tv_option_fourHtml: TextView
    lateinit var btn_submitHtml: Button
    lateinit var imgBtnHTMLBackQuizzes : ImageButton
    lateinit var myRef: DatabaseReference

    var correctAnsCount = 0
    var hasSubmitted = false // Flag to track if the user has submitted an answer

    var mCurrentPosition: Int = 1 // Default and the first question position（total got 10 questions）
    var mQuestionsList: ArrayList<QuestionQuizzes>? = null
    var mSelectedOptionPosition: Int = 0    //we got four options, using numbers 1 to 4 to represent as each option（0 means that the user did not select any option

    val getStorage : QuizFunction = QuizImplement(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes_html)

        txtHTMLUsername = findViewById(R.id.txtHTMLUsername)
        progressBarHtml = findViewById(R.id.progressBarHtml)
        tv_progressHtml = findViewById(R.id.tv_progressHtml)
        tv_questionHtml = findViewById(R.id.tv_questionHtml)
        tv_option_oneHtml = findViewById(R.id.tv_option_oneHtml)
        tv_option_twoHtml = findViewById(R.id.tv_option_twoHtml)
        tv_option_threeHtml = findViewById(R.id.tv_option_threeHtml)
        tv_option_fourHtml = findViewById(R.id.tv_option_fourHtml)
        btn_submitHtml = findViewById(R.id.btn_submitHtml)
        imgBtnHTMLBackQuizzes = findViewById(R.id.imgBtnHTMLBackQuizzes)
        myRef = FirebaseDatabase.getInstance().reference

        val strUsername = intent.getStringExtra("Username")
        txtHTMLUsername.setText(strUsername)

        imgBtnHTMLBackQuizzes.setOnClickListener {
            val intent = Intent(this@QuizzesHTML, Quizzes::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        mQuestionsList = Constants.getHtmlQuestions()

        getStorage.processQuestions(mQuestionsList, mCurrentPosition, btn_submitHtml, progressBarHtml, tv_progressHtml, tv_questionHtml, tv_option_oneHtml,tv_option_twoHtml,tv_option_threeHtml,tv_option_fourHtml)

        // set event click listener = pass the ui to the current activity or fragment then using onClick function for processing（搭配onClick function来用）
        tv_option_oneHtml.setOnClickListener(this)
        tv_option_twoHtml.setOnClickListener(this)
        tv_option_threeHtml.setOnClickListener(this)
        tv_option_fourHtml.setOnClickListener(this)
        btn_submitHtml.setOnClickListener(this)
    }

    //when user click on certain ui, the ui act as a parameters passed to onClick function for processing
    override fun onClick(v: View?) {
        if (v is TextView) {        //double check the user selected element/ui is a TextView
            when (v.id) {   //look into element's id(唯一表示符)
                R.id.tv_option_oneHtml -> {
                    selectAnswer(tv_option_oneHtml, 1)
                }

                R.id.tv_option_twoHtml -> {
                    selectAnswer(tv_option_twoHtml, 2)
                }

                R.id.tv_option_threeHtml -> {
                    selectAnswer(tv_option_threeHtml, 3)
                }

                R.id.tv_option_fourHtml -> {
                    selectAnswer(tv_option_fourHtml, 4)
                }

                R.id.btn_submitHtml -> {

                    // if user submit without select any option
                    if (mSelectedOptionPosition == 0) {

                        mCurrentPosition++

                        when {

                            // ensure we haven't meet the questions limit
                            mCurrentPosition <= mQuestionsList!!.size -> {
                                getStorage.processQuestions(mQuestionsList, mCurrentPosition, btn_submitHtml, progressBarHtml, tv_progressHtml, tv_questionHtml, tv_option_oneHtml,tv_option_twoHtml,tv_option_threeHtml,tv_option_fourHtml)
                            }
                            else -> {   // if meet the questions limit, then jump to result activity
                                val strUsername = intent.getStringExtra("Username")

                                val intent = Intent(this@QuizzesHTML, QuizzesResult::class.java)
                                intent.putExtra("Username", strUsername)
                                intent.putExtra(Constants.CORRECT_ANSWERS, correctAnsCount)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)

                                if (correctAnsCount == 10){
                                    val streak = true
                                    if (strUsername != null) {
                                        myRef.child(strUsername).child("StreakHTML").setValue(streak.toString())
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
                            getStorage.answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg,mQuestionsList, mCurrentPosition, btn_submitHtml, progressBarHtml, tv_progressHtml, tv_questionHtml, tv_option_oneHtml,tv_option_twoHtml,tv_option_threeHtml,tv_option_fourHtml)
                        }
                        else {
                            correctAnsCount++
                        }

                        // This is for correct answer
                        getStorage.answerView(question.answer, R.drawable.correct_option_border_bg,mQuestionsList, mCurrentPosition, btn_submitHtml, progressBarHtml, tv_progressHtml, tv_questionHtml, tv_option_oneHtml,tv_option_twoHtml,tv_option_threeHtml,tv_option_fourHtml)

                        getStorage.disableClickEvents(tv_option_oneHtml,tv_option_twoHtml,tv_option_threeHtml,tv_option_fourHtml)

                        //if we met the questions limit
                        if (mCurrentPosition == mQuestionsList!!.size) {
                            btn_submitHtml.text = "FINISH"
                        } else {
                            btn_submitHtml.text = "GO TO NEXT QUESTION"
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
            getStorage.defaultOptionsView(mQuestionsList, mCurrentPosition, btn_submitHtml, progressBarHtml, tv_progressHtml, tv_questionHtml, tv_option_oneHtml,tv_option_twoHtml,tv_option_threeHtml,tv_option_fourHtml)
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