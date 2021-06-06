package com.example.cmsbackend

import mu.KotlinLogging
import org.springframework.stereotype.Service

private val ourLogger = KotlinLogging.logger {}

@Service
class NoteService(
    val dbClient: NoteDbClient
) {
    fun saveNote(noteData: Map<String, String>, contactId: String) {
        ourLogger.info { "The following data will be converted to note: $noteData" }
        val note = noteData.plus(mapOf("contactId" to contactId)).toNote()
        ourLogger.info { "The following data will be inserted $note" }
        dbClient.saveNote(note)
    }
}
