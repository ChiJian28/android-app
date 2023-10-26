package com.example.gramativ

import android.content.Intent
import android.content.res.Resources.NotFoundException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FrontEndProject : AppCompatActivity() {

    private lateinit var txtFrontEndProjectUsername: TextView
    private lateinit var imgBtnFrontEndBackProjects: ImageButton
    private lateinit var btnFrontEndEnrol: Button
    private lateinit var tabLayoutFrontEnd: TabLayout
    private lateinit var viewPagerFrontEnd: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front_end_project)

        txtFrontEndProjectUsername = findViewById(R.id.txtFrontEndProjectUsername)
        imgBtnFrontEndBackProjects = findViewById(R.id.imgBtnFrontEndBackProjects)
        btnFrontEndEnrol = findViewById(R.id.btnFrontEndEnrol)
        tabLayoutFrontEnd = findViewById(R.id.tabLayoutFrontEnd)
        viewPagerFrontEnd = findViewById(R.id.viewPagerFrontEnd)
        viewPagerFrontEnd.adapter = FrontEndPagerAdapter(this) // setting up adapter to be applied on viewPager
        TabLayoutMediator(tabLayoutFrontEnd, viewPagerFrontEnd){ tab, index ->
            tab.text = when(index){ // to identify the tab title
                0 ->{"Description"}
                1 -> {"Sample"}
                else -> {throw NotFoundException("Position Not Found")}
            }
        }.attach()

        val strUsername = intent.getStringExtra("Username")
        txtFrontEndProjectUsername.setText(strUsername)

        btnFrontEndEnrol.setOnClickListener {
            val intent = Intent(this@FrontEndProject, Tutors::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }
        imgBtnFrontEndBackProjects.setOnClickListener {
            finish()
        }
    }
}