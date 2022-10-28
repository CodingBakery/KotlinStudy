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

        fun myFunString(no: Int): String {
            return no.toString()+"!"
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
    array.filter(FuncRefMyClass2::myFun2).forEach{ print("111 ${it}, ") }
    println()
    array.filter { FuncRefMyClass2.myFun2(it) }.forEach{ print("222 ${it}, ") }
    println()
    array.map{ FuncRefMyClass2.myFunString(it)}.forEach{ print("333 ${it}, ") }

    println()
    array.map(FuncRefMyClass2::myFunString).forEach{ print("444 ${it}, ") }

    println()
    ///array.map(FuncRefMyClass2::m).forEach{ print("333${it}, ") }

}
