package com.viktorger.mangaverse.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.viktorger.mangaverse.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), RecentMangaAdapter.MangaItemListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
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

    }

    private fun setupRecyclerView() {
        binding.rvHomeRecent.adapter = RecentMangaAdapter(this)
    }

    override fun onClickListener(mangaId: Int) {
        // TODO: Write some logic here
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}