package com.example.cmsbackend
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.InputStream

fun loadDataFromCsv(stream: InputStream): List<Map<String, String>> {
    val reader = csvReader {
        delimiter = ';'
    }
    return reader.readAllWithHeader(stream)
}