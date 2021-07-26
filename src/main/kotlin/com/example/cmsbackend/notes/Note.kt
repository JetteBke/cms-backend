package com.example.cmsbackend.notes

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class NoteRequest(
    val createdAt: Long,
    val updatedAt: Long?,
    val text: String,
    val contactId: Int
)

data class NoteWithId(
    val id: Int,
    val createdAt: Long,
    val updatedAt: Long?,
    val text: String,
    val contactId: Int
)

fun Map<String, String>.toNote(): NoteRequest {
    return NoteRequest(
        createdAt = this["createdAt"]!!.toLong(),
        updatedAt = this["updatedAt"]?.toLong(),
        text = this["text"]!!.toString(),
        contactId = this["contactId"]!!.toInt()
    )
}

fun Map<String, String>.toNoteWithId(): NoteWithId {
    return NoteWithId(
        id = this["id"]!!.toInt(),
        createdAt = this["createdAt"]!!.toLong(),
        updatedAt = this["updatedAt"]?.toLong(),
        text = this["text"]!!.toString(),
        contactId = this["contactId"]!!.toInt()
    )
}