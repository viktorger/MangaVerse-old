package com.viktorger.mangaverse.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viktorger.mangaverse.domain.usecase.GetMangaSimpleMangaListUseCase

class HomeViewModel(
    private val getMangaSimpleMangaListUseCase: GetMangaSimpleMangaListUseCase
) : ViewModel() {

}