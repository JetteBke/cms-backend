package com.example.cmsbackend.contacts

import com.google.gson.Gson
import java.net.URI
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@CrossOrigin(maxAge = 3600)
@RestController
class ContactController(
    val service: ContactService
) {
    @GetMapping("/cms/api/contacts", produces = ["application/json"])
    @ResponseBody
    fun getContacts(): ResponseEntity<Any?> {
        val list = service.getContacts()
        return ResponseEntity(Gson().toJson(list), HttpStatus.OK)
    }

    @GetMapping("/cms/api/contacts/{contactId}", produces = ["application/json"])
    @ResponseBody
    fun getContact(@PathVariable contactId: Int): ResponseEntity<Any?> {
        return service.getContact(contactId).fold(
            ifRight = { ResponseEntity(Gson().toJson(it), HttpStatus.OK) },
            ifLeft = { ResponseEntity.badRequest().build() }
        )
    }

    @PostMapping("/cms/api/contacts/new", produces = ["application/json"])
    fun saveContact(
        @RequestBody contact: ContactRequest
    ): ResponseEntity<out Any> {
        return service.saveContact(contact).fold(
            ifRight = { ResponseEntity.created(URI("")).build() },
            ifLeft = { ResponseEntity.badRequest().build() }
        )
    }

    @PutMapping("/cms/api/contacts/edit", produces = ["application/json"])
    fun updateContact(
        @RequestBody contact: Contact
    ): ResponseEntity<out Any> {
        return service.updateContact(contact).fold(
            ifRight = { ResponseEntity.noContent().build() },
            ifLeft = { ResponseEntity.badRequest().build() }
        )
    }

    @DeleteMapping("/cms/api/contacts/{contactId}/delete", produces = ["application/json"])
    fun deleteContact(@PathVariable contactId: Int): ResponseEntity<out Any> {
       return service.deleteContact(contactId).fold(
           ifRight = { ResponseEntity.noContent().build() },
           ifLeft = { ResponseEntity.badRequest().build() }
       )
    }

    @PostMapping("/cms/api/fileUpload", consumes = ["multipart/form-data"])
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadFile(
        @RequestBody file: MultipartFile
    ) {
        service.uploadFile(file)
    }
}