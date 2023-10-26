package com.example.gramativ

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuizzesResult : AppCompatActivity() {

    lateinit var txtResultUsername: TextView
    lateinit var btn_finish : Button
    lateinit var tv_score : TextView
    lateinit var myRef: DatabaseReference
    lateinit var getData: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzes_result)

        txtResultUsername = findViewById(R.id.txtResultUsername)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN    //使apps在全屏模式下运行，即隐藏系统状态栏和导航栏，从而最大化屏幕空间
        btn_finish = findViewById(R.id.btn_finish)
        tv_score = findViewById(R.id.tv_score)
        myRef = FirebaseDatabase.getInstance().reference

        var correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val strUsername = txtResultUsername.text.toString().trim()

                val userNode = snapshot.child(strUsername)

                if (userNode.exists()) {
                    val totalScoreNode = userNode.child("Total Score")
                    val totalExpNode = userNode.child("Total Exp")

                    if (totalScoreNode.exists()) {
                        var totalScore = totalScoreNode.value.toString().toInt()
                        totalScore += correctAnswers
                        myRef.child(strUsername).child("Total Score").setValue(totalScore)
                    }
                    if (totalExpNode.exists()) {
                        val ExpConstant = 50
                        var totalExp = totalExpNode.value.toString().toInt()
                        totalExp += (correctAnswers * ExpConstant)
                        myRef.child(strUsername).child("Total Exp").setValue(totalExp)
                    }

                    // Remove the listener after updating the data
                    myRef.removeEventListener(getData)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }


        val strUsername = intent.getStringExtra("Username")
        txtResultUsername.setText(strUsername)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        tv_score.text = "Your Score is $correctAnswers out of $totalQuestions."


        btn_finish.setOnClickListener {
            myRef.addValueEventListener(getData)

            val intent = Intent(this@QuizzesResult, MainActivity::class.java)
            intent.putExtra("Username", strUsername)

            startActivity(intent)
        }
    }
}