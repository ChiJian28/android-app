package com.example.gramativ

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BackEndPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { BackEndDescFragment() } // First page display Description
            1 -> { BackEndSampleFragment() } // Second page display Sample
            else -> {throw NotFoundException("Position Not Found")}
        }
    }
}