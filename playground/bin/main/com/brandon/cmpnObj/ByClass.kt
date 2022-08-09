package com.brandon.cmpnObj

import kotlin.reflect.KProperty

class byCalss {
    var userid by Field("eager20")
    var count by Field(0)
}

class Field<T:Any>(private var value:T){
    operator fun getValue(ref:Any?, prop: KProperty<*>) = value
    operator fun setValue(ref:Any?, prop: KProperty<*>, v:T){
        value = v
    }
}

fun main(args: Array<String>) {
    val value = byCalss() ;
    println( value.userid   )
    println( value.count )
}
