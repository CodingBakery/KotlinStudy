package com.baker.kotest

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class BehaviorSpecTest: BehaviorSpec({
    Given("Kotest 클래스의") {
        val kotest = mockk<Kotest>()
        val expected = "메롱"
        every { kotest.executeTest() } returns expected
        When("executeTest를 실행하면") {
            val actual = kotest.executeTest()
            Then("결과값이 ${expected}이/가 리턴된다.") {
                actual shouldBe expected
                println(expected)
            }
        }
    }

    given("Kotest 클래스에서") {
        val childKotest = mockk<ChildKotest>()
        val kotest = Kotest(childKotest)
        val value = 3
        val expected = 9
        `when`("${value}를 이용하여 printSquare를 호출하면") {
            val actual = kotest.printSquare(value)
            `then`("${expected}가 리턴된다.") {
                actual shouldBe expected
                println(expected)
            }
        }
    }
})