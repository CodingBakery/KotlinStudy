package com.brandon.reflection

import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

open class MyClass(no: Int) {
     constructor(no: Int, name: String): this(10) {}
     constructor(no: Int, name: String, email: String): this(10) {}
}

fun someFun(arg: KClass<*>) {
     val constructors = arg.constructors // 모든 생성자 정보
     for (constructor in constructors) {
          print("constructor->")
          val parameters =constructor.parameters
          for (parameter in parameters) {
               print("${parameter.name}: ${parameter.type}, ");
              }
          println()
         }
    
     print("primary constructor->")
     val primaryConstructor = arg.primaryConstructor // 주 생성자 정보
     if (primaryConstructor != null) {
          val parameters =primaryConstructor.parameters
          for (parameter in parameters) {
              print("${parameter.name}: ${parameter.type}, ");
              }
         }
}
fun main() {
    someFun(MyClass::class)
}