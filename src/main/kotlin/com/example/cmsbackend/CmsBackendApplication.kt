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

    val dbUrl = "jdbc:postgresql://localhost:5432/cms"
    val dbUser = "postgres"
    val dbPass = "password"
    Database.connect(dbUrl, driver = "org.postgresql.Driver", user = dbUser, password = dbPass)

    transaction {
        addLogger(StdOutSqlLogger)

        SchemaUtils.drop(ContactTable)
        SchemaUtils.create(ContactTable)

        ContactTable.insert {
            it[name] = "testing1"
        }

        ContactTable.insert {
            it[name] = "testing2"
        }

    }

    runApplication<CmsBackendApplication>(*args)
}
