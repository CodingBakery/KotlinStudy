package com.evelyn.extensionFunction

import com.evelyn.tmp1.lastChar
import com.evelyn.tmp2.lastChar as lastChar2

/**
 * 확장함수
 * class를 상속받지 않고 클래스의 기능을 확장할 수 있다.
 *
 * 확장함수 선언
 * fun 클래스.메서드명(파라메터):리턴타입 { 구현 }
 */
fun main() {
    /**
     * 확장함수는 static 메소드이고, 클래스 밖에 선언되기 때문에 오버라이드 되지 않는다.
     * - 메서드 호출 시, 인스턴스가 아닌 변수 타입에 해당하는 메서드가 호출된다.
     * - 디컴파일된 코드를 보면 오버라이딩이 아닌, 오버로딩이 되어있다. (파라미터만 다른 static 메서드로)
     */
    println("[확장함수의 오버라이드]")
    val view: View = Button() //View 를 상속받은 Button 클래스
    view.showOff() //오버라이드된것 같지만 View.showOff() 가 호출 됨

    val button: Button = Button()
    button.showOff() //Button.showOff() 가 호출 됨
    println()

    /**
     * 클래스의 멤버메서드와 동일한 signature의 확장함수를 만들때, 항상 멤버메서드가 호출된다.
     * - 멤버 메서드와 이름만 같지만 다른 시그니쳐인 오버로딩에 해당하는 확장함수를 만드는 것은 가능하다.
     */
    println("[확장함수와 멤버메서드]")
    Example().printFuncType() // Example의 확장함수가 아닌, 멤버 메서드가 호출된다.
    println()

    /**
     * nullable receiver 확장함수 만들기
     */
    println("[확장함수 - nullable receiver]")
    val Nbutton: Button? = null
    println(Nbutton.getName())
    println()

    /**
     * 중복된 이름의 확장함수 - import as 로 해결하기
     */
    println("[중복된 이름의 확장함수 - import as]")
    val str: String = "Hello Kotlin"
    println(str.lastChar())  // import com.evelyn.tmp1.lastChar
    println(str.lastChar2()) // import com.evelyn.tmp2.lastChar as lastChar2
    println()

    /**
     * 확장 프로퍼티
     * - property도 확장이 가능하다.
     * - 확장 프로퍼티는 상태를 가질 수 없기 때문에 초기화를 하는 것은 불가능.
     * - 우리가 할 수 있는 것은 setter, getter를 정의하는 것 밖에없다. 이 setter, getter메서드로 마치 property처럼 동작하게 해주는 것이다.
     *
     * - val 확장 property의 경우 getter를 정의해야하며
     * - var 확장 property의 경우 getter, setter 모두 정의해야 한다.
     *
     * - Extension Function처럼 getter,setter에서 this를 사용할 수 있다.
     */
    println("[확장 프로퍼티]")
    val person = Person("홍", "길동")
    println(person.fullName)
    person.fullName = "아무개"
    println(person.fullName)

}

open class View
class Button : View()

fun View.showOff() {
    println("View show off!!")
}

fun Button.showOff() {
    println("Button show off!!")
}

fun Button?.getName(): String {
    if (this == null) {
        return "null"
    } else {
        return "nullable Button!"
    }
}

class Example {
    fun printFuncType() {
        println("Class method")
    }
}

fun Example.printFuncType() {
    println("Extension function")
}

// 자바에서 사용할 확장함수
fun String.convert(): String {
    return "hello this is extension function!"
}

// 확장 프로퍼티
class Person(var firstName: String, var lastName: String) {}

var Person.fullName: String
    get() = this.firstName + " " + this.lastName
    set(value) {
        this.firstName = value.substring(0,1)
        this.lastName = value.substring(1,3)
    }
