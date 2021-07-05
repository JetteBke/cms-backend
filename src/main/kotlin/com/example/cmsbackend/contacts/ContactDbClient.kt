package com.example.cmsbackend.contacts

import com.example.cmsbackend.db.ContactTable
import com.example.cmsbackend.db.NoteTable
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Component

@Component
class ContactDbClient {
    fun getContacts(): List<ContactWithId> {
        return transaction {
            ContactTable.selectAll().map {
                ContactWithId(
                    id = it[ContactTable.id],
                    title = it[ContactTable.title],
                    firstName = it[ContactTable.firstName],
                    lastName = it[ContactTable.lastName],
                    company = it[ContactTable.company],
                    address = it[ContactTable.address],
                    postalCode = it[ContactTable.postalCode],
                    city = it[ContactTable.city],
                    phoneOne = it[ContactTable.phoneOne],
                    phoneTwo = it[ContactTable.phoneTwo],
                    emailOne = it[ContactTable.emailOne],
                    emailTwo = it[ContactTable.emailTwo]
                )
            }
        }
    }

    fun getContact(contactId: Int): ContactWithId {
        return transaction {
            ContactTable.select { ContactTable.id eq contactId }.map {
                ContactWithId(
                    id = it[ContactTable.id],
                    title = it[ContactTable.title],
                    firstName = it[ContactTable.firstName],
                    lastName = it[ContactTable.lastName],
                    company = it[ContactTable.company],
                    address = it[ContactTable.address],
                    postalCode = it[ContactTable.postalCode],
                    city = it[ContactTable.city],
                    phoneOne = it[ContactTable.phoneOne],
                    phoneTwo = it[ContactTable.phoneTwo],
                    emailOne = it[ContactTable.emailOne],
                    emailTwo = it[ContactTable.emailTwo]
                )
            }.first()
        }
    }

    fun saveContact(contact: Contact) {
        insertContact(contact)
    }

    fun updateContact(contact: ContactWithId) {
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

    fun deleteContact(contactId: Int) {
        transaction {
            NoteTable.deleteWhere { NoteTable.contactId eq contactId }
        }
        transaction {
            ContactTable.deleteWhere { ContactTable.id eq contactId }
        }
    }

    fun insertContactsFromFile(contactDataFromFile: List<Contact>) {
        contactDataFromFile.map { insertContact(it) }
    }

    private fun insertContact(contact: Contact) {
        transaction {
            ContactTable.insert {
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
}