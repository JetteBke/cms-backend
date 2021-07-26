package com.example.cmsbackend.contacts

import com.example.cmsbackend.Failure
import com.example.cmsbackend.isLeft
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

class ContactServiceTest {
    private val contactDbClient = mockk<ContactDbClient>()
    private val contactService = ContactService(contactDbClient)
    private val contactList = listOf(
        ContactRequest(
            lastName = "Mustermann",
            title = "Herr",
            firstName = "",
            company = "",
            address = "",
            postalCode = 12345,
            city = "",
            phoneOne = 123456789,
            phoneTwo = 123456789,
            emailOne = "",
            emailTwo = "",
        )
    )

//    todo: Find a way to test Multipart file without mocking it
//    @Test
//    fun `should save received file`() {
//        val file = MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "hello World!".toByteArray())
//
//        every {
//            loadDataFromCsv(any())
//        } returns listOf(
//            mapOf(
//                "lastName" to "Mustermann",
//                "title" to "Herr"
//            )
//        )
//
//        contactService.uploadFile(file)
//        verify { contactDbClient.insertContactsFromFile(contactList) }
//    }

    @Test
    fun `returns failure if contact could not be saved`() {
        every {
            contactDbClient.saveContact(any())

        } throws NullPointerException()

        expectThat(
            contactService.saveContact(
                ContactRequest(
                    lastName = "Mustermann",
                    firstName = null,
                    title = "Herr",
                    address = null,
                    city = null,
                    company = null,
                    postalCode = null,
                    emailOne = null,
                    emailTwo = null,
                    phoneOne = null,
                    phoneTwo = null
                )
            )
        )
            .isLeft()
            .isA<Failure>()
    }

    @Test
    fun `returns failure if contact could not be updated`() {
        every {
            contactDbClient.updateContact(any())

        } throws NullPointerException()

        expectThat(
            contactService.updateContact(
                Contact(
                    lastName = "Mustermann",
                    firstName = null,
                    title = "Herr",
                    id = 2,
                    address = null,
                    city = null,
                    company = null,
                    postalCode = null,
                    emailOne = null,
                    emailTwo = null,
                    phoneOne = null,
                    phoneTwo = null
                )
            )
        )
            .isLeft()
            .isA<Failure>()
    }

    @Test
    fun `returns failure if contact could not be deleted`() {
        every {
            contactDbClient.deleteContact(any())

        } throws NullPointerException()

        expectThat(
            contactService.deleteContact(1)
        )
            .isLeft()
            .isA<Failure>()
    }
}