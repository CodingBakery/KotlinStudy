package com.brandon.funcprgm

fun main(){
    val result1 = callByValue1(lambda())
    println(result1)
    val result2 = callByValue2(otherlambda)
    println(result2)
}

fun callByValue1(b: Boolean) : Boolean{
    println("CallByValue1 Func")
    return b
}

val lambda: () -> Boolean = {
    println("Lamda1 Func")
    true
}

fun callByValue2(b: ()-> Boolean) : Boolean{
    println("CallByValue2 Func")
    return b()
}

val otherlambda: () -> Boolean = {
    println("otherlambda2 Func")
    true
}

