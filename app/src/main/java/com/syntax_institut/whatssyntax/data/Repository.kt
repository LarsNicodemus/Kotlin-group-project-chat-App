package com.syntax_institut.whatssyntax.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.syntax_institut.whatssyntax.BuildConfig

import com.syntax_institut.whatssyntax.data.datamodel.Call
import com.syntax_institut.whatssyntax.data.datamodel.ChatList
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import com.syntax_institut.whatssyntax.data.datamodel.Message
import com.syntax_institut.whatssyntax.data.datamodel.Profile
import com.syntax_institut.whatssyntax.data.datamodel.CallListResponse
import com.syntax_institut.whatssyntax.data.remote.WhatsSyntaxApi

class Repository() {

    private val number = 8
    private val key = BuildConfig.apiKey
    private var _contacts = MutableLiveData<List<Contact>>()
    var contacts: MutableLiveData<List<Contact>> = _contacts
    private var _contact = MutableLiveData<Contact>()
    var contact: MutableLiveData<Contact> = _contact
    private var _profile = MutableLiveData<Profile>()
    var profile: MutableLiveData<Profile> = _profile
    private var _callResponses = MutableLiveData<List<Call>>()
    var callResponses: MutableLiveData<List<Call>> = _callResponses
    private var _response = MutableLiveData<CallListResponse>()
    var response: MutableLiveData<CallListResponse> = _response
    private var _chats = MutableLiveData<List<ChatList>>()
    var chats: MutableLiveData<List<ChatList>> = _chats
    private var _chat = MutableLiveData<List<Message>>()
    var chat: MutableLiveData<List<Message>> = _chat


    suspend fun getContacts() {
        val result = WhatsSyntaxApi.retrofitService.getContacts(number, key)
        _contacts.postValue(result)
    }

    suspend fun getCalls() {
        val result = WhatsSyntaxApi.retrofitService.getCalls(number, key)
        _callResponses.postValue(result)
    }

    suspend fun getChats() {
        Log.d("Repository", "Fetching chats from API")
        val result = WhatsSyntaxApi.retrofitService.getChats(number, key)
        Log.d("Repository", "Received chats from API: $result")
        _chats.postValue(result)
    }

    suspend fun getChat(id: Int) {
        val result = WhatsSyntaxApi.retrofitService.getChat(number, id, key)
        _chat.postValue(result)
    }

    suspend fun getContact(id: Int) {
        val result = WhatsSyntaxApi.retrofitService.getContact(number, id, key)
        _contact.postValue(result)

    }

    suspend fun sendMessage(chatId: Int, message: Message) {
        return WhatsSyntaxApi.retrofitService.sendMessage(number, chatId, message, key)
    }

    fun selectContact(contact: Contact) {
        _contact.value = contact
    }

    suspend fun getProfile() {
        val result = WhatsSyntaxApi.retrofitService.getProfile(number, key)
        _profile.postValue(result)
    }

    suspend fun updateProfile(profile: Profile) {
        return WhatsSyntaxApi.retrofitService.updateProfile(number, profile, key)
    }

}