package com.example.cmsbackend

import com.example.cmsbackend.db.ContactTable
import com.example.cmsbackend.db.NoteTable
import javax.annotation.PostConstruct
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example.cmsbackend"])
class CmsBackendApplication {
    @Value("\${spring.datasource.url}")
    private lateinit var url: String
    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var driver: String
    @Value("\${spring.datasource.username}")
    private lateinit var username: String
    @Value("\${spring.datasource.password}")
    private lateinit var password: String

    @PostConstruct
    fun init() {
        Database.connect(url, driver = driver, user = username, password = password)

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(NoteTable)
            SchemaUtils.createMissingTablesAndColumns(ContactTable)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<CmsBackendApplication>(*args)
}
