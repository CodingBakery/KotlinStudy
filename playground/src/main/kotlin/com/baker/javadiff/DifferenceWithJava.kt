package com.baker.javadiff

import com.baker.nullable.strLenNullable as strLenEvenNull

/**
 * 코틀린을 이용하면서 자바와 다른 점들을 살펴보고 예제를 작성한다.
 * 관련 설명은 아래 문서를 참조.
 * - https://github.com/CodingBakery/KotlinStudy/blob/main/content/%EC%BD%94%ED%8B%80%EB%A6%B0%EC%9D%80_%EC%9E%90%EB%B0%94%EC%99%80_%EB%AD%90%EA%B0%80_%EB%8B%A4%EB%A5%BC%EA%B9%8C.md
 */
fun main(args: Array<String>) {
    /***************
     *    예제 1    *
     ***************/
    println("##### 예제 1 #####")

    // Kotlin 의 변수 선언
    var mutable: String                     // 변경 가능
    val immutable: String = "immutable"     // 불변 (final)


    /***************
     *    예제 2    *
     ***************/
    println("")
    println("")
    println("##### 예제 2 #####")

    val person = Person("베이커", true)
    println(person.name)        // name의 getter 호출
    person.isMarried = false    // isMarried의 setter 호출

    val square = Square(100, 100)
    println(square.isSquare)    // 커스텀 getter 호출


    /***************
     *    예제 3    *
     ***************/
    println("")
    println("")
    println("##### 예제 3 #####")

    // 문자열 템플릿 기능으로 변수를 문자열에 편하게 삽입
    val name = "베이커"
    println("Hello, ${name}")


    /***************
     *    예제 4    *
     ***************/
    println("")
    println("")
    println("##### 예제 4 #####")

    // Kotlin 스마트 캐스트 - 타입 검사 이후에 별도 형변환 필요없음
    var castTarget: Any
    castTarget = name
    if (castTarget is String) {
        castTarget.subSequence(0, castTarget.lastIndex)
    }


    /***************
     *    예제 5    *
     ***************/
    println("")
    println("")
    println("##### 예제 5 #####")

    // Kotlin에서는 == 이 동등성(equals), === 값 일치(참조 주소)를 비교한다.
    val person1 = Person("베이커", true)
    val person2 = Person("베이커", true)
    println(person1.equals(person2))     // true
    println(person1 == person2)     // true
    println(person1 === person2)    // false

    // == 구문 자체에서 null 체크를 내부적으로 진행하므로 NPE 걱정 없이 사용 가능
    var person3 = null
    var person4 = null
    println(person3 == person1)     // false
    println(person3 == person4)     // true


    /***************
     *    예제 6    *
     ***************/
    println("")
    println("")
    println("##### 예제 6 #####")
    // String 타입에 대한 확장함수를 정의하고 사용할 수 있음
    // 수신객체의타입               실제수신객체  실제수신객체
    fun String.lastChar(): Char = this.get(this.length - 1)
    println("Kotlin".lastChar())    // 결과: n


    /***************
     *    예제 7    *
     ***************/
    println("")
    println("")
    println("##### 예제 7 #####")
    val list = listOf("AA", "BB", "CC")
    // 파라미터 명을 지정한 경우 순서를 지키 않아도 되고, 생략된 파라미터는 디폴트 값을 사용한다.
    println(joinToString(list, postfix=";", prefix="# "))   // 결과: # AA, BB, CC;


    /***************
     *    예제 8    *
     ***************/
    println("")
    println("")
    println("##### 예제 8 #####")
    // list 의 엘리먼트를 순차적으로 반복
    for (el in list) {
        print("${el} ")
    }
    println()

    // 1부터 100까지 1씩 증가하며 100번 반복
    for (i in 1..100) {
        print("${i} ")
    }
    println()

    // 100 부터 1까지 2씩 감소하며 50번 반복
    for (i in 100 downTo 1 step 2) {
        print("${i} ")
    }
    println()

    // 3이 list2 에 포함되지 않았는지 확인
    val list2 = listOf(1, 3, 5)
    println(3 !in list2)     // false

    val map = mapOf(1 to "one", 2 to "two", 3 to "three")
    for ((key, value) in map) {
        println("${key} = ${value}")
    }


    /***************
     *    예제 9    *
     ***************/
    println("")
    println("")
    println("##### 예제 9 #####")
    parseNumber("Not a number")     // 결과: null


    /***************
     *    예제 10   *
     ***************/
    println("")
    println("")
    println("##### 예제 10 #####")
    // 코틀린의 when (Java의 향상된 switch 문)
    val color: Color = Color.RED
    val str: String = when (color) {
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.BLUE -> "Battle"
        else -> "UNKNOWN"
    }
    println(color)      // 결과: Richard

    println(getMixedColor(Color.RED, Color.YELLOW)?.name)       // 결과: ORANGE
    println(getMixedColor(Color.RED, Color.RED)?.name)          // 결과: UNKNOWN


    /***************
     *    예제 11   *
     ***************/
    println("")
    println("")
    println("##### 예제 11 #####")
    // 이 코드는 코드의 동작을 보기 위함이 아니라
    // 컴파일된 결과물의 멤버 이름이 어떻게 mangle 되는지 확인하기 위해 작성됨
    // 빌드 결과물(jar 파일)을 디컴파일하여 확인하여였음
    // getInternalMember$playground() 와 같이 뒤에 프로젝트명이 붙음
    val internalClass = InternalTest("any value")
    println(internalClass.internalMember)
    println(internalClass.internalFun())


    /***************
     *    예제 12   *
     ***************/
    println("")
    println("")
    println("##### 예제 12 #####")
    // 클래스 뿐만 아니라 최상위에 정의된 함수, 멤버등도 import 하여 사용할 수 있다.
    println(strLenEvenNull(null))
    println(strLenEvenNull("hahaha"))
}

