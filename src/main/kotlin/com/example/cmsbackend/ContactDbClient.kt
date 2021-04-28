package com.example.cmsbackend

import com.example.cmsbackend.db.ContactTable
import mu.KotlinLogging
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

private val ourLogger = KotlinLogging.logger {}

@Component
class ContactDbClient {
    fun getContacts(): List<Any> {
        ourLogger.info { "About to get contacts" }
        return ContactTable.getAll()
    }

    fun saveContact(contact: Contact) {
        ourLogger.info { "About to insert data" }
        transaction {
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
        ourLogger.info { "Successfully inserted a contact" }
    }
}