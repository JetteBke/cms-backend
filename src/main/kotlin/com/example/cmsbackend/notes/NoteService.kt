package com.example.cmsbackend.notes

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.example.cmsbackend.Failure
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val ourLogger = KotlinLogging.logger {}

@Service
class NoteService(
    val dbClient: NoteDbClient
) {
    fun saveNote(noteData: Map<String, String>, contactId: Int): Either<Failure, Unit> =
        runCatching {
            val note = noteData.plus(mapOf("contactId" to contactId.toString())).toNote()
            ourLogger.info { "The following data will be inserted: $note" }
            dbClient.saveNote(note).right()
        }.getOrElse {
            ourLogger.info { "The following note could not be saved: $noteData" }
            Failure("Note could not be saved").left()
        }

    fun updateNote(noteData: Map<String, String>, contactId: Int): Either<Failure, Unit> =
        runCatching {
            val note = noteData.plus(mapOf("contactId" to contactId.toString())).toNoteWithId()
            ourLogger.info { "The note with id ${note.id} will be updated" }
            dbClient.updateNote(note).right()
        }.getOrElse {
            ourLogger.info { "The note could not be updated" }
            Failure("Note could not be updated").left()
        }

    fun getNotes(contactId: Int): Either<Failure, List<NoteWithId>> =
        runCatching {
            dbClient.getNotes(contactId).right()
        }.getOrElse {
            ourLogger.info { "Could not get notes for $contactId" }
            Failure("Could not get notes for $contactId").left()
        }

    fun deleteNote(noteId: Int): Either<Failure, Unit> =
        runCatching {
            ourLogger.info { "Removing note with id: $noteId" }
            return dbClient.deleteNote(noteId).right()
        }.getOrElse {
            ourLogger.info { "Could not delete note with id $noteId" }
            Failure("Could not delete note with id $noteId").left()
        }
}

