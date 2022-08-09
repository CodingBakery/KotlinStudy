package com.brandon.reflection

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

data class Person (val name: String, val age: Int) {
    fun printAllProps() {
        println("name: ${name}, age: $age")
    }
}

fun main() {
    // 클래스 정보 가져오기
    val personClass: KClass<Person> = Person::class
    println(personClass.simpleName) // Person
    personClass.java.methods.forEach(::println)
    println()
    personClass.memberProperties.forEach(::println)
    println()

    // 인스턴스 생성 & 메서드 호출
    val constructor = personClass.primaryConstructor
    val params = listOf("Yoon", 10)
    val personInstance = constructor?.call(*params.toTypedArray())
    // vararg 를 인자로 받는 fucntion 에 array 를 넘겨주고 싶은 경우 (매개변수로 사용하고 싶은 경우) * 을 앞에 붙여주면 되네요.
    // 이걸 spread operator 라고 하네요.
    // spread operator -> https://kotlinlang.org/docs/functions.html#variable-number-of-arguments-varargs
    personInstance?.printAllProps() // name: Yoon, age: 10

    println()
    // 인스턴스로부터 클래스정보 가져오기
    println(personInstance?.javaClass?.simpleName) // Person

    println()
    // java lang
    val person = Class.forName("com.brandon.reflection.Person")
    println(person.simpleName) // Person
}