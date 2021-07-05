package com.example.cmsbackend

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

internal class NoteServiceTest {
    private val noteDbClient = mockk<NoteDbClient>()
    private val noteService = NoteService(noteDbClient)

    @Test
    fun `returns failure if note could not be saved`() {
        every {
            noteDbClient.saveNote(any())
        } throws NullPointerException()

        expectThat(noteService.saveNote(mapOf("firstName" to "Jana"), 2))
            .isLeft()
            .isA<Failure>()
    }

    @Test
    fun `returns notes for a contact`() {
        every {
            noteDbClient.getNotes(any())
        } returns listOf(Note(
            contactId = 2,
            text = "a testing note",
            updatedAt = 123456,
            createdAt = 1234563))

        expectThat(noteService.getNotes(2))
            .isRight()
            .isA<List<Note>>()
    }
}