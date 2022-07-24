package com.evelyn.collection

fun main() {
    // lazy 가 적용되지 않는 확장함수
    println("##### 확장함수 #####")
    listOf(1, 2, 3, 4, 5, 6)
        .filter {
            println("filter: $it")
            it < 3
        }
        .map {
            println("map: $it")
            it * it
        }
        .any {
            println("any: $it")
            it > 2
        }
    //<결과>
    //filter: 1
    //filter: 2
    //filter: 3
    //filter: 4
    //filter: 5
    //filter: 6
    //map: 1
    //map: 2
    //any: 1
    //any: 4

    println()
    println("##### 시퀀스 #####")
    // lazy가 적용되는 시퀀스
    listOf(1, 2, 3, 4, 5, 6)
        .asSequence()   // 이 부분을 추가해서 Sequence 로 변경
        .filter {
            println("filter: $it")
            it < 3
        }
        .map {
            println("map: $it")
            it * it
        }
        .any {
            println("any: $it")
            it > 2
        }

    //<결과>
    //filter: 1
    //map: 1
    //any: 1
    //filter: 2
    //map: 2
    //any: 4
}