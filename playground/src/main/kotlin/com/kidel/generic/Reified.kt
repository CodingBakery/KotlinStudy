package com.kidel.generic

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlin.reflect.KClass

fun main() {
    println("#### 예제1: 타입을 넘겨받으려면 ####")
    fun <T: Any> String.toKotlinObject(c: KClass<T>): T {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(this, c.java)
    }

//    inline fun <reified T: Any> String.toKotlinObjectReified(): T {
//        val mapper = jacksonObjectMapper()
//        return mapper.readValue(this, T::class.java)
//    }
}