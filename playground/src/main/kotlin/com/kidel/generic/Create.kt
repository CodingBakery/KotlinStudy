package com.kidel.generic

fun main() {

    println("#### 컬렉션 생성 ####")
    val names1 = listOf("Bob", "John", "May", "Kate")
    val names2 = listOf<String>()   // 인자가 없을 때는 타입추론이 안 되므로~

    val names3 : MutableList<String> = mutableListOf()
    val names4 = mutableListOf<String>()    // 자바에서처럼 제네릭 이전 버전과의 호환을 위한 raw 타입이 없음. (코틀린은 이미 제네릭 개념을 아니까) -> 이펙자바 아이템26.

    println("#### 제네릭 함수 ####")
    names1.slice(1..2)
    names1.slice<String>(1..2)

    println("#### 제네릭 고차 함수 ####")
    val numbers = listOf(1, 55, 7, 90, 2)
    val selectedNumbers = mutableListOf(2, 55)
    println(selectedNumbers.filter { it !in numbers})

}