package com.brandon.reflection

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberExtensionProperties
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

open class SuperClass {
     val superVal: Int = 10
}

class ProMyClass(val no: Int): SuperClass() {
     val myVal: String = "hello"
     val String.someVal: String
      get() = "world"
}

fun proSomeFun(arg: KClass<*>) {
     // 확장 프로퍼티를 제외한 클래스에 선언된 모든 프로퍼티 반환
     val properties = arg.declaredMemberProperties
     println("[declaredMemberProperties]")
     for (property in properties) {
          println("${property.name}: ${property.returnType} ")
         }
    println()

    // 확장 프로퍼티를 제외한 클래스와 상위 클래스에 선언된 모든 프로퍼티 반환
     val properties2 = arg.memberProperties
     println("[memberProperties]")
     for (property in properties2) {
          println("${property.name}: ${property.returnType} ")
         }
    println()

    // 클래스에 선언된 확장 프로퍼티 반환
     val properties3 = arg.declaredMemberExtensionProperties
     println("[pdeclaredMemberExtensionProperties]")
     for (property in properties3) {
          println("${property.name}: ${property.returnType} ")
         }
}
fun main(){
    proSomeFun(ProMyClass::class)
}
