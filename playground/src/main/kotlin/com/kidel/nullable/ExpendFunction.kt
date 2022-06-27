package com.kidel.nullable


fun main() {

    /**
     * null이 가능한 타입에 선언된 확장 함수
     *
     */
    println("#### EX 1 ####")
    fun verifyUserInput(input: String?) {
        if (input.isNullOrBlank()) {
            println("Please fill in the required fields")
        }
    }

    verifyUserInput(" ")
    verifyUserInput(null)

    /**
     * 직접 확장 함수를 만들 수도 있다!
     *
     * - 이 클래스를 영구적으로 훼손시키지는 않아서 가능한 정의일까..?
     */
    println("#### EX 2 ####")
    fun String?.isEmpty(): Boolean = this == null || this.isBlank()
    val s1: String? = null  // ? 는 컴파일러에 알려줄 수 있는 키워드 정도!
    val s2 = "Hello"    // String 이어도 호출할 수 있음. ???

    println("s1 is Empty? => ${s1.isEmpty()}")
    println("s2 is Empty? => ${s2.isEmpty()}")
}