fun getMixedColor(c1: Color, c2: Color): Color {
    // 코틀린 - 인자 없는 when 가능
    return when {
        (c1 == Color.RED && c2 == Color.YELLOW) -> Color.ORANGE
        (c1 == Color.BLUE && c2 == Color.RED) -> Color.PURPLE
        else -> Color.UNKNOWN
    }
}

enum class Color {
    RED,
    YELLOW,
    ORANGE,
    BLUE,
    PURPLE,
    UNKNOWN;
}

fun getLarger(a:Int, b:Int): Int {
    return if (a > b) a else b
}

fun <T> joinToString(
        collection: Collection<T>,
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""
): String {
    return collection?.joinToString(separator, prefix, postfix)
}

fun parseNumber(input: String) {
    val number = try {
        Integer.parseInt(input)
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}

class Person(
        val name: String,           // 읽기 전용
        var isMarried: Boolean      // 읽기 쓰기
) {
    override fun equals(other: Any?): Boolean {
        return if (other is Person) name == other.name && isMarried == other.isMarried else false
    }
}

class Square(
        val width: Int,
        val height: Int
) {


    val isSquare: Boolean

        get() = height == width
}

internal class InternalTest(
        internal val value: Any
) {
    internal val internalMember: Any
    get() = value

    internal fun internalFun(): Int {
        return 1
    }
}

// Java 처럼 작성한 것
class DelegatingCollection<T>: Collection<T> {
    private val innerList = arrayListOf<T>()
    override val size: Int get() = innerList.size
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)
    override fun iterator(): Iterator<T> = innerList.iterator()
}

// Kotlin의 By를 이용한 것
class DelegatingCollectionBy<T> (
    innerList: Collection<T> = ArrayList<T>()
): Collection<T> by innerList

data class PersonData(val name: String, val postalCode: Int)

object DataManager {
    val key: String
        get() {
            TODO()
        }
}