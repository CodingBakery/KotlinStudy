package com.brandon.cmpnObj

import kotlin.reflect.KProperty

class byTest {
    var userid = Field2("eager20")
    var count = Field2(0)
}

class Field2<T:Any>(private var value:T){
    operator fun getValue(ref:Any?, prop: KProperty<*>) = value
    operator fun setValue(ref:Any?, prop: KProperty<*>, v:T){
        value = v
    }
}

fun main(args: Array<String>) {
    val value = byTest() ;
    println( value.toString()   )
    println( value.userid   )
    println( value.count )
}
