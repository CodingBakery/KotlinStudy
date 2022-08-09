package com.baker.kotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.string.shouldHaveLength
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.checkAll
import io.kotest.property.forAll

class PropertyExample: StringSpec({
    "임의의 문자열 길이 체크하기 - forAll" {
        forAll<String, String> { a, b ->
            println("a: ${a}")
            println("b: ${b}")
            println("a == b: ${a.length == b.length}")
            println()
            (a + b).length == a.length + b.length
        }
    }

    "임의의 3가지 타입 인풋 생성하기" {
        forAll<String, Int, Boolean>(5) { a, b, c ->
            println("a: ${a}")
            println("b: ${b}")
            println("c: ${c}")
            println()
            true
        }
    }

    "임의의 문자열 길이 체크하기 - checkAll" {
        checkAll<String, String> { a, b ->
            a + b shouldHaveLength a.length + b.length
        }
    }

    "음주 가능한 나이인가? (forAll)" {
        forAll(Arb.int(18..150)) { a ->
            val actual = isAdult(a)
            println("${a} = ${actual}")
            actual
        }
    }

    "음주 가능한 나이인가? (checkAll)" {
        checkAll(Arb.int(18..150)) { a ->
            val actual = isAdult(a)
            println("${a} = ${actual}")
            actual
        }
    }
})

fun isAdult(age: Int): Boolean {
    return age >= 19
}