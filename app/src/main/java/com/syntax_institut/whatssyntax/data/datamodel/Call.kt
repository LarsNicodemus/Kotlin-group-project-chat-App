package com.syntax_institut.whatssyntax.data.datamodel

data class Call(
    val contact: Contact,
    val incoming: Boolean,
    val accepted: Boolean,
    val time: String
)
