package com.brandon.collect

import com.brandon.reflection.FuncRefMyClass
import kotlin.reflect.KFunction

class MethodInject {
    companion object{
        fun tempMethod(aa: Int, bb: String): String {
            return bb + "=="+aa.toString();
        }
    }
}

fun main(args : Array<String>) {


    // 함수의 형식을 만들어 내는것이 꼭 인터페이스와 같다.. 신기하네.. 
    var lamdaMethod : (aa:Int, bb:String ) -> String = {
        x, y -> y+":"+x.toString()
    }

    //val funReference2: KFunction<*> = MethodInject::tempMethod

    // 람다 함수를 변수에 넣어서 전달 하는게 대부분일것 같고..
    // funReference2 <== 요런넘은 filter, map 과 같은 곳에서 사용되는것으로 보인당 +_+ 좀더 봐야 겠지만둥..
    println(SimpleHighOrderFunction(lamdaMethod, 23, "eager20"))
    //println(SHOF(funReference2, 100, "eager30"))

    val tempList = mutableListOf<String>("test", "eager20", "woeowowo")

    tempList.map { MethodInject.tempMethod(it.length, it) }.forEach { print(it+" ")}


}

fun SimpleHighOrderFunction(sum: (Int, String) -> String, a: Int, b: String): String = sum(a, b)