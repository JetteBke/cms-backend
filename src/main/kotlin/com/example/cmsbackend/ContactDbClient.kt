package com.example.cmsbackend

import com.example.cmsbackend.db.ContactTable
import org.jetbrains.exposed.sql.insert
import org.springframework.stereotype.Component

@Component
class ContactDbClient {
    fun getContacts(): List<Any> {

        return ContactTable.getAll()
    }

    fun saveContact(contact: Contact) {
        ContactTable.insert {
            it[title] = contact.title
            it[firstName] = contact.firstName
            it[lastName] = contact.lastName
            it[address] = contact.address
            it[postalCode] = contact.postalCode
            it[city] = contact.city
            it[phoneOne] = contact.phoneOne
            it[phoneTwo] = contact.phoneTwo
            it[emailOne] = contact.emailOne
            it[emailTwo] = contact.emailTwo
        }
    }
}