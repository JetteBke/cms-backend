package com.example.cmsbackend

import com.example.cmsbackend.db.ContactTable
import com.example.cmsbackend.db.NoteTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
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
        SchemaUtils.create(ContactTable)
        SchemaUtils.create(NoteTable)
    }

    runApplication<CmsBackendApplication>(*args)
}
