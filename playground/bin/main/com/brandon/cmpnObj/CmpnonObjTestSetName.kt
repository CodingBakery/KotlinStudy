package com.brandon.cmpnObj

class CmpnonObjTest2 {
    companion object factory{
        val prop = "나는 Companion object의 속성이다."
        fun method() = "나는 Companion object의 메소드다."
    }
}
fun main(args: Array<String>) {
    //사실은 MyClass2.맴버는 MyClass2.Companion.맴버의 축약표현이다.
    println(CmpnonObjTest2)
    println(CmpnonObjTest2.factory)
    println(CmpnonObjTest2.factory.prop)
    println(CmpnonObjTest2.factory.method())

    val comp1 = CmpnonObjTest2.factory // -- (3)
    println(comp1.prop)
    println(comp1.method())
    val comp2 = CmpnonObjTest2 // -- (4)
    println(comp2.prop)
    println(comp2.method())
//    val comp3 = CmpnonObjTest2.Companion // -- (5) 에러발생!!!
//    println(comp3.prop)
//    println(comp3.method())
}