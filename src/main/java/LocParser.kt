package CurrentWeather

import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.*


class LocParser {
    init {
        if (!File("loc.txt").exists()) {
            try {
                if (!File("loc.txt").createNewFile()) {
                    throw FileNotFoundException("Could not create file")
                }
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }
    }
        companion object {
            @JvmStatic
            fun location(): Array<String> {
                try {
                    val file = File("loc.txt")
                    val scanner = Scanner(file)
                    return arrayOf(scanner.nextLine(), scanner.nextLine())
                } catch (e: NoSuchElementException) {
                    return arrayOf("0", "0")
                }
            }
            @JvmStatic
            fun locationAsString(): String {
                try {
                    val file = File("loc.txt")
                    val stream = FileReader(file)
                    val list = stream.readLines()
                    return """
        Latitude: ${list[0]}
        Longitude: ${list[1]}
        Location: ${list[2]}
        """.trimIndent()
                }
                catch (e: Exception) {
                    return when (e){
                        is NoSuchElementException -> "No Location Specified"
                        is FileNotFoundException -> "File not found"
                        else -> throw e
                    }

                }
            }
        }
    }

