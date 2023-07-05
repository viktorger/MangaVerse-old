package com.viktorger.mangaverse.domain.usecase

import com.viktorger.mangaverse.domain.repository.MangaRepository

class GetMangaDetailsUseCase(private val mangaRepository: MangaRepository) {
    fun execute(id: String) = mangaRepository.getMangaDetails(id)
}