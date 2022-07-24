package com.evelyn.collection

fun main() {
    // 불변형 Set - setOf()
    val intSet: Set<Int> = setOf(1, 5, 5)
    val mixedTypesSet = setOf("Hello", 5, 3.14, 'x') // 자료형 혼합
    // 불변형은 add/remove 가 없다

    println("intSet : $intSet")
    println("mixedTypeSet : $mixedTypesSet")


    // 가변형 Set - mutableSetOf()
    // 이밖에도 hashSetOf, linkedSetOf, sortedSetOf 헬퍼 함수 존재.
    val animals = mutableSetOf("dog", "cat", "rabbit", "lion")
    animals.add("turtle")
    animals.remove("rabbit")
    println("animals : $animals")
//    animals.clear()

    //불변형 Map
    val langMap: Map<Int, String> = mapOf(11 to "Java", 22 to "Kotlin", 33 to "C++")
    for ((key, value) in langMap) {
        println("key=$key, value=$value")
    }
    println("langMap[22] = ${langMap[22]}")
    println("langMap.get(22) = ${langMap.get(22)}")
    println("langMap.keys = ${langMap.keys}")

    // 가변형 Map
    val capitalCityMap: MutableMap<String, String>
            = mutableMapOf("Korea" to "Seoul", "China" to "Beijing", "Japan" to "Tokyo")
    capitalCityMap.put("UK", "London")
    capitalCityMap.remove("China")
    println("capitalCityMap : $capitalCityMap")


}