package com.viktorger.mangaverse.presentation.home.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.viktorger.mangaverse.databinding.FragmentMangaDetailBinding
import com.viktorger.mangaverse.databinding.ItemChapterBinding

class MangaDetailFragment : Fragment() {

    private var _binding: FragmentMangaDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MangaDetailFragmentArgs by navArgs()

    private val viewModel: MangaDetailViewModel by viewModels {
        MangaDetailViewModelFactory(args.mangaId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMangaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivDetailArrowBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // TODO: UNSUPPORTABLE
        viewModel.detailsLiveData.observe(viewLifecycleOwner) {
            binding.tvDetailTitle.text = it.title
            binding.tvDetailType.text = it.type
            binding.tvDetailDesc.text = it.desc

            // Reference to an image file in Cloud Storage
            val storageReference = Firebase.storage.getReferenceFromUrl(it.imgURL)
            storageReference.downloadUrl.addOnSuccessListener { url ->

                val circularProgressDrawable = CircularProgressDrawable(binding.root.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(binding.root.context)
                    .load(url)
                    .placeholder(circularProgressDrawable)
                    .into(binding.ivDetailBackground)

            }

        }

        viewModel.chaptersLiveData.observe(viewLifecycleOwner) {

            val linLayout = binding.llDetailLayout

            for (chapter in it) {
                val chBinding = ItemChapterBinding
                    .inflate(layoutInflater, linLayout, true)

                chBinding.root.id = View.generateViewId()
                chBinding.tvChapterVolume.text = chapter.vol.toString()
                chBinding.tvChapterChapter.text = chapter.name


                chBinding.root.setOnClickListener {
                    val action = MangaDetailFragmentDirections
                        .actionMangaDetailFragmentToChapterFragment(
                            mangaId = args.mangaId,
                            chapterId = chapter.id
                        )
                    findNavController().navigate(action)
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}