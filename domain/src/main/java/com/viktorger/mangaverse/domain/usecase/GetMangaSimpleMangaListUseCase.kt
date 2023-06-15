package com.viktorger.mangaverse.domain.usecase

import com.viktorger.mangaverse.domain.models.MangaSimpleListItem
import com.viktorger.mangaverse.domain.repository.MangaSimpleListRepository

class GetMangaSimpleMangaListUseCase(
    private val mangaSimpleListRepository: MangaSimpleListRepository
) {
    fun execute(): List<MangaSimpleListItem> {
        return mangaSimpleListRepository.getMangaSimpleList()
    }
}