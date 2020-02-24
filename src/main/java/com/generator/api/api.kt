package com.generator.api

import com.generator.entity.data.Data
import com.generator.entity.data.Residence
import com.generator.entity.data.State
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files

val gson: Gson = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .create()

const val FILE_PATH = "states-cities-zip-codes.json"

/**
 * Reads compressed data from zip_codes json file
 */
fun parseStateCitiesZip(gson: Gson): Map<String, State> {
    val path = File("zip_codes.json").toPath()
    val reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)
    val type = object: TypeToken<Map<String, State>>() {}.type
    return gson.fromJson(reader, type)
}

/**
 * Serializes list of objects Residence in json file out.json
 */
fun generateOutputData(gson: Gson, residences: List<Residence>) {
    val file = File("out.json")
    if (!file.exists()) file.createNewFile()
    val result: String = gson.toJson(residences)
    file.writeText(result, StandardCharsets.UTF_8)
}

/**
 * Reads information from json file with list of objects Data
 */
fun parseStateCityZipJson(): List<Data> {
    val path = File(FILE_PATH).toPath()
    val reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)
    val type = object: TypeToken<List<Data>>() {}.type
    return gson.fromJson(reader, type)
}


/**
 * Transforms data from zip_codes.json into list of objects Data adn writes the list into json file
 */
fun generateStateCityZipJson(states: Map<String, State>) {
    val data = mutableListOf<Data>()
    states.asSequence()
            .forEach {
                val stateAbbreviation = it.key
                val state = it.value
                val stateName = state.name
                val cities = state.cities
                cities.entries
                        .asSequence()
                        .forEach { city ->
                            val o = Data()
                            o.abbreviation = stateAbbreviation
                            o.state = stateName
                            o.city = city.key
                            o.zip = city.value
                            data.add(o)
                        }
            }
    val file = File(FILE_PATH)
    if (!file.exists()) file.createNewFile()
    val result: String = gson.toJson(data)
    file.writeText(result, StandardCharsets.UTF_8)
}

/**
 * Returns random Data object from list
 */
fun getRandomState(data: List<Data>): Data {
    val index = (Math.random() * data.size).toInt()
    return data[index]
}

/**
 * Returns random zip code from list
 */
fun getRandomZip(list: List<Int>): Int {
    val index = (Math.random() * list.size).toInt()
    return list[index]
}