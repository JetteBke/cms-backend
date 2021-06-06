package com.example.cmsbackend

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Note(
    val createdAt: Long,
    val updatedAt: Long?,
    val text: String,
    val contactId: Int
)

fun Map<String, String>.toNote(): Note {
    return Note(
        createdAt = this["createdAt"]!!.toLong(),
        updatedAt = this["updatedAt"]!!.toLong(),
        text = this["text"]!!.toString(),
        contactId = this["contactId"]!!.toInt()
    )
}