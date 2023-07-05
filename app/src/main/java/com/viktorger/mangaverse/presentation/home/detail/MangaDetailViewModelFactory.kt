package com.viktorger.mangaverse.presentation.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viktorger.mangaverse.data.repository.MangaRepositoryImpl
import com.viktorger.mangaverse.data.storage.FirebaseMangaStorage
import com.viktorger.mangaverse.domain.usecase.GetMangaChaptersInfoUseCase
import com.viktorger.mangaverse.domain.usecase.GetMangaDetailsUseCase

class MangaDetailViewModelFactory(private val mangaId: String) : ViewModelProvider.Factory {
    private val firebaseMangaStorage by lazy (LazyThreadSafetyMode.NONE) {
        FirebaseMangaStorage()
    }

    private val mangaRepository by lazy(LazyThreadSafetyMode.NONE) {
        MangaRepositoryImpl(firebaseMangaStorage)
    }

    private val getMangaDetailsUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetMangaDetailsUseCase(mangaRepository)
    }

    private val getMangaChaptersInfoUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetMangaChaptersInfoUseCase(mangaRepository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MangaDetailViewModel(
            getMangaDetailsUseCase,
            getMangaChaptersInfoUseCase,
            mangaId = mangaId
        ) as T
    }
}