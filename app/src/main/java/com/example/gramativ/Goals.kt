package com.example.gramativ

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Goals : AppCompatActivity() {

    private lateinit var imgBtnGoalsBackMenu: ImageButton
    private lateinit var btnClaimPython: Button
    private lateinit var btnClaimHTML: Button
    private lateinit var btnClaimCSS: Button
    private lateinit var btnClaimJs: Button
    private lateinit var btnGoalsToPythonQuiz: Button
    private lateinit var btnGoalsToHTMLQuiz: Button
    private lateinit var btnGoalsToCSSQuiz: Button
    private lateinit var btnGoalsToJsQuiz: Button
    private lateinit var txtGoalsUsername: TextView
    private lateinit var myRef: DatabaseReference
    private lateinit var getData: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        imgBtnGoalsBackMenu = findViewById(R.id.imgBtnGoalsBackMenu)
        btnClaimPython = findViewById(R.id.btnClaimPython)
        btnClaimHTML = findViewById(R.id.btnClaimHTML)
        btnClaimCSS = findViewById(R.id.btnClaimCSS)
        btnClaimJs = findViewById(R.id.btnClaimJs)
        btnGoalsToPythonQuiz = findViewById(R.id.btnGoalsToPythonQuiz)
        btnGoalsToHTMLQuiz = findViewById(R.id.btnGoalsToHTMLQuiz)
        btnGoalsToCSSQuiz = findViewById(R.id.btnGoalsToCSSQuiz)
        btnGoalsToJsQuiz = findViewById(R.id.btnGoalsToJsQuiz)
        txtGoalsUsername = findViewById(R.id.txtGoalsUsername)
        myRef = FirebaseDatabase.getInstance().reference
        getData = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val strUsername = txtGoalsUsername.text.toString().trim()
                val userNode = snapshot.child(strUsername)

                if (userNode.exists()){
                    val streakPythonNode = userNode.child("StreakPython")
                    val streakHTMLNode = userNode.child("StreakHTML")
                    val streakCSSNode = userNode.child("StreakCSS")
                    val streakJsNode = userNode.child("StreakJs")

                    val totalExpNode = userNode.child("Total Exp")

                    if (streakPythonNode.exists()) {
                        var streakPython = streakPythonNode.value.toString()
                        if (streakPython == "true") {
                            // If there is streak on Python Quiz
                            btnClaimPython.isVisible = true
                            btnGoalsToPythonQuiz.isVisible = false

                            btnClaimPython.setOnClickListener { // Claim Reward
                                btnClaimPython.isVisible = false
                                btnGoalsToPythonQuiz.isVisible = true

                                var totalExp = totalExpNode.value.toString().toInt()
                                totalExp += 100 // Add 100 Exp

                                streakPython = "false"
                                myRef.child(strUsername).child("StreakPython").setValue(streakPython)
                                myRef.child(strUsername).child("Total Exp").setValue(totalExp.toString())
                                Toast.makeText(this@Goals, "Congratulations! You've claimed 100 EXP!", Toast.LENGTH_LONG).show()
                            }
                        } else if (streakPython == "false") {
                            // If there is NO streak on Python Quiz
                            btnClaimPython.isVisible = false
                            btnGoalsToPythonQuiz.isVisible = true
                        }
                    } else{
                        myRef.child(strUsername).child("StreakPython").setValue("false")
                    }
                    if (streakHTMLNode.exists()){
                        // If there is streak on HTML Quiz
                        var streakHTML = streakHTMLNode.value.toString()
                        if (streakHTML == "true"){
                            btnClaimHTML.isVisible = true
                            btnGoalsToHTMLQuiz.isVisible = false

                            btnClaimHTML.setOnClickListener { // Claim Reward
                                btnClaimHTML.isVisible = false
                                btnGoalsToHTMLQuiz.isVisible = true

                                var totalExp = totalExpNode.value.toString().toInt()
                                totalExp += 100 // Add 100 Exp

                                streakHTML = "false"
                                myRef.child(strUsername).child("StreakHTML").setValue(streakHTML)
                                myRef.child(strUsername).child("Total Exp").setValue(totalExp.toString())
                                Toast.makeText(this@Goals, "Congratulations! You've claimed 100 EXP!", Toast.LENGTH_LONG).show()
                            }
                        }else if (streakHTML == "false"){
                            // If there is NO streak on HTML Quiz
                            btnClaimHTML.isVisible = false
                            btnGoalsToHTMLQuiz.isVisible = true
                        }
                    } else{
                        myRef.child(strUsername).child("StreakHTML").setValue("false")
                    }
                    if (streakCSSNode.exists()){
                        var streakCSS = streakCSSNode.value.toString()
                        if (streakCSS == "true"){
                            // If there is streak on CSS Quiz
                            btnClaimCSS.isVisible = true
                            btnGoalsToCSSQuiz.isVisible = false

                            btnClaimCSS.setOnClickListener { // Claim Reward
                                btnClaimCSS.isVisible = false
                                btnGoalsToCSSQuiz.isVisible = true

                                var totalExp = totalExpNode.value.toString().toInt()
                                totalExp += 100 // Add 100 Exp

                                streakCSS = "false"
                                myRef.child(strUsername).child("StreakCSS").setValue(streakCSS)
                                myRef.child(strUsername).child("Total Exp").setValue(totalExp.toString())
                                Toast.makeText(this@Goals, "Congratulations! You've claimed 100 EXP!", Toast.LENGTH_LONG).show()
                            }
                        }else if (streakCSS == "false"){
                            // If there is NO streak on CSS Quiz
                            btnClaimCSS.isVisible = false
                            btnGoalsToCSSQuiz.isVisible = true
                        }
                    } else{
                        myRef.child(strUsername).child("StreakCSS").setValue("false")
                    }
                    if (streakJsNode.exists()){
                        var streakJs = streakJsNode.value.toString()
                        if (streakJs == "true"){
                            // If there is streak on Js Quiz
                            btnClaimJs.isVisible = true
                            btnGoalsToJsQuiz.isVisible = false

                            btnClaimJs.setOnClickListener { // Claim Reward
                                btnClaimJs.isVisible = false
                                btnGoalsToJsQuiz.isVisible = true

                                var totalExp = totalExpNode.value.toString().toInt()
                                totalExp += 100 // Add 100 Exp

                                streakJs = "false"
                                myRef.child(strUsername).child("StreakJs").setValue(streakJs)
                                myRef.child(strUsername).child("Total Exp").setValue(totalExp.toString())
                                Toast.makeText(this@Goals, "Congratulations! You've claimed 100 EXP!", Toast.LENGTH_LONG).show()
                            }
                        }else if (streakJs == "false"){
                            // If there is NO streak on Js Quiz
                            btnClaimJs.isVisible = false
                            btnGoalsToJsQuiz.isVisible = true
                        }
                    } else{
                        myRef.child(strUsername).child("StreakJs").setValue("false")
                    }
                }else {
                    // If there are no keys for streak on every quiz
                    myRef.child(strUsername).child("StreakPython").setValue("false")
                    myRef.child(strUsername).child("StreakHTML").setValue("false")
                    myRef.child(strUsername).child("StreakCSS").setValue("false")
                    myRef.child(strUsername).child("StreakJs").setValue("false")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }

        btnClaimPython.isVisible = false
        btnClaimHTML.isVisible = false
        btnClaimCSS.isVisible = false
        btnClaimJs.isVisible = false

        val strUsername = intent.getStringExtra("Username")
        txtGoalsUsername.setText(strUsername)

        imgBtnGoalsBackMenu.setOnClickListener {
            val intent = Intent(this@Goals, MainActivity::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        myRef.addValueEventListener(getData)

        // Take Quiz
        btnGoalsToPythonQuiz.setOnClickListener {
            val intent = Intent(this@Goals,QuizzesPython::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
        btnGoalsToHTMLQuiz.setOnClickListener {
            val intent = Intent(this@Goals,QuizzesHTML::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
        btnGoalsToCSSQuiz.setOnClickListener {
            val intent = Intent(this@Goals,QuizzesCSS::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
        btnGoalsToJsQuiz.setOnClickListener {
            val intent = Intent(this@Goals,QuizzesJs::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
    }
}

