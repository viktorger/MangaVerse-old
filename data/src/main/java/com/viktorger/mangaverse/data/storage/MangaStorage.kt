package com.viktorger.mangaverse.data.storage

import com.viktorger.mangaverse.data.storage.models.ChRequestData
import com.viktorger.mangaverse.data.storage.models.ChapterData
import com.viktorger.mangaverse.data.storage.models.MangaShortcutData
import kotlinx.coroutines.flow.Flow
import java.net.URL

interface MangaStorage {

    fun getMangaShortcutList(): Flow<List<MangaShortcutData>>

    fun getMangaShortcut(id: String): Flow<MangaShortcutData>

    fun getMangaDescription(id: String): Flow<String>

    fun getChapterList(id: String): Flow<List<ChapterData>>

    fun getChapterPagesURL(chRequestData: ChRequestData): Flow<List<String>>
}