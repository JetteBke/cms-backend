package com.example.cmsbackend.contacts

import java.io.File
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ContactDataFromCSVTest {
    @Test
    fun `returns a list of objects from csv`() {
        val file = File("src/test/kotlin/com/example/cmsbackend/dataForTests/test-data.csv")
        val input = file.inputStream()
        val expectedData = listOf(
            mapOf("lastName" to "Meier", "firstName" to "Lena"),
            mapOf("lastName" to "Mustermann", "firstName" to "Max")
        )
        val loadedData = loadDataFromCsv(input)

        expectThat(loadedData).isEqualTo(expectedData)
    }
}