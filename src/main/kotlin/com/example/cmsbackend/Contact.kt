package com.example.cmsbackend

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.sql.Time

@JsonIgnoreProperties(ignoreUnknown = true)
data class Contact(
    val id: Int,
    val title: String,
    val firstName: String?,
    val lastName: String,
    val address: String?,
    val postalCode: Int?,
    val city: String?,
    val phoneOne: Int?,
    val phoneTwo: Int?,
    val emailOne: String?,
    val emailTwo: String?
)

fun Map<String, String>.toContact(): Contact {
    return Contact(
        id = this["id"]!!.toInt(),
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