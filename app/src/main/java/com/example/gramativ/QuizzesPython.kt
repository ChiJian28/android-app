package com.example.gramativ

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class QuizzesPython : AppCompatActivity(), View.OnClickListener {

    private lateinit var txtPythonUsername: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var tv_progress: TextView
    private lateinit var tv_question: TextView
    private lateinit var tv_option_one: TextView
    private lateinit var tv_option_two: TextView
    private lateinit var tv_option_three: TextView
    private lateinit var tv_option_four: TextView
    private lateinit var btn_submit: Button
    private lateinit var imgBtnPythonBackQuizzes : ImageButton
    private lateinit var myRef: DatabaseReference

    private var correctAnsCount = 0
    private var hasSubmitted = false // Flag to track if the user has submitted an answer

    private var mCurrentPosition: Int = 1 // Default and the first question position（total got 10 questions）
    private var mQuestionsList: ArrayList<QuestionQuizzes>? = null
    private var mSelectedOptionPosition: Int = 0    //we got four options, using numbers 1 to 4 to represent as each option（0 means that the user did not select any option

    val getStorage : QuizFunction = QuizImplement(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes_python)

        txtPythonUsername = findViewById(R.id.txtPythonUsername)
        progressBar = findViewById(R.id.progressBar)
        tv_progress = findViewById(R.id.tv_progress)
        tv_question = findViewById(R.id.tv_question)
        tv_option_one = findViewById(R.id.tv_option_one)
        tv_option_two = findViewById(R.id.tv_option_two)
        tv_option_three = findViewById(R.id.tv_option_three)
        tv_option_four = findViewById(R.id.tv_option_four)
        btn_submit = findViewById(R.id.btn_submit)
        imgBtnPythonBackQuizzes = findViewById(R.id.imgBtnPythonBackQuizzes)
        myRef = FirebaseDatabase.getInstance().reference

        val strUsername = intent.getStringExtra("Username")
        txtPythonUsername.setText(strUsername)

        imgBtnPythonBackQuizzes.setOnClickListener {
            val intent = Intent(this@QuizzesPython, Quizzes::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        mQuestionsList = Constants.getPythonQuestions()

        getStorage.processQuestions(mQuestionsList, mCurrentPosition, btn_submit, progressBar, tv_progress, tv_question, tv_option_one,tv_option_two,tv_option_three,tv_option_four)

        // set event click listener = pass the ui to the current activity or fragment then using onClick function for processing（搭配onClick function来用）
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    //when user click on certain ui, the ui act as a parameters passed to onClick function for processing
    override fun onClick(v: View?) {
        if (v is TextView) {        //double check the user selected element/ui is a TextView
            when (v.id) {   //look into element's id(唯一表示符)
                R.id.tv_option_one -> {
                    selectAnswer(tv_option_one, 1)
                }

                R.id.tv_option_two -> {
                    selectAnswer(tv_option_two, 2)
                }

                R.id.tv_option_three -> {
                    selectAnswer(tv_option_three, 3)
                }

                R.id.tv_option_four -> {
                    selectAnswer(tv_option_four, 4)
                }

                R.id.btn_submit -> {

                    // if user submit without select any option
                    if (mSelectedOptionPosition == 0) {

                        mCurrentPosition++

                        when {

                            // ensure we haven't meet the questions limit
                            mCurrentPosition <= mQuestionsList!!.size -> {
                                getStorage.processQuestions(mQuestionsList, mCurrentPosition, btn_submit, progressBar, tv_progress, tv_question, tv_option_one,tv_option_two,tv_option_three,tv_option_four)
                            }
                            else -> {   // if meet the questions limit, then jump to result activity
                                val strUsername = intent.getStringExtra("Username")

                                val intent = Intent(this@QuizzesPython, QuizzesResult::class.java)
                                intent.putExtra("Username", strUsername)
                                intent.putExtra(Constants.CORRECT_ANSWERS, correctAnsCount)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)

                                if (correctAnsCount == 10){
                                    val streak = true
                                    if (strUsername != null) {
                                        myRef.child(strUsername).child("StreakPython").setValue(streak.toString())
                                    }
                                }
                                startActivity(intent)
                            }
                        }
                    } else {    //if user submit with select the option
                        val question = mQuestionsList?.get(mCurrentPosition - 1)

                        // This is to check if the answer is wrong
                        if (question!!.answer != mSelectedOptionPosition) {
                            getStorage.answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg,mQuestionsList, mCurrentPosition, btn_submit, progressBar, tv_progress, tv_question, tv_option_one,tv_option_two,tv_option_three,tv_option_four)
                        }
                        else {
                            correctAnsCount++
                        }

                        // This is for correct answer
                        getStorage.answerView(question.answer, R.drawable.correct_option_border_bg,mQuestionsList, mCurrentPosition, btn_submit, progressBar, tv_progress, tv_question, tv_option_one,tv_option_two,tv_option_three,tv_option_four)

                        getStorage.disableClickEvents(tv_option_one,tv_option_two,tv_option_three,tv_option_four)

                        //if we met the questions limit
                        if (mCurrentPosition == mQuestionsList!!.size) {
                            btn_submit.text = "FINISH"
                        } else {
                            btn_submit.text = "GO TO NEXT QUESTION"
                        }
                        mSelectedOptionPosition = 0     //reset to zero for next time quizzes
                    }
                }
            }
        }
    }

    // show user selected
    private fun selectAnswer(selectedOption: TextView, selectedOptionNum: Int) {
        if (!hasSubmitted) { // Allow selecting answers only before submission
            getStorage.defaultOptionsView(mQuestionsList, mCurrentPosition, btn_submit, progressBar, tv_progress, tv_question, tv_option_one,tv_option_two,tv_option_three,tv_option_four)
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



