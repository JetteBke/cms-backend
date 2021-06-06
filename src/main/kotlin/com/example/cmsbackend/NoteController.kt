package com.example.cmsbackend

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(maxAge = 3600)
@RestController
class NoteController(
    val service: NoteService
) {
    @PostMapping("/cms/api/contact/{contactId}/notes/new", produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun saveNote(
        @RequestBody noteData: Map<String, String>,
        @PathVariable contactId: String
    ) {
        service.saveNote(noteData, contactId)
    }
}