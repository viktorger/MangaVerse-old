package com.viktorger.mangaverse.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viktorger.mangaverse.domain.usecase.GetMangaSimpleMangaListUseCase

class HomeViewModelFactory(
    private val getMangaSimpleMangaListUseCase: GetMangaSimpleMangaListUseCase
) : ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getMangaSimpleMangaListUseCase) as T
    }
}