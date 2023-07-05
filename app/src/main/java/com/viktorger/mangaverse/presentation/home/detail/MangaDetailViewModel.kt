package com.viktorger.mangaverse.presentation.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.viktorger.mangaverse.domain.usecase.GetMangaChaptersInfoUseCase
import com.viktorger.mangaverse.domain.usecase.GetMangaDetailsUseCase

class MangaDetailViewModel(
    private val getMangaDetailsUseCase: GetMangaDetailsUseCase,
    private val getMangaChaptersInfoUseCase: GetMangaChaptersInfoUseCase,
    private val mangaId: String
) : ViewModel() {

    val detailsLiveData = getMangaDetailsUseCase.execute(mangaId).asLiveData()

    val chaptersLiveData = getMangaChaptersInfoUseCase.execute(mangaId).asLiveData()

}