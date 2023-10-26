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

class CreateAcc : AppCompatActivity() {

    lateinit var edtCreateAccName : EditText
    lateinit var edtCreateAccPassword: EditText
    lateinit var edtCreateAccConfirmPassword: EditText
    lateinit var btnSubmitCreateAcc : Button
    lateinit var txtCreateAccLogin: TextView
    lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_acc)
        edtCreateAccName = findViewById(R.id.edtCreateAccName)
        edtCreateAccPassword = findViewById(R.id.edtCreateAccPassword)
        edtCreateAccConfirmPassword = findViewById(R.id.edtCreateAccConfirmPassword)
        btnSubmitCreateAcc = findViewById(R.id.btnSubmitCreateAcc)
        txtCreateAccLogin = findViewById(R.id.txtCreateAccLogin)
        myRef = FirebaseDatabase.getInstance().reference

        txtCreateAccLogin.setOnClickListener{
            startActivity(Intent(this@CreateAcc, Login::class.java))
        }

        btnSubmitCreateAcc.setOnClickListener {
            val strUsername = edtCreateAccName.text.toString().trim()
            val strPassword = edtCreateAccPassword.text.toString().trim()
            val strConfirmPassword = edtCreateAccConfirmPassword.text.toString().trim()

            if (strUsername.isEmpty() || strPassword.isEmpty() || strConfirmPassword.isEmpty()) { // If user didn't enter anything for each field
                // Handle empty fields
                Toast.makeText(this, "Please Enter Username & Password.", Toast.LENGTH_LONG).show()
                edtCreateAccName.setText("")
                edtCreateAccPassword.setText("")
                edtCreateAccConfirmPassword.setText("")
            } else if (strPassword == strConfirmPassword) {
                // Passwords match, check if username exists in the database
                myRef.child(strUsername).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            // Username already exists
                            Toast.makeText(this@CreateAcc, "This Username is already taken. Please try another one.", Toast.LENGTH_LONG).show()
                        } else {
                            // Username is available, proceed to create an account
                            Toast.makeText(this@CreateAcc, "You've successfully created an account!", Toast.LENGTH_LONG).show()
                            myRef.child(strUsername).child("Password").setValue(strPassword)

                            // Clear input fields before moving to Login Page
                            edtCreateAccName.setText("")
                            edtCreateAccPassword.setText("")
                            edtCreateAccConfirmPassword.setText("")

                            // Navigate to the login page
                            val intent = Intent(this@CreateAcc, Login::class.java)
                            intent.putExtra("Username", strUsername)
                            startActivity(intent)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
            } else {
                // Password doesn't match with Confirm Password
                Toast.makeText(this, "Password Does Not Match. Please Try Again.", Toast.LENGTH_LONG).show()
                edtCreateAccName.setText("")
                edtCreateAccPassword.setText("")
                edtCreateAccConfirmPassword.setText("")
            }
        }


    }
}