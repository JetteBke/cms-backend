package com.example.cmsbackend

import com.example.cmsbackend.db.ContactTable
import mu.KotlinLogging
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Component

private val ourLogger = KotlinLogging.logger {}

@Component
class ContactDbClient {
    fun getContacts(): List<Any> {
        return transaction {
            ContactTable.selectAll().map {
                mapOf(
                    "id" to it[ContactTable.id],
                    "title" to it[ContactTable.title],
                    "firstName" to it[ContactTable.firstName],
                    "lastName" to it[ContactTable.lastName],
                    "company" to it[ContactTable.company],
                    "address" to it[ContactTable.address],
                    "postalCode" to it[ContactTable.postalCode],
                    "city" to it[ContactTable.city],
                    "phoneOne" to it[ContactTable.phoneOne],
                    "phoneTwo" to it[ContactTable.phoneTwo],
                    "emailOne" to it[ContactTable.emailOne],
                    "emailTwo" to it[ContactTable.emailTwo]
                )
            }
        }
    }

    fun getContact(contactId: Int): Any {
        return transaction {
            ContactTable.select { ContactTable.id eq contactId }.map {
                mapOf(
                    "id" to it[ContactTable.id],
                    "title" to it[ContactTable.title],
                    "firstName" to it[ContactTable.firstName],
                    "lastName" to it[ContactTable.lastName],
                    "company" to it[ContactTable.company],
                    "address" to it[ContactTable.address],
                    "postalCode" to it[ContactTable.postalCode],
                    "city" to it[ContactTable.city],
                    "phoneOne" to it[ContactTable.phoneOne],
                    "phoneTwo" to it[ContactTable.phoneTwo],
                    "emailOne" to it[ContactTable.emailOne],
                    "emailTwo" to it[ContactTable.emailTwo]
                )
            }.first()
        }
    }

    fun saveContact(contact: Map<String,String>) {
        insertContact(contact)
    }

    fun updateContact(contact: Contact) {
        transaction {
            ContactTable.update({ ContactTable.id eq contact.id }) {
                it[title] = contact.title
                it[firstName] = contact.firstName
                it[lastName] = contact.lastName
                it[company] = contact.company
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

    fun insertContactsFromFile(contactDataFromFile: List<Map<String, String>>) {
        contactDataFromFile.map { insertContact(it) }
    }

    fun insertContact(contact: Map<String,String>) {
        transaction {
            ContactTable.insert {
                it[title] = contact["title"].toString()
                it[firstName] = contact["firstName"]
                it[lastName] = contact["lastName"].toString()
                it[company] = contact["company"]
                it[address] = contact["address"]
                it[postalCode] = contact["postalCode"]?.toInt()
                it[city] = contact["city"]
                it[phoneOne] = contact["phoneOne"]?.toInt()
                it[phoneTwo] = contact["phoneTwo"]?.toInt()
                it[emailOne] = contact["emailOne"]
                it[emailTwo] = contact["emailTwo"]
            }
        }
    }
}