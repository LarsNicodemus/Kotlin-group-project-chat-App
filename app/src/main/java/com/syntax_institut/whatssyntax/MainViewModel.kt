package com.syntax_institut.whatssyntax

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.syntax_institut.whatssyntax.data.Repository
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import com.syntax_institut.whatssyntax.data.datamodel.Message
import com.syntax_institut.whatssyntax.data.datamodel.Note
import com.syntax_institut.whatssyntax.data.datamodel.Profile
import com.syntax_institut.whatssyntax.data.local.NoteDatabase
import com.syntax_institut.whatssyntax.data.local.WhatsSyntaxDatabaseDao
import com.syntax_institut.whatssyntax.data.local.getDatabase
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database: NoteDatabase = getDatabase(application)
    private val repository = Repository(database)

    var contacts = repository.contacts

    private var contact = repository.contact

    var profile = repository.profile

    var callResponse = repository.callResponses


    var chats = repository.chats

    var chat = repository.chat

    val notes = repository.noteList


    init {
        getContacts()
        getChats()
        getProfile()
        getCalls()
    }

    private fun getContacts() {
        viewModelScope.launch {
            try {
                repository.getContacts()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading contacts", e)
            }
        }
    }

    private fun getCalls() {
        viewModelScope.launch {
            try {
                repository.getCalls()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading contacts", e)
            }
        }
    }

    fun getChats() {
        viewModelScope.launch {
            try {
                repository.getChats()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading Chats", e)
            }
        }
    }

    fun getChat(id: Int) {
        viewModelScope.launch {
            try {
                repository.getChat(id)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading Chat", e)
            }
        }
    }

    fun sendMessage(chatId: Int, message: Message) {
        viewModelScope.launch {
            try {
                repository.sendMessage(chatId, message)
                getChat(chatId)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error sending message", e)
            }
        }
    }

    fun refreshChats() {
        Log.d("MainViewModel", "Refreshing chats")
        viewModelScope.launch {
            try {
                repository.getChats()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error refreshing chats", e)
            }
        }
    }


    private fun getProfile() {
        viewModelScope.launch {
            try {
                repository.getProfile()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading profile", e)
            }
        }
    }


    fun updateProfile(profile: Profile) {
        viewModelScope.launch {
            try {
                repository.updateProfile(profile)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error updating profile", e)
            }
        }
    }


    fun selectContact(contact: Contact) {
        repository.selectContact(contact)
    }

    fun isValidName(name: String): Boolean {
        // Name sollte nicht leer sein und keine Zahlen oder Sonderzeichen enthalten
        return name.isNotBlank() && name.matches(Regex("^[\\p{L} .'-]+$"))
    }

    fun isValidPhoneNumber(number: String): Boolean {
        // Telefonnummer sollte nur Zahlen, '+', '-' und Leerzeichen enthalten
        return number.isNotBlank() && number.matches(Regex("^[+\\d\\s-]+$"))
    }

    fun insertNote(note: Note){
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    fun deleteNote(id: Long){
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

    fun deleteAllNotes(){
        viewModelScope.launch {
            repository.deleteAllNotes()
        }
    }

    fun getNote(id: Long): LiveData<Note> {
        return repository.getNote(id)
    }
}