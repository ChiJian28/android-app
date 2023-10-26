package com.example.gramativ

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView

class Projects : AppCompatActivity() {

    private lateinit var imgBtnProjectsBackMain: ImageButton
    private lateinit var cardProjectsFrontEnd: CardView
    private lateinit var cardProjectsBackEnd: CardView
    private lateinit var txtProjectsUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_projects)

        imgBtnProjectsBackMain = findViewById(R.id.imgBtnProjectsBackMain)
        cardProjectsFrontEnd = findViewById(R.id.cardProjectsFrontEnd)
        cardProjectsBackEnd = findViewById(R.id.cardProjectsBackEnd)
        txtProjectsUsername = findViewById(R.id.txtProjectsUsername)

        val strUsername = intent.getStringExtra("Username")
        txtProjectsUsername.setText(strUsername)

        cardProjectsFrontEnd.setOnClickListener {
            val intent = Intent(this@Projects, FrontEndProject::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        cardProjectsBackEnd.setOnClickListener {
            val intent = Intent(this@Projects, BackEndProject::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        imgBtnProjectsBackMain.setOnClickListener {
            val intent = Intent(this@Projects, MainActivity::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
    }
}