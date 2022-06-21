package com.evelyn.generic

/** 제네릭 클래스 */
class Box<T>(t: T) {
    var name = t
}

fun main() {
    val box1: Box<Int> = Box<Int>(1)
    val box2: Box<String> = Box<String>("Hello")

    // 객체 생성시, 생성자에서 유추될 수 있는 자료형이 있다면 <Int>, <String> 생략 가능
    val box3 = Box(1) // Box<Int>로 추론
    val box4 = Box("Hello") //Box<String>으로 추론
    println("")
    println(box1.name)
    println(box2.name)
    println(box3.name)
    println(box4.name)

    val result = add(2, 3, {a, b -> a + b})
    // 마지막 인자가 람다식이면 val result = add(2,3){a, b -> a + b} 이렇게 표현 가능
    println(result)
}

/**
 * - 형식 매개변수를 프로퍼티에 사용하는 경우 클래스 내부에서는 사용할 수 없다.
 *      -> 프로퍼티는  초기화 되거나 abstract로 선언되어야 한다.
 *
 * - 프로퍼티를 사용하는 경우 주 생성자나 부 생성자를 이용해야 한다.
 */
/*
class MyClass<T> {
    var myProp: T // 오류!
}
 */
class MyClass1<T>(val myProp: T) {} // 주 생성자 이용
class MyClass2<T> {
    var myProp: T

    constructor(myProp: T) {        // 부 생성자 이용
        this.myProp = myProp
    }
}

/** 제네릭 함수 */
fun <T> genericFunc(arg: T): T? {
    println(arg)
    return arg
}

// 형식 매개변수가 2개인 경우
fun <K, V> put(key: K, value: V): Unit {
    print("key : $key")
    print("value : $value")
}

/**
 * 제네릭과 람다식
 * 형식 매개변수로 선언된 함수의 매개변수는 연산 불가
 *
 * 람다식을 매개변수로 받으면 해결 가능 - 자료형을 결정하지 않아도 실행 시 람다식 본문을 넘겨줄 때 결정되기 때문
 */
/*
fun <T> add(a: T, b: T): T {
    return a + b //오류
}
 */

fun <T> add(a: T, b: T, op: (T, T) -> T): T {
    return op(a, b)
}

/** 형식 매개변수의 null 제어 */
val box = Box(null) // 제네릭의 형식 매개변수는 기본적으로 Null 허용
val boxNonNull: Box<String> = Box("nonNull") // non-null
val boxNull: Box<String?> = Box(null) // null 가능

/**
 * upper bound
 * 콜론(:)을 사용해 타입 파라미터를 제한 하는 것. java의 extends와 같음
 * 하나의 타입 파라미터에 여러개의 upper bound가 필요할 땐 'where' 키워드 사용
 */
// null 을 허용하지 않으려면
class GenericNull<T: Any> { // Any가 upper bound로 지정되어 null을 허용하지 않음

}

val obj = GenericNull<Int>()
//val obj = GenericNull<Int?>() // 오류!

// 여러개의 upper bound 필요
fun <T> myMax(a: T, b: T): T where T:Number, T:Comparable<T> {
    return if (a > b) a else b
}

