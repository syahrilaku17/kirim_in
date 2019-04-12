package com.example.kirim_in.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                com.example.kirim_in.Frgament.HomeFragment()
            }
            2 -> {
                com.example.kirim_in.Frgament.SearchFragment()
            }
            3 -> {
                com.example.kirim_in.Frgament.AddFragment()
            }
            4 -> {
                com.example.kirim_in.Frgament.MessageFragment()
            }
            else -> {
                return com.example.kirim_in.Frgament.ProfileFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Voice Call"
            else -> {
                return "Video Call"
            }
        }
    }
}