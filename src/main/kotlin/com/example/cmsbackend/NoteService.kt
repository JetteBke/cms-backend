package com.example.cmsbackend

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.lang.Integer.parseInt
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val ourLogger = KotlinLogging.logger {}

@Service
class NoteService(
    val dbClient: NoteDbClient
) {
    fun saveNote(noteData: Map<String, String>, contactId: String): Either<Failure, Unit> =
        runCatching {
            ourLogger.info { "The following data will be converted to note: $noteData" }
            val note = noteData.plus(mapOf("contactId" to contactId)).toNote()
            ourLogger.info { "The following data will be inserted: $note" }
            dbClient.saveNote(note).right()
        }.getOrElse {
            ourLogger.info { "The following note could not be saved: $noteData" }
            Failure("Note could not be saved").left()
        }

    fun getNotes(contactId: String): Either<Failure, List<Note>> =
        runCatching {
            dbClient.getNotes(parseInt(contactId)).right()
        }.getOrElse {
            Failure("Could not get notes for $contactId").left()
        }
}

