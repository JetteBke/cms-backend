package com.example.cmsbackend.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ContactTable : Table() {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 50)
    val firstName = varchar("firstName", 50).nullable()
    val lastName = varchar("lastName", 50)
    val address = varchar("street", 70).nullable()
    val postalCode = integer("postalCode").nullable()
    val city = varchar("city", 50).nullable()
    val phoneOne = integer("phoneOne").nullable()
    val phoneTwo = integer("phoneTwo").nullable()
    val emailOne = varchar("emailOne", 50).nullable()
    val emailTwo = varchar("emailTwo", 50).nullable()

    fun getAll(): List<Any> = transaction {
        ContactTable.selectAll().map {
            mapOf(
                "id" to it[ContactTable.id],
                "title" to it[title],
                "firstName" to it[firstName],
                "lastName" to it[lastName],
                "address" to it[address],
                "postalCode" to it[postalCode],
                "city" to it[city],
                "phoneOne" to it[phoneOne],
                "phoneTwo" to it[phoneTwo],
                "emailOne" to it[emailOne],
                "emailTwo" to it[emailTwo]
            )
        }
    }
}