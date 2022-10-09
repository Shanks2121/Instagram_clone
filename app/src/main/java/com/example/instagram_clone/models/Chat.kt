package com.example.instagram_clone.models

data class Chat(
    val id: Int,
    val otherUser: User,
    val lastMessage: Message,
)
