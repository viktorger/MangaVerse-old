package com.viktorger.mangaverse.presentation.home.chapter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.viktorger.mangaverse.R
import com.viktorger.mangaverse.databinding.FragmentChapterBinding

class ChapterFragment : Fragment() {

    private var _binding: FragmentChapterBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PagesAdapter

    private val args: ChapterFragmentArgs by navArgs()

    private val viewModel: ChapterViewModel by viewModels {
        ChapterViewModelFactory(args.mangaId, args.chapterId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChapterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initListeners()

    }

    private fun initAdapter() {
        adapter = PagesAdapter()
        binding.rvChapter.adapter = adapter
    }

    private fun initListeners() {
        viewModel.urlLiveData.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}