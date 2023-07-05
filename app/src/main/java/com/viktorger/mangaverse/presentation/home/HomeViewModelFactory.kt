package com.viktorger.mangaverse.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viktorger.mangaverse.data.repository.MangaRepositoryImpl
import com.viktorger.mangaverse.data.storage.FirebaseMangaStorage
import com.viktorger.mangaverse.domain.usecase.GetMangaShortcutListUseCase

class HomeViewModelFactory : ViewModelProvider.Factory {
    private val firebaseMangaStorage by lazy (LazyThreadSafetyMode.NONE) {
        FirebaseMangaStorage()
    }

    private val mangaRepository by lazy(LazyThreadSafetyMode.NONE) {
        MangaRepositoryImpl(firebaseMangaStorage)
    }

    private val getMangaShortcutListUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetMangaShortcutListUseCase(mangaRepository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getMangaShortcutListUseCase) as T
    }
}