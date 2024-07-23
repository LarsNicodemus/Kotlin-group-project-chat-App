package com.syntax_institut.whatssyntax

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.syntax_institut.whatssyntax.data.Repository
import com.syntax_institut.whatssyntax.data.datamodel.Contact
import com.syntax_institut.whatssyntax.data.datamodel.Profile
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()

    val contacts = repository.contacts

    val contact = repository.contact

    val profile = repository.profile


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

    fun markCurrentStatusAsRead() {
        val currentContact = contact.value
        currentContact?.markStatusAsRead()
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