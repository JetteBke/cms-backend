package com.example.cmsbackend.notes

import com.example.cmsbackend.Failure
import com.example.cmsbackend.isLeft
import com.example.cmsbackend.isRight
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
    fun `returns failure if note could not be updated`() {
        every {
            noteDbClient.updateNote(any())
        } throws NullPointerException()

        expectThat(noteService.updateNote(mapOf("text" to "a testing note"), 2))
            .isLeft()
            .isA<Failure>()
    }

    @Test
    fun `returns notes for a contact`() {
        every {
            noteDbClient.getNotes(any())
        } returns listOf(
            NoteWithId(
                id = 1,
                contactId = 2,
                text = "a testing note",
                updatedAt = 123456,
                createdAt = 1234563
            )
        )

        expectThat(noteService.getNotes(2))
            .isRight()
            .isA<List<Note>>()
    }
}