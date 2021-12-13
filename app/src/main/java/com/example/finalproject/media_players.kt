package com.example.finalproject

data class media_players(
    val id: Int,
    val name: String,
    val type: String,
    var isPlaying: Boolean,
    val nowPlayingSongId: Int,
    var currentTimeSeconds: Double
)
