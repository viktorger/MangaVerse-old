package com.viktorger.mangaverse.domain.usecase

import com.viktorger.mangaverse.domain.models.MangaShortcut
import com.viktorger.mangaverse.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class GetMangaShortcutListUseCase(
    private val mangaSimpleListRepository: MangaRepository
) {
    fun execute(): Flow<List<MangaShortcut>> = mangaSimpleListRepository.getMangaShortcutList()
}