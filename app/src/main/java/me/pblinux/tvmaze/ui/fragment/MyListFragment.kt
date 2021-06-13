package me.pblinux.tvmaze.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import me.pblinux.tvmaze.data.viewmodel.HomeViewModel
import me.pblinux.tvmaze.databinding.FragmentMyListBinding
import me.pblinux.tvmaze.ui.adapter.RowShowsAdapter

class MyListFragment : Fragment() {
    // Binding
    private var _binding: FragmentMyListBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val homeViewModel: HomeViewModel by activityViewModels()

    //Adapter
    private val showsAdapter = RowShowsAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.myShowsList.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = showsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            homeViewModel.favourites.collectLatest { data ->
                showsAdapter.addItems(data)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MyListFragment().apply {}
    }
}