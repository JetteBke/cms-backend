package com.example.cmsbackend.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ContactTable : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
//    val title = varchar("title", 50)
//    val firstName = varchar("firstName", 50)
//    val lastName = varchar("lastName", 50)
//    val street = varchar("street", 50)
//    val postalCode = varchar("postalCode", 50)
//    val city = varchar("city", 50)
//    val phoneOne = varchar("phoneOne", 50)
//    val phoneTwo = varchar("phoneTwo", 50)
//    val emailOne = varchar("emailOne", 50)
//    val emailTwo = varchar("emailTwo", 50)

    fun getAll(): List<Any> = transaction {
        ContactTable.selectAll().map { it[name] }
    }
}