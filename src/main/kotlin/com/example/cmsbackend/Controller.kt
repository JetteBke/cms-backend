package com.example.cmsbackend

import com.example.cmsbackend.db.ContactTable
import com.google.gson.Gson
import org.springframework.hateoas.MediaTypes
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin(maxAge = 3600)
@RestController
class AppsController(
) {
    @GetMapping("/hello", produces = [MediaTypes.HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun helloWorld(): ResponseEntity<Any?> {

        val list = ContactTable.getAll()
        return ResponseEntity(Gson().toJson(list), HttpStatus.OK)
    }
}