package com.example.cmsbackend

import com.example.cmsbackend.db.ContactTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CmsBackendApplication

fun main(args: Array<String>) {

    val dbUrl = "jdbc:postgresql://db:5432/cms"
    val dbUser = "postgres"
    val dbPass = "password"
    val dbDriver = "org.postgresql.Driver"
    Database.connect(dbUrl, driver = dbDriver, user = dbUser, password = dbPass)

    transaction {
        addLogger(StdOutSqlLogger)

//        shall be removed after first run
        SchemaUtils.create(ContactTable)

//        shall be removed after first run
        ContactTable.insert {
            it[lastName] = "Meier"
            it[firstName] = "Berta"
            it[title] = "Frau"
        }
    }

    runApplication<CmsBackendApplication>(*args)
}
