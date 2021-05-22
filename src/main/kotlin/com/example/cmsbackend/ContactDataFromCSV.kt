package com.example.cmsbackend

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File
import mu.KotlinLogging

private val ourLogger = KotlinLogging.logger {}

fun loadDataFromCsv(fileLocation: String): List<Map<String, String>> {
    ourLogger.info { "Reading data from $fileLocation" }
    val file = File(fileLocation)
    val reader = csvReader {
        delimiter = ';'
    }
    val rows: List<Map<String, String>> = reader.readAllWithHeader(file)
    ourLogger.info { "Successfully read data from $fileLocation" }
    return rows
}