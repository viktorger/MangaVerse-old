package com.viktorger.mangaverse.data.repository

import com.viktorger.mangaverse.data.storage.MangaSimpleListStorage
import com.viktorger.mangaverse.data.storage.models.MangaSimpleListItemData
import com.viktorger.mangaverse.domain.models.MangaSimpleListItem
import com.viktorger.mangaverse.domain.repository.MangaSimpleListRepository

class MangaSimpleListRepositoryImpl(
    private val mangaSimpleListStorage: MangaSimpleListStorage
) : MangaSimpleListRepository {
    override fun getMangaSimpleList(): List<MangaSimpleListItem> {
        val mangaSimpleListData = mangaSimpleListStorage.getMangaSimpleList()
        return mapToDomain(mangaSimpleListData)
    }

    private fun mapToDomain(mangaSimpleListData: List<MangaSimpleListItemData>) :
            List<MangaSimpleListItem> {
        return mangaSimpleListData.map{ MangaSimpleListItem(
            mangaId = it.mangaId,
            resourceId = it.resourceId,
            title = it.title,
            type = it.type
        ) }.toList()
    }

}