package com.syntax_institut.whatssyntax

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.syntax_institut.whatssyntax.data.Repository
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import com.syntax_institut.whatssyntax.data.datamodel.Profile
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()

    var contacts = repository.contacts

    private var contact = repository.contact

    var profile = repository.profile


    init {
        getContacts()
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

    fun getContact(id: Int) {
        viewModelScope.launch {
            try {
                repository.getContact(id)
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error loading contact", e)
            }
        }
    }

    fun markCurrentStatusAsRead() {
        val currentContact = contact.value
        currentContact?.statusSeen = true
        contact.value = currentContact
    }

    fun markStatusAsReadTwo(contact: Contact) {
        repository.markStatusAsRead(contact)
    }

    fun markCurrentStatusAsUnread() {
        val currentContact = contact.value
        currentContact?.statusSeen = false
        contact.value = currentContact
    }

    fun getProfile() {
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
}