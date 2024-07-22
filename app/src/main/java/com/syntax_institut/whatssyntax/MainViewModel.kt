package com.syntax_institut.whatssyntax

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.syntax_institut.whatssyntax.data.Repository
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()

    val contacts = repository.contacts

    val contact = repository.contact


    fun getContacts() {
        viewModelScope.launch {
            try {
                repository.getContacts()
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading contacts", e)
            }
        }
    }

    fun getContact(id: Int) {
        viewModelScope.launch {
            try {
                repository.getContact(id)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading contact", e)
            }
        }
    }

//    fun selectContact(contact: Contact) {
//        repository.selectContact(contact)
//    }
}