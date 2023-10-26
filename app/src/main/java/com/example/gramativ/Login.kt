package com.example.gramativ

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {

    lateinit var txtLoginUsername : TextView
    lateinit var edtLoginName: EditText
    lateinit var edtLoginPassword: EditText
    lateinit var btnSubmitLogin: Button
    lateinit var myRef: DatabaseReference
    lateinit var getData: ValueEventListener
    lateinit var txtLoginTitle: TextView
    lateinit var txtLoginCreateAcc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txtLoginUsername = findViewById(R.id.txtLoginUsername)
        edtLoginName = findViewById(R.id.edtLoginName)
        edtLoginPassword = findViewById(R.id.edtLoginPassword)
        btnSubmitLogin = findViewById(R.id.btnSubmitLogin)
        txtLoginTitle = findViewById(R.id.txtLoginTitle)
        txtLoginCreateAcc = findViewById(R.id.txtLoginCreateAcc)

        myRef = FirebaseDatabase.getInstance().reference
        getData = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val strUsername = edtLoginName.text.toString().trim()
                val strPassword = edtLoginPassword.text.toString().trim()

                for (level1 in snapshot.children){ // Loop checking on username (level1)
                    if (strUsername == level1.key){ // If Username Exists
                        for (level2 in level1.children){ // Loop checking on password (level2)
                            if (level2.key == "Password" && level2.value == strPassword){
                                // If Password Match with The Existing Username
                                Toast.makeText(this@Login, "Login Success", Toast.LENGTH_LONG).show()

                                val intent = Intent(this@Login, MainActivity::class.java)
                                intent.putExtra("Username", strUsername)
                                startActivity(intent)

                                edtLoginName.setText("")
                                edtLoginPassword.setText("")

                                break // If correct, escape from the upcoming/next loop
                            }else{
                                // If Password Does not Match
                                edtLoginName.setText("")
                                edtLoginPassword.setText("")
                                Toast.makeText(this@Login, "Wrong Password. Please Try Again.", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }

                if (edtLoginName.text.toString().trim() != "" && edtLoginPassword.text.toString().trim() != ""){ // If Username not found & Not left blank
                    edtLoginName.setText("")
                    edtLoginPassword.setText("")
                    Toast.makeText(this@Login, "Username Not Found. Please Try Again.", Toast.LENGTH_LONG).show()
                }

            }

            override fun onCancelled(error: DatabaseError) {
            }

        }

        val username = intent.getStringExtra("Username")
        if (username != null) {
            txtLoginUsername.setText("Welcome $username")
        }

        txtLoginCreateAcc.setOnClickListener {
            startActivity(Intent(this@Login, CreateAcc::class.java))
        }

        btnSubmitLogin.setOnClickListener {
            if (edtLoginName.text.toString().trim() == "" || edtLoginPassword.text.toString().trim() == ""){ // To check if the field is left blank
                Toast.makeText( this@Login, "Please Enter Username & Password.", Toast.LENGTH_LONG).show()
                edtLoginName.setText("")
                edtLoginPassword.setText("")
            }else{
                myRef.addValueEventListener(getData) // Check Username & Password if they match with existing account
            }
        }
    }
}