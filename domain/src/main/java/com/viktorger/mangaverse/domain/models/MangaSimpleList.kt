package com.viktorger.mangaverse.domain.models

data class MangaSimpleListItem(
    val mangaId: Int,
    val resourceId: Int,
    val title: String,
    val type: String
)