package com.example.gramativ

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var txtMainScore: TextView
    private lateinit var txtMainExp: TextView
    private lateinit var cardMainQuizzes: CardView
    private lateinit var cardMainProjects: CardView
    private lateinit var cardMainTutors: CardView
    private lateinit var cardMainGoals: CardView
    private lateinit var imgGoalsClaim: ImageView
    private lateinit var txtMainUsername:TextView
    private lateinit var imgBtnLogOut: ImageButton
    private lateinit var myRef: DatabaseReference
    private lateinit var getData: ValueEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtMainScore = findViewById(R.id.txtMainScore)
        txtMainExp = findViewById(R.id.txtMainExp)
        cardMainQuizzes = findViewById(R.id.cardMainQuizzes)
        cardMainProjects = findViewById(R.id.cardMainProjects)
        cardMainTutors = findViewById(R.id.cardMainTutors)
        cardMainGoals = findViewById(R.id.cardMainGoals)
        imgGoalsClaim = findViewById(R.id.imgGoalsClaim)
        imgBtnLogOut = findViewById(R.id.imgBtnLogOut)
        txtMainUsername = findViewById(R.id.txtMainUsername)
        myRef = FirebaseDatabase.getInstance().reference

        getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val strUsername = txtMainUsername.text.toString().trim()

                // In Database, node is a specific location or branch
                // .exists() is to check if the branch exists or not

                val userNode = snapshot.child(strUsername)

                if (userNode.exists()) { // if userNode has branch
                    val totalScoreNode = userNode.child("Total Score") // assign branch named "Total Score" to totalScoreNode
                    val totalExpNode = userNode.child("Total Exp")
                    val streakPythonNode = userNode.child("StreakPython")
                    val streakHTMLNode = userNode.child("StreakHTML")
                    val streakCSSNode = userNode.child("StreakCSS")
                    val streakJsNode = userNode.child("StreakJs")

                    if (totalScoreNode.exists()) { // if totalScoreNode has branch
                        val totalScore = totalScoreNode.value.toString()
                        txtMainScore.text = totalScore
                    } else {
                        // If "Total Score" exists but has no value, set it to 0
                        myRef.child(strUsername).child("Total Score").setValue("0")
                    }
                    if (totalExpNode.exists()) { // if totalEXPNode has branch
                        val totalExp = totalExpNode.value.toString()
                        txtMainExp.text = totalExp
                    } else {
                        // If "Total EXP" exists but has no value, set it to 0
                        myRef.child(strUsername).child("Total Exp").setValue("0")
                    }
                    if (streakPythonNode.exists() || streakHTMLNode.exists() || streakCSSNode.exists() || streakJsNode.exists()){
                        // If there is any streak, change background color with popup gift icon
                        if (streakPythonNode.value == "true" || streakHTMLNode.value == "true" || streakCSSNode.value == "true" || streakJsNode.value == "true"){
                            cardMainGoals.setCardBackgroundColor(Color.parseColor("#46FE57A4"))
                            imgGoalsClaim.isVisible = true
                        }
                    }
                } else {
                    // If user node doesn't exist, create it with "Total Score" & "Total Exp" set to 0 (default for new user)
                    myRef.child(strUsername).child("Total Score").setValue("0")
                    myRef.child(strUsername).child("Total Exp").setValue("0")
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }

        val strUsername = intent.getStringExtra("Username")
        txtMainUsername.setText(strUsername)

        myRef.addValueEventListener(getData) // Get Score, Exp and Streak

        cardMainQuizzes.setOnClickListener{
            val intent = Intent(this@MainActivity, Quizzes::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
        cardMainProjects.setOnClickListener{
            val intent = Intent(this@MainActivity, Projects::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
        cardMainTutors.setOnClickListener{
            val intent = Intent(this@MainActivity, Tutors::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
        cardMainGoals.setOnClickListener{
            val intent = Intent(this@MainActivity, Goals::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        imgBtnLogOut.setOnClickListener {
            Toast.makeText(this@MainActivity, "You have logged out.", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@MainActivity, AccountSetup::class.java))
        }
    }
}