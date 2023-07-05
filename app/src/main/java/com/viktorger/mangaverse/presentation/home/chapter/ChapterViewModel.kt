package com.viktorger.mangaverse.presentation.home.chapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.viktorger.mangaverse.domain.models.ChRequest
import com.viktorger.mangaverse.domain.usecase.GetMangaChapterPagesURLUseCase

class ChapterViewModel(
    getMangaChapterPagesURLUseCase: GetMangaChapterPagesURLUseCase,
    chRequest: ChRequest
) : ViewModel() {

    val urlLiveData = getMangaChapterPagesURLUseCase.execute(chRequest).asLiveData()

}