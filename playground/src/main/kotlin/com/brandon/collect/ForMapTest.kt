package com.brandon.collect

import kotlinx.coroutines.flow.merge

class ForMapTest {
}

data class Person(val name: String, val age: Int)

fun main(args: Array<String>){

    val myList = listOf(Person("a", 10),
        Person("b", 12),
        Person("c", 15))


    val myMap1 = myList.map { it.name to it.age } // Convert Pair
    val myMap = myList.map { it.name to it.age }.toMap()
    println(myMap1.get(0).javaClass.name)
    println(myMap)



    //declaring a map of integer to string
    val diffMap = mapOf("a" to 100, "d" to 200 , "g" to 300, "f" to 400, "c" to 500)
    println("Map Entries : "+diffMap)
    println("Map Keys: "+diffMap.keys )
    println("Map Values: "+diffMap.values )


    val result = myMap.toMutableMap().apply {
        diffMap.forEach {
            (k,v)-> merge(k,v) { oldVal, newVale -> oldVal}
        }
    }
    println("Fin : "+result)

    val tempReturn = myMap.map { (k, v) -> Pair(k, 1) }.toMap();
    val result2 = tempReturn.toMutableMap().apply {
        diffMap.forEach { (k, v) ->
            merge(k, 1) { oldVal: Int, newVale: Int -> oldVal + 1 }
        }
    }
    println("Fin2 : "+result2)

//    val json = js("{}")               // 1
//    json.name = "Jane"                // 2
//    json.hobby = "movies"
//
//    println(JSON.stringify(json))
}