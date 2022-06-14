package com.kidel.nullable

fun main() {

    /**
     * let 함수: 블록 내에서 null이 아닌 it을 다룰 수 있다.
     * safe call과 함께 사용하면, null이 아닌 경우에만 let 함수가 호출됨.
     *
     * - 지정된 값이 null 이 아닌 경우에 코드를 실행해야 하는 경우.
     * - Nullable 객체를 다른 Nullable 객체로 변환하는 경우.
     * - 단일 지역 변수의 범위를 제한 하는 경우.
     */
    println("#### EX 1 ####")
    fun decorateStr(s: String): String {    // null 이 아닌 인자를 받아야만 할 때
        return "-*-#-*- ${s} -*-#-*-"
    }
    fun beautify(s: String?) {
        s?.let {    // safe call 뒤에 let이 호출되므로
            decorateStr(it) // it은 not null
        }
    }
    println("RESULT => ${beautify(null)}")
}
