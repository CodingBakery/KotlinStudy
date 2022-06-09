package com.brandon.funcprgm

fun main(){

    val source = "Hello Kotlin"
    val target = "Target"

    println(source.getLongString(target))
}

fun String.getLongString(target: String): String =
    if(this.length > target.length) this else target