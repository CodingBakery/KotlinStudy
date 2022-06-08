package com.brandon.funcprgm

fun main() {
    val multi_newLine: (Int, Int) -> Int = {x: Int, y: Int ->
        println("x * y")
        x * y}
    // 람다함수에서 NewLine (Enter) 값을 기준으로 맨 아래가 return

    val multi1 : (Int, Int) -> Int = {x: Int, y: Int -> x * y}
    val multi1_1 : (Int, Int) -> Int = {x , y -> x * y}
    val multi2 = {x: Int, y: Int -> x * y}

    // val multi2err = {x, y -> x * y} // ERROR
    // 타입은 앞쪽 : 로 선언하든, 뒤쪽에 선언하든 해야함!

    println("${multi_newLine(3,4)}")
}