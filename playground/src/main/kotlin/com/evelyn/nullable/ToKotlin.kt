package com.evelyn.nullable

import java.lang.IllegalArgumentException

/**
 * NullCheck.java 를 코틀린으로 변환한 것.
 */
fun main() {

}

fun startsWithA1(str: String?): Boolean {
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("null이 들어왔습니다.")
}

fun startsWithA2(str: String?): Boolean? {
    return str?.startsWith("A")
}

fun startsWithA3(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}
