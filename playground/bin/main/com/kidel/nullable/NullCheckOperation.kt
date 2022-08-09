package com.kidel.nullable

import java.text.SimpleDateFormat
import java.util.*

/**
 * 코틀린에서의 Null Check
 */
fun main() {
    val s1 = "Hello"
    val s2: String? = null

    println("**** 연산자 ?. ****") // (s?).가 아니라 (s)?.
    println("[$s1 is ${s1.length} letters.")
    println("[$s2 is ${s2?.length} letters.")

    /**
     * 엘비스 연산자 ?:
     */
    fun strLenSafe(s: String?): Int = s?.length ?: 0

    /**
     * 엘비스 연산자와 throw
     */
    class BirthDate(val year: Int, val month: Int, val day: Int)
    class Person(val name: String?, val birthYear: BirthDate?)

    fun getAge(person: Person): Int {
        val birthYear = person.birthYear?.year ?: throw Exception("출생년도가 없습니다.")

        val sysDate = Date(System.currentTimeMillis())
        val dateFormat = SimpleDateFormat("yyyy", Locale("ko", "KR"))
        val nowYear = dateFormat.format(sysDate).toInt()

        return nowYear - birthYear
    }

    println("**** 엘비스 연산자 ?: ****")
    println("[$s1 is ${strLenSafe(s1)} letters.")
    println("[$s2 is ${strLenSafe(s2)} letters.")

    println("**** 엘비스 연산자와 throw ****")
    val p1 = Person("Kidel", BirthDate(1997, 4, 24))
    val p2 = Person("Kidel", null)
    println("p age: ${getAge(p1)}")
    println("p age: ${getAge(p2)}") // 에러 발생!

    /**
     * 안전한 캐스트 as?
     */
    class Cat(val furColor: String, val eyeColor: String, val genom: String) {
        override fun equals(o: Any?): Boolean {
            val otherCat = o as? Cat ?: return false

            return otherCat.genom.equals(genom)
        }

        override fun hashCode(): Int {
            return genom.hashCode()
        }
    }

    println("**** 안전한 캐스트 as? ****")
    val c1 = Cat("black", "yellow", "awelkjh23rdaf")
    val c2 = Cat("white", "yellow", "awelkjh23rdaf")
    println(c1 == c2)   // true


    /**
     * 널 아님 단언 !! - 써야 하는 상황이 뭘까? -> 역으로 null이면 NPE가 발생해야 하는 상황이 있을 수도 있겠다!
     */
    fun printReallyNotNullLength(s: String?) {
        println("${s!!.length}")
    }

    println("**** 널 아님 단언 !! ****")
//    printReallyNotNullLength(s1)
     printReallyNotNullLength(s2) // NPE

}




