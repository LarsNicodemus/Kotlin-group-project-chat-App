package com.syntax_institut.whatssyntax.data

import androidx.lifecycle.MutableLiveData
import com.syntax_institut.whatssyntax.data.datamodel.CallResponse
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import com.syntax_institut.whatssyntax.data.datamodel.Profile
import com.syntax_institut.whatssyntax.data.datamodel.WhatsSyntaxResponse
import com.syntax_institut.whatssyntax.data.remote.WhatsSyntaxApi
import retrofit2.Response

class Repository() {

    private val number = 8
    private val key = "JSON_Jugglers"
    private var _contacts = MutableLiveData<List<Contact>>()
    var contacts: MutableLiveData<List<Contact>> = _contacts
    private var _contact = MutableLiveData<Contact>()
    var contact: MutableLiveData<Contact> = _contact
    private var _profile = MutableLiveData<Profile>()
    var profile: MutableLiveData<Profile> = _profile
    private var _callResponses = MutableLiveData<List<CallResponse>>()
    var callResponses: MutableLiveData<List<CallResponse>> = _callResponses
    private var _response = MutableLiveData<WhatsSyntaxResponse>()
    var response: MutableLiveData<WhatsSyntaxResponse> = _response


    suspend fun getContacts() {
        val result = WhatsSyntaxApi.retrofitService.getContacts(number, key)
        _contacts.postValue(result)
    }

    suspend fun getCalls() {
        val result = WhatsSyntaxApi.retrofitService.getCalls(number, key)
        _callResponses.postValue(result)
    }

    suspend fun getContact(id: Int) {
        val result = WhatsSyntaxApi.retrofitService.getContact(number, id, key)
        _contact.postValue(result)

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

    fun markStatusAsRead(contact: Contact) {

        var updatedContact = _contact.value

        if (updatedContact != null) {
            if (contact.id == _contact.value?.id) {
                _contact.value = updatedContact!!
            }
        }
    }

}