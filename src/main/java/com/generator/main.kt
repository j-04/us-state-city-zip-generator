package com.generator

import com.generator.api.generateOutputData
import com.generator.api.getRandomState
import com.generator.api.getRandomZip
import com.generator.api.parseStateCityZipJson
import com.generator.entity.data.Data
import com.generator.entity.data.Residence
import com.google.gson.Gson
import com.google.gson.GsonBuilder

var states: List<Data> = parseStateCityZipJson()

fun main(args: Array<String>) {
    generateResidences()
}

fun generateResidences() {
    val gson: Gson = GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .create()

    val residences: MutableList<Residence> = mutableListOf()


    println("--------------------------")
    print("Enter quantity of states: ")
    var quantity = readLine()
    println("--------------------------")
    if (quantity == null) quantity = ""
    if (quantity.contains("""^[0-9]+$""".toRegex())) {
        println("-> Generating data...")
        val q = quantity.toInt()
        for (i in 0 until q) {
            val o = getRandomState(states)
            val zip = getRandomZip(o.zip!!)
            residences.add(Residence(state = o.state, state_abbreviation = o.abbreviation, city = o.city, zip = zip))
        }
        println("-> Data successfully generated!")
        println("-> Serializing data...")
        generateOutputData(gson, residences)
        println("-> Data successfully serialized!")
    } else {
        println("-------------------------")
        println("Wrong parameter!")
    }
    println("-------------------------")
}

