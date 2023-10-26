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

class BackEndProject : AppCompatActivity() {

    private lateinit var txtBackEndProjectUsername: TextView
    private lateinit var imgBtnBackEndBackProjects: ImageButton
    private lateinit var btnBackEndEnrol: Button
    private lateinit var tabLayoutBackEnd: TabLayout
    private lateinit var viewPagerBackEnd: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_back_end_project)

        txtBackEndProjectUsername = findViewById(R.id.txtBackEndProjectUsername)
        imgBtnBackEndBackProjects = findViewById(R.id.imgBtnBackEndBackProjects)
        btnBackEndEnrol = findViewById(R.id.btnBackEndEnrol)
        tabLayoutBackEnd = findViewById(R.id.tabLayoutBackEnd)
        viewPagerBackEnd = findViewById(R.id.viewPagerBackEnd)
        viewPagerBackEnd.adapter = BackEndPagerAdapter(this) // setting up adapter to be applied on viewPager
        TabLayoutMediator(tabLayoutBackEnd, viewPagerBackEnd){ tab, index ->
            tab.text = when(index){ // to identify the tab title
                0 -> {"Description"}
                1 -> {"Sample"}
                else -> {throw NotFoundException("Position Not Found")}
            }
        }.attach()

        val strUsername = intent.getStringExtra("Username")
        txtBackEndProjectUsername.setText(strUsername)

        btnBackEndEnrol.setOnClickListener {
            val intent = Intent(this@BackEndProject, Tutors::class.java)
            intent.putExtra("Username", strUsername)
            startActivity(intent)
        }

        imgBtnBackEndBackProjects.setOnClickListener {
            finish()
        }
    }
}