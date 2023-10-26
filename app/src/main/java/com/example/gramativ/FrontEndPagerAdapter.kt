package com.example.gramativ

import android.content.res.Resources.NotFoundException
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FrontEndPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2 // Two tabs provided

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { FrontEndDescFragment() } // First page display Description
            1 -> { FrontEndSampleFragment() } // Second page display Sample
            else -> {throw NotFoundException("Position Not Found")}
        }
    }
}