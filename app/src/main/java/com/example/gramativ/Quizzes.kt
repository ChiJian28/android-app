package com.example.gramativ

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView

class Quizzes : AppCompatActivity() {

    private lateinit var imgBtnQuizzesBackMain: ImageButton
    private lateinit var cardQuizzesPython: CardView
    private lateinit var cardQuizzesHTML: CardView
    private lateinit var cardQuizzesJS: CardView
    private lateinit var cardQuizzesCSS: CardView
    private lateinit var txtQuizzesUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes)

        imgBtnQuizzesBackMain = findViewById(R.id.imgBtnQuizzesBackMain)
        cardQuizzesPython = findViewById(R.id.cardQuizzesPython)
        cardQuizzesHTML = findViewById(R.id.cardQuizzesHTML)
        cardQuizzesJS = findViewById(R.id.cardQuizzesJS)
        cardQuizzesCSS = findViewById(R.id.cardQuizzesCSS)
        txtQuizzesUsername = findViewById(R.id.txtQuizzesUsername)

        val strUsername = intent.getStringExtra("Username")
        txtQuizzesUsername.setText(strUsername)

        cardQuizzesPython.setOnClickListener {
            val intent = Intent(this@Quizzes, QuizzesPython::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        cardQuizzesHTML.setOnClickListener  {
            val intent = Intent(this@Quizzes, QuizzesHTML::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        cardQuizzesJS.setOnClickListener  {
            val intent = Intent(this@Quizzes, QuizzesJs::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        cardQuizzesCSS.setOnClickListener  {
            val intent = Intent(this@Quizzes, QuizzesCSS::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        imgBtnQuizzesBackMain.setOnClickListener{
            val intent = Intent(this@Quizzes, MainActivity::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
    }
}