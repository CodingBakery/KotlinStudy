package com.kidel.exception

fun main() {
    val person = Person(age=1)
}

fun test(): Nothing {
    throw Exception("")
}

fun test2(): Unit {
    println("hello")
}

class Person(val name: String = "name", val age: Int)  {

}