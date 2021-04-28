package com.example.cmsbackend

import org.springframework.stereotype.Service

@Service
class ContactService(
    val dbClient: ContactDbClient
) {
    fun getContacts() {
        dbClient.getContacts()
    }

    fun saveContact(contactData: Map<String, String>) {
        val contact = contactData.toContact()
        dbClient.saveContact(contact)
    }
}
