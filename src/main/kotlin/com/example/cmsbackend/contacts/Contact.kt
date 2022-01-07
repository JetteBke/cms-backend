package com.example.cmsbackend.contacts

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ContactRequest(
    val title: String,
    val firstName: String?,
    val lastName: String,
    val company: String?,
    val address: String?,
    val postalCode: String?,
    val city: String?,
    val phoneOne: String?,
    val phoneTwo: String?,
    val emailOne: String?,
    val emailTwo: String?,
    val oldNote: String?
)

data class Contact(
    val id: Int,
    val title: String,
    val firstName: String?,
    val lastName: String,
    val company: String?,
    val address: String?,
    val postalCode: String?,
    val city: String?,
    val phoneOne: String?,
    val phoneTwo: String?,
    val emailOne: String?,
    val emailTwo: String?,
    val oldNote: String?
)

fun Map<String, String>.toContactRequest(): ContactRequest {
    return ContactRequest(
        title = this["title"]!!,
        firstName = this["firstName"],
        lastName = this["lastName"]!!,
        company = this["company"],
        address = this["address"],
        postalCode = this["postalCode"],
        city = this["city"],
        phoneOne = this["phoneOne"],
        phoneTwo = this["phoneTwo"],
        emailOne = this["emailOne"],
        emailTwo = this["emailTwo"],
        oldNote = this["oldNote"]
    )
}