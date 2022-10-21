package com.brandon.cmpnObj

class CmpnonObjTest1 {
    companion object{
        val prop = "나는 Companion object의 속성이다."
        fun method() = "나는 Companion object의 메소드다."
    }
}
fun main(args: Array<String>) {
    //사실은 MyClass2.맴버는 MyClass2.Companion.맴버의 축약표현이다.
    println(CmpnonObjTest1.Companion.prop)
    println(CmpnonObjTest1.Companion.method())
}

