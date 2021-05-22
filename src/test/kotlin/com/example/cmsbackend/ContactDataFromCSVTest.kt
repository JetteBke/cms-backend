package com.example.cmsbackend

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ContactDataFromCSVTest {
    @Test
    fun `returns a list of objects from csv`() {
        val csvFile = "src/test/kotlin/com/example/cmsbackend/dataForTests/test-data.csv"
        val loadedData = loadDataFromCsv(csvFile)
        val expectedData = listOf(
            mapOf("lastName" to "Meier", "firstName" to "Lena"),
            mapOf("lastName" to "Mustermann", "firstName" to "Max")
        )
        expectThat(loadedData).isEqualTo(expectedData)
    }
}