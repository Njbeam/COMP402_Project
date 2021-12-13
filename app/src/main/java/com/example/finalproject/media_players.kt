package com.example.finalproject

data class media_players(
    val id: Int,
    val name: String,
    val type: String,
    val isPlaying: Boolean,
    val nowPlayingSongId: Int,
    val currentTimeSeconds: Float
)
