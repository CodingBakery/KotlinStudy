package com.brandon.reflection

import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberExtensionFunctions
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberFunctions

open class MethodSuperClass {
     fun superFun() {}
}

class MethodMyClass: SuperClass() {
     fun myFun() {}
     fun String.someFun() {}
}

fun methodSomeFun(arg: KClass<*>) {
     // 확장 함수를 제외한 클래스에 선언된 모든함수반환
     val functions= arg.declaredMemberFunctions
     println("[declaredFunctions]")
     for (function in functions) {
          println("${function.name}: ${function.returnType}")
         }
    println()
    // 확장함수를 제외한 클래스와 상위 클래스에 선언된 모든함수반환
     val functions2= arg.memberFunctions
     println("[memberFunctions]")
     for (function in functions2) {
          println("${function.name}: ${function.returnType}")
         }
    println()
    // 클래스에 선언된 확장함수반환
     val functions3= arg.declaredMemberExtensionFunctions
     println("[declaredMemberExtensionFunctions]")
     for (function in functions3) {
          println("${function.name}: ${function.returnType}")
         }
}

fun main()
{
    methodSomeFun(MethodMyClass::class)
}
