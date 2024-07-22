package com.syntax_institut.whatssyntax.data

import androidx.lifecycle.MutableLiveData
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import com.syntax_institut.whatssyntax.data.remote.WhatsSyntaxApi

class Repository() {

    private val number = 8
    private val key = "JSON_Jugglers"
    private var _contacts = MutableLiveData<List<Contact>>()
    val contacts: MutableLiveData<List<Contact>> = _contacts
    private var _contact = MutableLiveData<Contact>()
    val contact: MutableLiveData<Contact> = _contact



    suspend fun getContacts() {
        val result = WhatsSyntaxApi.retrofitService.getContacts(number, key)
        _contacts.postValue(result)
    }

    suspend fun getContact(id: Int) {
        val result = WhatsSyntaxApi.retrofitService.getContact(number,id,key)
        _contact.postValue(result)

    }

    fun selectContact(contact: Contact) {
        _contact.value = contact
    }

}