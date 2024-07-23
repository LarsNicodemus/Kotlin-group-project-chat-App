package com.syntax_institut.whatssyntax.data.datamodel

data class CallResponse(
    val contact: Contact,
    val incoming: Boolean,
    val accepted: Boolean,
    val time: String
)
