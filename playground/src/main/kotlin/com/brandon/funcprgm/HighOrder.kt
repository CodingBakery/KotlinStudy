package com.brandon.funcprgm

import java.util.concurrent.locks.ReentrantLock

fun main(){

    val multi1 : (Int, Int) -> Int = {x: Int, y: Int -> x * y}
    val multi1_1 : (Int, Int) -> Int = {x , y -> x * y}
    val multi2 = {x: Int, y: Int -> x * y}

    println(simpleHigherOrderFunction({ x, y -> x + y }, 10, 20)) // result: 30

    println("multi1 : ${funcParam( 10, 20, multi1)}") // 람다 함수 주입 시에는 그냥
    println("multi1_1 : ${funcParam( 10, 20, multi1_1)}") // 람다 함수 주입 시에는 그냥
    println("multi2 : ${funcParam( 10, 20, multi2)}") // 람다 함수 주입 시에는 그냥

    println("::minusFunc : ${funcParam( 10, 20, ::minusFunc)}") // fun 주입시에는 :: FunctionReference ~
}


fun minusFunc(a: Int, b: Int) = a-b

fun simpleHigherOrderFunction(sum: (Int, Int) -> Int, a: Int, b: Int): Int = sum(a, b)

fun funcParam(a: Int, b: Int, c:(Int, Int) -> Int) : Int {
    return c(a,b)
}
