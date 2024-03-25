package com.example.clinicapets.models

data class PostResponseItem(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)