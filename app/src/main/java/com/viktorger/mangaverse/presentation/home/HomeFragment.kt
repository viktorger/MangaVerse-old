package com.viktorger.mangaverse.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.viktorger.mangaverse.R
import com.viktorger.mangaverse.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), RecentMangaAdapter.MangaItemListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels { HomeViewModelFactory() }

    private lateinit var adapter: RecentMangaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = RecentMangaAdapter(this)
        binding.rvHomeRecent.adapter = adapter
    }

    private fun setupObservers() {
        homeViewModel.mangaShortcutList.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onClickListener(mangaId: String) {
        val action = HomeFragmentDirections.actionNavigationHomeToMangaDetailFragment(mangaId)
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}