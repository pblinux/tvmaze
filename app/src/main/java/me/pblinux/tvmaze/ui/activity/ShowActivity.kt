package me.pblinux.tvmaze.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.pblinux.tvmaze.data.models.State
import me.pblinux.tvmaze.data.models.episode.Episode
import me.pblinux.tvmaze.data.models.show.Show
import me.pblinux.tvmaze.data.viewmodel.ShowViewModel
import me.pblinux.tvmaze.databinding.ActivityShowBinding

@AndroidEntryPoint
class ShowActivity : AppCompatActivity() {
    // ViewModel
    private val showViewModel: ShowViewModel by viewModels()

    // Binding
    private lateinit var binding: ActivityShowBinding

    // Model
    private lateinit var show: Show


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        show = intent.getParcelableExtra<Show>("show")!!
        showViewModel.changeShow(show)
        binding = ActivityShowBinding.inflate(layoutInflater)
        binding.episodeInfoVisible = false
        binding.singleShow = show
        binding.showEpisodes = listOf()
        setContentView(binding.root)
        setupTabs()
    }

    fun setupTabs() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    showViewModel.episodes.collect { state ->
                        when (state) {
                            is State.Loaded<*> -> {
                                val result = (state.data as Map<Int, List<Episode>>)
                                binding.episodeInfoVisible = true
                                binding.showEpisodes = result.entries.first().value
                                result.onEachIndexed { _, entry ->
                                    val title =
                                        if (entry.key > 100) "${entry.key}" else "Season ${entry.key}"
                                    binding.tabs.addTab(binding.tabs.newTab().setText(title))
                                }
                                binding.tabs.addOnTabSelectedListener(object :
                                    TabLayout.OnTabSelectedListener {
                                    override fun onTabSelected(tab: TabLayout.Tab?) {
                                        tab?.let {
                                            val position = tab.position + 1
                                            val episodes = result[position]
                                            episodes?.let { eps ->
                                                binding.showEpisodes = eps
                                            }
                                        }
                                    }

                                    override fun onTabUnselected(tab: TabLayout.Tab?) {}

                                    override fun onTabReselected(tab: TabLayout.Tab?) {}
                                })
                            }
                            else -> {
                            }
                        }
                    }
                }
            }
        }
    }
}