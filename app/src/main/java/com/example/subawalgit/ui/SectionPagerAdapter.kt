package com.example.subawalgit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter (activity: AppCompatActivity, private val value: String) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> {
                fragment = FollowersFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowersFragment.PROFILE_NAME, value)
                }
            }
            1 -> {
                fragment = FollowingFragment()
                fragment.arguments = Bundle().apply {
                    putString(FollowingFragment.PROFILE_NAME, value)
                }
            }
        }
        return fragment as Fragment
    }

    private fun fragmentFollowersSetData(){
        val setData = Bundle()
        setData.putString("user", "tes")
        val followersfragment:FollowersFragment= FollowersFragment()
        followersfragment.arguments= setData
    }
}