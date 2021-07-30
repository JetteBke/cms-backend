package com.example.cmsbackend.contacts

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.cmsbackend.Failure
import java.io.BufferedInputStream
import java.io.InputStream
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

private val ourLogger = KotlinLogging.logger {}

//class Config {
//    @Bean
//    fun service(dbClient: ContactDbClient): ContactService = ContactService(dbClient)
//}

@Service
class ContactService(
    private val dbClient: ContactDbClient
) {
    fun getContacts(): List<Contact> {
        ourLogger.info { "Getting contacts" }
        return dbClient.getContacts()
    }

    fun saveContact(contact: ContactRequest): Either<Failure, Unit> =
        runCatching {
            ourLogger.info { "The following data will be inserted $contact" }
            dbClient.saveContact(contact).right()
        }.getOrElse {
            ourLogger.info { "The following contact could not be saved: $contact" }
            Failure("Contact could not be saved").left()
        }

    fun updateContact(contact: Contact): Either<Failure, Unit> =
        runCatching {
            ourLogger.info { "The contact with id ${contact.id} will be updated" }
            dbClient.updateContact(contact).right()
        }.getOrElse {
            ourLogger.info { "The following contact could not be updated: $contact" }
            Failure("Contact could not be updated").left()
        }

    fun getContact(contactId: Int): Either<Failure, Contact> =
        runCatching {
            ourLogger.info { "Getting contact with id: $contactId" }
            return dbClient.getContact(contactId).right()
        }.getOrElse {
            ourLogger.info { "Could not get contact with id $contactId" }
            Failure("Could not get contact with id $contactId").left()
        }

    fun deleteContact(contactId: Int): Either<Failure, Unit> =
        runCatching {
            ourLogger.info { "Removing contact with id: $contactId" }
            return dbClient.deleteContact(contactId).right()
        }.getOrElse {
            ourLogger.info { "Could not delete contact with id $contactId" }
            Failure("Could not delete contact with id $contactId").left()
        }

    fun uploadFile(file: MultipartFile) {
        ourLogger.info { "Uploading file ${file.originalFilename}" }
        val inputStream: InputStream = BufferedInputStream(file.inputStream)
        val contactData = loadDataFromCsv(inputStream).map { it.toContactRequest() }
        dbClient.insertContactsFromFile(contactData)
        ourLogger.info { "Inserted ${contactData.size} contacts" }
    }
}
