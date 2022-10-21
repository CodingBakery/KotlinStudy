package com.kidel.expend

/** 확장 프로퍼티 */
val List<String>.lastLength
    get() = get(lastIndex).length

fun main() {
    val strList = listOf("Hello", "my", "name", "is", "000")
    println("LAST LENGTH => ${strList.lastLength}")
}
