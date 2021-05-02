package com.example.cmsbackend

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

internal class ContactTest {
    private val contactData = mapOf("id" to "2", "title" to "Frau", "lastName" to "Meister")

    @Test
    fun `should convert map to contact`() {
        val result = contactData.toContact()
        expectThat(result).isA<Contact>()
    }
}