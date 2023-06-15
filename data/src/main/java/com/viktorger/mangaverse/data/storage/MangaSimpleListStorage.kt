package com.viktorger.mangaverse.data.storage

import com.viktorger.mangaverse.data.storage.models.MangaSimpleListItemData

interface MangaSimpleListStorage {

    fun getMangaSimpleList(): List<MangaSimpleListItemData>

}