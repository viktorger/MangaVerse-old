package com.viktorger.mangaverse.presentation.home.chapter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viktorger.mangaverse.data.repository.MangaRepositoryImpl
import com.viktorger.mangaverse.data.storage.FirebaseMangaStorage
import com.viktorger.mangaverse.domain.models.ChRequest
import com.viktorger.mangaverse.domain.usecase.GetMangaChapterPagesURLUseCase

class ChapterViewModelFactory(
    private val mangaId: String, private val chapterId: String
) : ViewModelProvider.Factory {

    private val firebaseMangaStorage by lazy (LazyThreadSafetyMode.NONE) {
        FirebaseMangaStorage()
    }

    private val mangaRepository by lazy(LazyThreadSafetyMode.NONE) {
        MangaRepositoryImpl(firebaseMangaStorage)
    }

    private val getMangaChapterPagesURLUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetMangaChapterPagesURLUseCase(mangaRepository)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChapterViewModel(
            getMangaChapterPagesURLUseCase,
            chRequest = ChRequest(mangaId, chapterId)
        ) as T
    }
}