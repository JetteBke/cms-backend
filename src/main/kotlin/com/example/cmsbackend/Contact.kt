package com.example.cmsbackend

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Contact(
    val id: Int?,
    val title: String,
    val firstName: String? = null,
    val lastName: String,
    val address: String? = null,
    val postalCode: Int? = null,
    val city: String? = null,
    val phoneOne: Int? = null,
    val phoneTwo: Int? = null,
    val emailOne: String? = null,
    val emailTwo: String? = null
)

fun Map<String, String>.toContact(): Contact {
    return Contact(
        id = this["id"]?.toInt(),
        title = this["title"].toString(),
        firstName = this["firstName"],
        lastName = this["lastName"].toString(),
        address = this["address"],
        postalCode = this["postalCode"]?.toInt(),
        city = this["city"],
        phoneOne = this["phoneOne"]?.toInt(),
        phoneTwo = this["phoneTwo"]?.toInt(),
        emailOne = this["emailOne"],
        emailTwo = this["emailTwo"]
    )
}