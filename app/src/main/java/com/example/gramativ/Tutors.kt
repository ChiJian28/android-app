package com.example.gramativ

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class Tutors : AppCompatActivity() {

    lateinit var btnSearch : Button
    lateinit var edtUrl : EditText
    lateinit var imgBtnTutorBackMain : ImageButton
    lateinit var btnConnectTc1 : Button
    lateinit var btnConnectTc2 : Button
    lateinit var btnConnectTc3 : Button
    lateinit var btnConnectTc4 : Button
    lateinit var btnConnectTc5 : Button
    lateinit var btnFindOutMore : Button
    lateinit var txtTutorsUsername : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutors)

        btnSearch = findViewById(R.id.btnSearch)
        edtUrl = findViewById(R.id.edtUrl)
        imgBtnTutorBackMain = findViewById(R.id.imgBtnTutorBackMain)
        btnConnectTc1 = findViewById(R.id.btnConnectTc1)
        btnConnectTc2 = findViewById(R.id.btnConnectTc2)
        btnConnectTc3 = findViewById(R.id.btnConnectTc3)
        btnConnectTc4 = findViewById(R.id.btnConnectTc4)
        btnConnectTc5 = findViewById(R.id.btnConnectTc5)
        btnFindOutMore = findViewById(R.id.btnFindOutMore)
        txtTutorsUsername = findViewById(R.id.txtTutorsUsername)

        val strUsername = intent.getStringExtra("Username")
        txtTutorsUsername.setText(strUsername)

        // GoBackMainPage
        imgBtnTutorBackMain.setOnClickListener {
            val intent = Intent(this@Tutors, MainActivity::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        // Search function (Implicit Intent)
        btnSearch.setOnClickListener {
            val url = "https://www." + edtUrl.text.toString() + ".com"

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        // Connect with tutor
        btnConnectTc1.setOnClickListener {
            val url = "https://www.teachme2.com/en-us/online-tutors/isabel-28015"
            findOuterResources(url)
        }
        btnConnectTc2.setOnClickListener {
            val url = "https://www.teachme2.com/en-us/start?tutor_id=27855&medium=online"
            findOuterResources(url)
        }
        btnConnectTc3.setOnClickListener {
            val url = "https://www.teachme2.com/en-us/start?tutor_id=28261&medium=online"
            findOuterResources(url)
        }
        btnConnectTc4.setOnClickListener {
            val url = "https://www.teachme2.com/en-us/start?tutor_id=28590&medium=online"
            findOuterResources(url)
        }
        btnConnectTc5.setOnClickListener {
            val url = "https://www.teachme2.com/en-us/start?tutor_id=28713&medium=online"
            findOuterResources(url)
        }

        // Find Out More
        btnFindOutMore.setOnClickListener {
            val url = "https://www.teachme2.com/en-us/start?medium=online"
            findOuterResources(url)
        }
    }

    // Open url
    fun findOuterResources(url: String) {   //In kotlin, need to specify the type of parameters
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

}