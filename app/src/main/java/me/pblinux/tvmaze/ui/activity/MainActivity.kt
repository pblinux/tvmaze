package me.pblinux.tvmaze.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import me.pblinux.tvmaze.databinding.ActivityMainBinding
import me.pblinux.tvmaze.ui.adapter.PagerAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = PagerAdapter(this, 2)
        TabLayoutMediator(binding.tabs, binding.pager) { tab, index ->
            tab.text = getTabTitle(index)
        }.attach()
    }

    private fun getTabTitle(index: Int): String = when (index) {
        0 -> "Shows"
        1 -> "My List"
        else -> ""
    }

    fun onSearchClick(view: View) {
        startActivity(Intent(view.context, SearchActivity::class.java))
    }
}