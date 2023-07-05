package com.viktorger.mangaverse.data.repository

import com.viktorger.mangaverse.data.storage.MangaStorage
import com.viktorger.mangaverse.data.storage.models.ChRequestData
import com.viktorger.mangaverse.data.storage.models.ChapterData
import com.viktorger.mangaverse.data.storage.models.MangaShortcutData
import com.viktorger.mangaverse.domain.models.ChRequest
import com.viktorger.mangaverse.domain.models.Chapter
import com.viktorger.mangaverse.domain.models.MangaDetails
import com.viktorger.mangaverse.domain.models.MangaShortcut
import com.viktorger.mangaverse.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip

class MangaRepositoryImpl(
    private val mangaStorage: MangaStorage
) : MangaRepository {

    override fun getMangaShortcutList(): Flow<List<MangaShortcut>> = mangaStorage
        .getMangaShortcutList()
        .map { mapShortcutToDomain(it) }


    override fun getMangaDetails(id: String): Flow<MangaDetails> = mangaStorage.getMangaShortcut(id)
        .zip(mangaStorage.getMangaDescription(id)) { mangaShortcutData, desc ->
            MangaDetails(
                mangaId = mangaShortcutData.mangaId,
                imgURL = mangaShortcutData.imgURL,
                title = mangaShortcutData.title,
                type = mangaShortcutData.type,
                desc = desc
            )
        }

    override fun getChapterList(id: String): Flow<List<Chapter>> = mangaStorage
        .getChapterList(id)
        .map { mapChapterToDomain(it) }

    override fun getChapterPagesURL(chRequest: ChRequest): Flow<List<String>> = mangaStorage
        .getChapterPagesURL(mapChapterRequestFromData(chRequest))

    private fun mapChapterRequestFromData(chRequest: ChRequest): ChRequestData {
        return ChRequestData(
            mangaId = chRequest.mangaId,
            chapterId = chRequest.chapterId
        )
    }

    private fun mapShortcutToDomain(mangaShortcutDataList: List<MangaShortcutData>) :
            List<MangaShortcut> = mangaShortcutDataList.map{ MangaShortcut(
            mangaId = it.mangaId,
            imgURL = it.imgURL,
            title = it.title,
            type = it.type
        ) }

    private fun mapChapterToDomain(mangaChapterDataList: List<ChapterData>) :
            List<Chapter> {
        return mangaChapterDataList.map{ Chapter(
            vol = it.vol,
            id = it.id,
            name = it.name
        ) }
    }

}

