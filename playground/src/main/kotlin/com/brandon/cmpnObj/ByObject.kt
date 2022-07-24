package com.brandon.cmpnObj

class Entity{
    val map = mutableMapOf<String, Any>()
    var userid:String by map
    var count:Int by map
    init{
        map["userid"] = "hika"
        map["count"] = 0
    }
}

fun main(args: Array<String>) {

    val value = Entity() ;

    println( value.userid )
    println( value.count )
    value.map.set("userid","baker")
    println( value.userid )
    println( value.count )
    value.userid = "dominic"
    println( value.userid )
    println( value.count )

}
