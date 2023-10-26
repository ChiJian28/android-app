package com.example.gramativ

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AccountSetup : AppCompatActivity() {

    lateinit var btnAccSetupCreateAcc : Button
    lateinit var btnAccSetupLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_setup)

        btnAccSetupCreateAcc = findViewById(R.id.btnAccSetupCreateAcc)
        btnAccSetupLogin = findViewById(R.id.btnAccSetupLogin)

        btnAccSetupCreateAcc.setOnClickListener {
            val intent = Intent(this, CreateAcc::class.java)
            startActivity(intent)
        }

        btnAccSetupLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}