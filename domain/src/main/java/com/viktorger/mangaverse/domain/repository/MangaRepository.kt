package com.viktorger.mangaverse.domain.repository

import com.viktorger.mangaverse.domain.models.ChRequest
import com.viktorger.mangaverse.domain.models.Chapter
import com.viktorger.mangaverse.domain.models.MangaDetails
import com.viktorger.mangaverse.domain.models.MangaShortcut
import kotlinx.coroutines.flow.Flow
import java.net.URL

interface MangaRepository {
    fun getMangaShortcutList(): Flow<List<MangaShortcut>>

    fun getMangaDetails(id: String): Flow<MangaDetails>

    fun getChapterList(id: String): Flow<List<Chapter>>

    fun getChapterPagesURL(chRequest: ChRequest): Flow<List<String>>
}