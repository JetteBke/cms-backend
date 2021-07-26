package com.example.cmsbackend.notes

import com.google.gson.Gson
import java.net.URI
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.badRequest
import org.springframework.http.ResponseEntity.created
import org.springframework.http.ResponseEntity.noContent
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
        @PathVariable contactId: Int
    ): ResponseEntity<out Any> {
        return service.saveNote(noteData, contactId).fold(
            ifRight = { created(URI("")).build() },
            ifLeft = { badRequest().build() }
        )
    }

    @PutMapping("/cms/api/contact/{contactId}/notes/edit", produces = ["application/json"])
    fun updateNote(
        @RequestBody noteData: Map<String, String>,
        @PathVariable contactId: Int
    ): ResponseEntity<out Any> {
        return service.updateNote(noteData, contactId).fold(
            ifRight = { noContent().build() },
            ifLeft = { badRequest().build() }
        )
    }

    @GetMapping("/cms/api/contact/{contactId}/notes", produces = ["application/json"])
    fun getNotes(@PathVariable contactId: Int): ResponseEntity<Any?> {
        return service.getNotes(contactId).fold(
            ifRight = { ResponseEntity(Gson().toJson(it), HttpStatus.OK) },
            ifLeft = { badRequest().build() }
        )
    }

    @DeleteMapping("/cms/api/notes/{noteId}/delete", produces = ["application/json"])
    fun deleteNote(@PathVariable noteId: Int): ResponseEntity<out Any> {
        return service.deleteNote(noteId).fold(
            ifRight = { noContent().build() },
            ifLeft = { badRequest().build() }
        )
    }
}