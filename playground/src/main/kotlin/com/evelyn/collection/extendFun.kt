package com.evelyn.collection

fun main() {
    val list = listOf(1, 2, 3, 4, 5, 6)

    // forEach(): 각 요소를 람다식으로 처리
    list.forEach { i -> print(i) }
    println()
    list.forEachIndexed { index, i -> println("index[$index]: $i") } // 인덱스 포함

    // onEach(): 각 요소를 람다식으로 처리 후 컬렉션으로 반환
    val returnedList = list.onEach { print(it) }

    val cities = listOf("Seoul", "Tokyo", "London")
    // map()
    val mapList = cities.map { city -> city.toUpperCase() }
    println(mapList)

    // mapNotNull(): null을 제외하고 식을 적용해 새로운 컬렉션 반환
    val notnullList = cities.mapNotNull { city -> if(city.length <=5) city else null }
    println(notnullList) // [Seoul, Tokyo]


    // groupBy(): 주어진 함수의 결과에 따라 그룹화하여 map으로 반환
    val grpMap = cities.groupBy { city -> if (city.length <= 5) "A" else "B" }
    println(grpMap) // {A=[Seoul, Tokyo], B=[London]}

    // filter(): 식에따라 요소를 골라내기
    cities.filter{ city -> city.length <= 5}
        .forEach{ println(it) }

    val cities2 = listOf("Seoul", "Tokyo", "London", "NYC", "Singapore")
    println()
    // Seoul
    println(cities2.first())

    // Singapore
    println(cities2.last())

    // London
    println(cities2.first { it.length > 5 })

    // null
    println(cities2.firstOrNull { it.length > 10 })

    val name = listOf("KIM","CHOI","HAN","LEE")
    // 문자열 형태로 변환하여 하나의 문자열로 합친다.
    println(name.joinToString())
    // 구분자 지정
    println(name.joinToString(" "))
    // 문자열의 형태 변환 가능
    println(name.joinToString { it -> "$it 입니다." })


    var ddd = cities.stream()
        .map { city -> city.length }

}