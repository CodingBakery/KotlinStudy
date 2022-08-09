package com.evelyn.collection

fun main() {
    // 불변형 List - listOf()
    // 불변형이기에 추가/삭제 메서드는 없음.
    var numbers: List<Int> = listOf(1, 2, 3, 4, 5)

    // 제네릭 생략하면 <Any>가 되고, 서로 다른 자료형을 담을 수 있다.
    var mixedTypes = listOf("hello", 1, 3.14, 's')

    // 컬렉션에 .indices 멤버를 추가하여 인덱스 접근
    for (index in mixedTypes.indices) {
        println("mixedTypes[$index] = ${mixedTypes[index]}")
    }

    // 빈 List 생성 - 제네릭 선언 필수
    var emptyList: List<String> = emptyList()

    // null을 제외한 요소로 List 생성
    var nonNullList: List<Int> = listOfNotNull(1, 24, 2, null, 5, null)
    println(nonNullList)

    // 가변형 List - mutableListOf(), arrayListOf()
    val stringList: ArrayList<String> = arrayListOf("Hello", "World", "Evelyn")
    stringList.add("Bye") // 추가
    stringList.remove("Hello") // 삭제
    println(stringList)

    val mutableList: MutableList<String> = mutableListOf("apple", "banana", "cherry")
    mutableList.add("mango")
    mutableList.removeAt(1)
    mutableList[0] = "grape"
    println(mutableList)

    // 자료형 혼합
    val mutableListMixed = mutableListOf(1, 1.5, "apple", 'r')
    println(mutableListMixed)

    // 불변형 List를 가변형으로 변경
    val names: List<String> = listOf("one", "two", "three")
    val mutableNames = names.toMutableList() // 새로운 가변형 List가 만들어짐
    mutableNames.add("four")
    println("names : $names")
    println("mutableNames : $mutableNames")
}