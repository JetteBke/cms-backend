package com.example.cmsbackend

import com.google.gson.Gson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@CrossOrigin(maxAge = 3600)
@RestController
class AppsController(
    val service: ContactService
) {
    @GetMapping("/cms/api/contacts", produces = ["application/json"])
    @ResponseBody
    fun getContacts(): ResponseEntity<Any?> {

        val list = service.getContacts()
        return ResponseEntity(Gson().toJson(list), HttpStatus.OK)
    }

    @PostMapping("/cms/api/contacts/new", produces = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun saveContact(
        @RequestParam contactData: Map<String, String>
    ) {
        service.saveContact(contactData)
    }
}