package com.brandon.reflection

import kotlin.reflect.KFunction

fun funcRefmyFun(no: Int): Boolean {
    return no > 10
}

class FuncRefMyClass {
     fun myFun2(no: Int): Boolean {
         return no < 10
     }
}

class FuncRefMyClass2 {
    companion object {
        fun myFun2(no: Int): Boolean {
            return no < 10
        }
    }
}

fun main(){
    val funReference:KFunction<*> =::funcRefmyFun // <- 함수 레퍼런스
    val funReference2:KFunction<*> =FuncRefMyClass::myFun2

    println(funReference.name)

    val array = arrayOf<Int>(10, 5, 30, 15)
    array.filter(::funcRefmyFun).forEach{ print("${it}, ") }

    println()
    //ERROR!! array.filter(FuncRefMyClass::myFun2).forEach{ print("${it}, ") }
    array.filter(FuncRefMyClass2::myFun2).forEach{ print("${it}, ") }

}
