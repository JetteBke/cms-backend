package com.example.cmsbackend.contacts

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Contact(
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
    val emailTwo: String?
)

data class ContactWithId(
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
    val emailTwo: String?
)

fun Map<String, String>.toContactWithId(): ContactWithId {
    return ContactWithId(
        id = this["id"]!!.toInt(),
        title = this["title"].toString(),
        firstName = this["firstName"],
        lastName = this["lastName"].toString(),
        company = this["company"],
        address = this["address"],
        postalCode = this["postalCode"]?.toInt(),
        city = this["city"],
        phoneOne = this["phoneOne"]?.toInt(),
        phoneTwo = this["phoneTwo"]?.toInt(),
        emailOne = this["emailOne"],
        emailTwo = this["emailTwo"]
    )
}

fun Map<String, String>.toContact(): Contact {
    return Contact(
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
        emailTwo = this["emailTwo"]
    )
}