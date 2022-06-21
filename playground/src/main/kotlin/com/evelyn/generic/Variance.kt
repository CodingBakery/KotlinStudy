package com.evelyn.generic

fun main() {
    /**
     * 상위 클래스는 하위 클래스를 수용할 수 있다.
     * Int형 변수는 Number형 변수로 할당되어 형변환이 이루어 짐.
     *
     * Int는 Int? 의 하위 자료형이다.
     */

    val obj = GenericNull<Int>()
//val obj = GenericNull<Int?>() // 오류!

    /**
     * 가변성을 지정하지 않으면 형식 매개변수에 상/하위 클래스가 지정되어도 서로 자료형이 변환되지 않음.
     */
    val obj1: Parent = Child() // 가능
//    val obj2: Child = Parent() // 불가능
//    val obj3: Cup<Parent> = Cup<Child> // 불가능
//    val obj4: Cup<Child> = Cup<Parent> // 불가능

    /**
     * 1. 무변성 (Invariance)
     * in이나 out으로 공변성이나 반공변성을 따로 지정하지 않으면 무변성.
     */
//    클래스의 관계 : Any < - Int < -Nothing
//    Nothing은 코틀린의 최하위 자료형
//    val anys: Pig<Any> = Pig<Int>(10) // 오류
//    val nothings: Pig<Nothing> = Pig<Int> // 오류

    /**
     * 2. 공변성 (Covariance)
     * 형식 매개변수의 상하 자료형 관계가 성립하고, 그 관계가 그대로 인스턴서 자료형 관계로 이어지는 경우
     * out 키워드를 사용
     */
    val anys: Dog<Any> = Dog<Int>(10) // 관계성립으로 객체 생성 가능
//    val nothings: Dog<Nothing> = Dog<Int>(20) // 오류

    /**
     * 3. 반공변성 (Contravariance)
     * 자료형의 상하 관계가 반대가 되어 인스턴스의 자료형이 상위 자료형이 됨
     * 공변성의 반대 경우
     * in 키워드를 사용
     */
    //    val anys: Cat<Any> = Cat<Int>(10) // 오류
    val nothings: Cat<Nothing> = Cat<Int>(20) // 관계성립으로 객체 생성 가능


}

/**
 * 스타 프로젝션 : 스타(*)를 통해 지정, 어떤 자료형이라도 들어올 수 있으나 구체적으로 자료형이 결정되면 그 자료형과 하위 자료형의 요소만 허용한다.
 * in으로 정의된 형식 매개변수를 *로 받으면 in Nothing으로 간주
 * out으로 정의된 형식 매개변수를 *로 받으면 out Any?로 간주
 * get 하기 위해서는 out, set하기 위해서는 in이 지정되어야 한다. (out은 반환 자료형, in은 매개변수의 자료형 에만 사용 가능....??)
 */
class InOutTest<in T, out U>(t: T, u: U) {
    //T: in 프로젝션, U: out 프로젝션
//    val propertyT: T = t // 오류! T는 in프로젝션이기 때문에 out위치에 사용 불가
    val propertyU: U = u

//    fun func1(u: U) //오류! U는 out위치이기 때문에 in 위치 사용 불가
    fun func2(t: T) {
        print(t)
    }
}

open class Parent

class Child : Parent()

class Cup<T>

class Pig<T>(val size: Int)
class Dog<out T>(val size: Int)
class Cat<in T>(val size: Int)
