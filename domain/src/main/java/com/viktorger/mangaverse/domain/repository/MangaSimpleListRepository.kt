package com.viktorger.mangaverse.domain.repository

import com.viktorger.mangaverse.domain.models.MangaSimpleListItem

interface MangaSimpleListRepository {
    fun getMangaSimpleList(): List<MangaSimpleListItem>
}