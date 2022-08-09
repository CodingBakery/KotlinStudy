package com.brandon.funcprgm

fun pureAdd (x: Int, y:Int ) : Int {
    return x + y
}

val outer = 3
fun noPureAdd (x: Int, y:Int ) : Int {
    return x + y + outer // fun 은 return 으로~
}

fun main(){
    println("Pure Function : ${pureAdd(1,2)}")
    println("None Pure Function :  ${noPureAdd(1,2)}")
}
