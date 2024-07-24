package com.syntax_institut.whatssyntax.data.datamodel

data class ChatList(
    val id: Int,
    val contact: Contact,
    val lastMessage: Message
)
