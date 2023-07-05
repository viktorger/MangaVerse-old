package com.viktorger.mangaverse.domain.usecase

import com.viktorger.mangaverse.domain.models.Chapter
import com.viktorger.mangaverse.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow

class GetMangaChaptersInfoUseCase(private val mangaRepository: MangaRepository) {

    fun execute(id: String): Flow<List<Chapter>> = mangaRepository.getChapterList(id)

}