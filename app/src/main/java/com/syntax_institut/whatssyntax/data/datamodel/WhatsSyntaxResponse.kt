package com.syntax_institut.whatssyntax.data.datamodel

data class WhatsSyntaxResponse(
    val count: Int,
    val results: List<Contact>
)
