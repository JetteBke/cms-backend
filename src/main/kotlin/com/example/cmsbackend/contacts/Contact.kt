package com.example.cmsbackend.contacts

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ContactRequest(
    val title: String,
    val firstName: String?,
    val lastName: String,
    val company: String?,
    val address: String?,
    val postalCode: Int?,
    val city: String?,
    val phoneOne: Int?,
    val phoneTwo: Int?,
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
    val postalCode: Int?,
    val city: String?,
    val phoneOne: Int?,
    val phoneTwo: Int?,
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
        postalCode = this["postalCode"]?.toInt(),
        city = this["city"],
        phoneOne = this["phoneOne"]?.toInt(),
        phoneTwo = this["phoneTwo"]?.toInt(),
        emailOne = this["emailOne"],
        emailTwo = this["emailTwo"],
        oldNote = this["oldNote"]
    )
}