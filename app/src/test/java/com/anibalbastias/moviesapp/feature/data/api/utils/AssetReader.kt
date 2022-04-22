package com.anibalbastias.moviesapp.feature.data.api.utils

import java.io.File
import java.io.IOException
import java.util.*

class AssetReader {
    fun loadResource(path: String): String {
        val sb = StringBuilder()
        val classLoader = javaClass.classLoader ?: throw IllegalStateException("Failed to acquire classloader from class")
        val file = File(classLoader.getResource(path).file)

        val scanner = Scanner(file)
        try {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            scanner.close()
        }
        return sb.toString()
    }
}