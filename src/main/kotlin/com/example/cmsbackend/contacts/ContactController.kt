package com.example.cmsbackend.contacts

import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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

        val contact = service.getContact(contactId)
        return ResponseEntity(Gson().toJson(contact), HttpStatus.OK)
    }

    @PostMapping("/cms/api/contacts/new", produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun saveContact(
        @RequestBody contactData: Map<String, String>
    ) {
        service.saveContact(contactData)
    }

    @PutMapping("/cms/api/contacts/edit", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun updateContact(
        @RequestBody contactData: Map<String, String>
    ) {
        service.updateContact(contactData)
    }

    @DeleteMapping("/cms/api/contacts/{contactId}/delete", produces = ["application/json"])
    @ResponseStatus(HttpStatus.OK)
    fun deleteContact(@PathVariable contactId: Int) {
        service.deleteContact(contactId)
    }

    @PostMapping("/cms/api/fileUpload", consumes = ["multipart/form-data"])
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadFile(
        @RequestBody file: MultipartFile
    ) {
        service.uploadFile(file)
    }
}