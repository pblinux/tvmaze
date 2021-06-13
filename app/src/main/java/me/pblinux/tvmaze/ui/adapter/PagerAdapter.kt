package me.pblinux.tvmaze.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.pblinux.tvmaze.ui.fragment.MyListFragment
import me.pblinux.tvmaze.ui.fragment.ShowsFragment

class PagerAdapter(activity: AppCompatActivity, private val count: Int) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return count
    }

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ShowsFragment.newInstance()
        1 -> MyListFragment.newInstance()
        else -> Fragment()
    }
}