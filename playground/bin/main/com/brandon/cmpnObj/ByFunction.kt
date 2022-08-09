package com.brandon.cmpnObj

import kotlin.reflect.KProperty

class ByFunction {
    val map = mutableMapOf<String, Any>()
    private fun <T:Any> byFunc(v:T) = Dele(v, map)

    val userid by byFunc("eager20")
    val count by byFunc(0)

}

class Dele<T:Any>(private var param1:T, private val map:MutableMap<String, Any>){
    operator fun getValue(ref:Any?, prop:KProperty<*>):T{
        if(prop.name !in map) map[prop.name] = param1 // 맵에 없으면 넣는데..
        return map[prop.name] as T
    }
    operator fun setValue(ref:Any?, prop:KProperty<*>, v:T){
        map[prop.name] = v
    }
}

fun main(args: Array<String>) {

    val value = ByFunction()
    value.map.forEach( { k, v ->
        println("$k = $v")
    });
    // Dele에서 get을 할때 값을 최초로 넣기 때문에 값이 없다.. 해결은 ByFunction2 에서

    println( value.userid )
    println( value.count )

    value.map.forEach( { k, v ->
        println("$k = $v")
    });

}
