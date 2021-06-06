package com.example.cmsbackend

import com.example.cmsbackend.db.NoteTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class NoteDbClient {
    fun saveNote(note: Note) {
        transaction {
            NoteTable.insert {
                it[createdAt] = note.createdAt
                it[updatedAt] = note.updatedAt
                it[text] = note.text
                it[contactId] = note.contactId
            }
        }
    }
}