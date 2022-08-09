package com.brandon.cmpnObj

import kotlin.reflect.KProperty

class ByFunction2 {
    val map = mutableMapOf<String, Any>()
    // private fun <T:Any> byFunc(v:T) = Dele(v, map)
    private fun <T:Any> ByFunc2(k:String, v:T) = Dele(v, map.also{it[k] = v}) // ByFunc2 호춘하는 시점에 초기화 처리~
    val userid by ByFunc2( "userNm","eager20")
    val count by ByFunc2("cnt",0)

}

fun main(args: Array<String>) {

    val value = ByFunction2() ;
    value.map.forEach( { k, v ->
        println("$k = $v");
    });
    println()
    println( value.userid )
    println( value.count )
    println()
    value.map.forEach( { k, v ->
        println("$k = $v");
    });

}
