package com.kidel.nullable


fun main() {

    /**
     * 제너릭을 사용하면 무조건 null 가능성 있음.
     *
     * - 타입 파라미터 T의 추론 = Any?
     * - ?가 없어도 null을 받을 수 있는 케이스.
     */
    println("#### Type Parameter : nullable ####")
    fun <T> printHashCode(t: T) {
        println("HASH => ${t?.hashCode()}")
    }
    printHashCode(null)

    /**
     * null이 될 수 없도록 하려면.. 타입 상한(upper bound)을 지정
     *
     * - 콜론 뒤에 지정된 유형의 하위 유형만 대체할 수 있도록 하는 상한.
     * - 기본 상한은 Any?
     */
    println("#### Type Parameter : nullable ####")
    fun <T: Any> printHashCode2(t: T) : T {
        println("HASH => ${t.hashCode()}")
        return t
    }
    printHashCode2("not null")

}
