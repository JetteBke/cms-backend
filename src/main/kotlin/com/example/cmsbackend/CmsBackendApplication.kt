package com.example.cmsbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CmsBackendApplication

fun main(args: Array<String>) {
    runApplication<CmsBackendApplication>(*args)
}
