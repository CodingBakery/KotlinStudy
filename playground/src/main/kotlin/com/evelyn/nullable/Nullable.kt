package com.evelyn.nullable

import java.lang.IllegalArgumentException

fun main() {

    /**
     * safe call '?.' - null이 아니면 실행하고, null이면 실행하지 않는다 (그대로 null)
     */
    val str1: String? = "ABC"
//    str1.length // nullable 에서 함수 호출 불가능 -> 컴파일 에러.
    str1?.length // 가능 (safe call)

    /**
     * 한 번 null 검사를 하면 non-null 임을 컴파일러가 알 수 있다.
     */
    if (str1 == null) {
        throw IllegalArgumentException("null!!")
    }
    str1.length // 가능


    /**
     * Elvis 연산자 '?:' - 앞의 연산 결과가 null 이면 뒤의 값을 사용.
     *  (잡담) ?: ->  90도 돌린 모양이 엘비스 가수의 머리모양을 닮았다 하여 붙여진 이름이라고 함.
     */
    val str2: String? = null
    println(str2?.length ?: 0)


    /**
     * Kotlin 에서 Java 코드를 가져다 사용할 때,
     * null에 대한 annotation (@Nullable, @Notnull) 을 Kotlin이 인식하고 이해하여 활용 할 수 있다.
     *
     * @Nullable 이 없다면,
     * Kotlin에서는 이 값이 nullable인지 non-nullable인지 알 수가 없다.
     *
     * '플랫폼 타입'
     * 코틀린이 null 관련 정보를 알 수 없는 타입.
     * Runtime시 Exception이 날 수 있다.
     *
     */

}

/**
 * !! - null 아님 단언
 * nullable로 선언되어 있지만, 로직상 절대로 null이 들어올 수 없는 경우 !! 를 붙여주면 컴파일 에러 없이 함수 호출 가능.
 * 단, null이 들어오는 경우에는 NPE가 나기 때문에 확실한 경우에만 사용.
 */
fun startsWithA(str: String?): Boolean {
    return str!!.startsWith("A")
}

