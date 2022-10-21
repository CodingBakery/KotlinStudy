package com.evelyn.generic

fun main() {
/** 자바와 마찬가지로 코틀린 제네릭 타입 인자 정보는 런타임에 지워진다.
 * 이는 제네릭 클래스 인스턴스가 그 인스턴스를 생성할 때 쓰인 타입 인자에 대한 정보를 유지하지 않는 다는 뜻. */
    fun <T> checkType(value: T) {
//        if (value is List<String>) {
//            // ERROR: Cannot check for instance of erased type
//        }

        if (value is List<*>) {  // 값이 List 인지를 확인하려면 스타 프로젝션 사용

        }
    }

//    실행 시점에는 제네릭 타입의 인자를 알 수 없으므로 캐스팅은 항상 성공, 그런 타입 캐스팅을 사용하면 컴파일러가 "unchecked case" 경고를 해준다.
//    코드는 문제없이 컴파일 되지만 잘못된 타입의 원소가 들어있는 리스트를 전달하면 실행 시점에 Exception이 발생한다.
    fun printSum(c: Collection<*>) {
        val intList = c as? List<Int>
            ?: throw IllegalArgumentException("List is expected")
        println(intList.sum())
    }

    printSum(listOf(1, 2, 3)) // 6
    printSum(setOf(1, 2, 3)) // IllegalArgumentException: List is expected
    printSum(listOf("a", "b", "c")) // ClassCastException: String cannot be cast to Number

//    다만 컴파일 타임 시점에 타입 정보가 주어진 경우에는 is 검사를 수행하게 허용할 수 있다
    fun printSum2(c: Collection<Int>) {
        if(c is List<Int>) {
            println(c.sum())
        }
    }
}

/**
 * inline + reified
 * 타입 소거의 제약을 피할 수 있는 방법.
 * 인라인 함수의 타입 파라미터는 실체화 되므로 실행 시점에 인라인 함수의 타입 인자를 알 수 있다.
 * 함수를 인라인 함수로 만들고 타입 파라미터를 reified로 지정하면 어떤 타입이 T의 인스턴스인지 실행 시점에 검사할 수 있다.
 */
//fun <T> isA(value: Any) = value is T // 타입 인자를 알 수 없기 때문에 불가능
inline fun <reified T> isA(value: T) = value is T // 가능

