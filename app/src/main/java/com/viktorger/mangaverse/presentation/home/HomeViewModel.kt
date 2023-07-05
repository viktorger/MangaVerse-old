package com.viktorger.mangaverse.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.viktorger.mangaverse.domain.models.MangaShortcut
import com.viktorger.mangaverse.domain.usecase.GetMangaShortcutListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class HomeViewModel(
    private val getMangaShortcutListUseCase: GetMangaShortcutListUseCase
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)

    val mangaShortcutList: LiveData<List<MangaShortcut>> =
        getMangaShortcutListUseCase.execute().asLiveData()

    /*companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            private val firebaseMangaStorage by lazy (LazyThreadSafetyMode.NONE) {
                FirebaseMangaStorage()
            }

            private val mangaRepository by lazy(LazyThreadSafetyMode.NONE) {
                MangaRepositoryImpl(firebaseMangaStorage)
            }

            private val getMangaSimpleListUseCase by lazy(LazyThreadSafetyMode.NONE) {
                GetMangaSimpleListUseCase(mangaRepository)
            }

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(getMangaSimpleListUseCase) as T
            }
        }

    }*/
}