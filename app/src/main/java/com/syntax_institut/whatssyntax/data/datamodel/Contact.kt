package com.syntax_institut.whatssyntax.data.datamodel

data class Contact(
    val id: Int,
    val name: String,
    val number: String,
    val image: String,
    val status: Status?
) {
    fun markStatusAsRead() {
        status?.isRead = true
    }
}
