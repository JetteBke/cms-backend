package com.example.cmsbackend

import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(maxAge = 3600)
@RestController
class NoteController(
    val service: NoteService
) {
    @PostMapping("/cms/api/contact/{contactId}/notes/new", produces = ["application/json"])
    fun saveNote(
        @RequestBody noteData: Map<String, String>,
        @PathVariable contactId: String
    ): ResponseEntity<out Any> {
        return service.saveNote(noteData, contactId).fold(
            ifRight = { noContent().build() },
            ifLeft = { badRequest().build() }
        )
    }

    @GetMapping("/cms/api/contact/{contactId}/notes", produces = ["application/json"])
    fun getNotes(@PathVariable contactId: String): ResponseEntity<Any?> {
        return service.getNotes(contactId).fold(
            ifRight = { ResponseEntity(Gson().toJson(it), HttpStatus.OK) },
            ifLeft = { badRequest().build() }
        )
    }
}