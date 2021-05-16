package com.example.cmsbackend.db

import org.jetbrains.exposed.sql.Table

object ContactTable : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 50)
    val firstName = varchar("firstName", 50).nullable()
    val lastName = varchar("lastName", 50)
    val company = varchar("company", 50).nullable()
    val address = varchar("street", 70).nullable()
    val postalCode = integer("postalCode").nullable()
    val city = varchar("city", 50).nullable()
    val phoneOne = integer("phoneOne").nullable()
    val phoneTwo = integer("phoneTwo").nullable()
    val emailOne = varchar("emailOne", 50).nullable()
    val emailTwo = varchar("emailTwo", 50).nullable()
}