package com.viktorger.mangaverse.domain.usecase

import com.viktorger.mangaverse.domain.models.ChRequest
import com.viktorger.mangaverse.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import java.net.URL

class GetMangaChapterPagesURLUseCase(private val mangaRepository: MangaRepository) {

    fun execute(chRequest: ChRequest) = mangaRepository
        .getChapterPagesURL(chRequest)

}