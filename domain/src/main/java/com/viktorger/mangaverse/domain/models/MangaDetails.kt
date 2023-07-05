package com.viktorger.mangaverse.domain.models

data class MangaDetails(
    val mangaId: String,
    val imgURL: String,
    val title: String,
    val type: String,
    val desc: String
)
