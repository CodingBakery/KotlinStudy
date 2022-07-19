package com.baker.kotest

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class ShouldSpecTest: ShouldSpec({
    should("Kotest 클래스의 executeChild를 실행하면 ChildKotest의 실행 결과값이 리턴된다.") {
        // given
        val childKotest = mockk<ChildKotest>()
        val kotest = Kotest(childKotest)
        val expected = "메롱"
        every { childKotest.executeTest() } returns expected

        // when
        val actual = kotest.executeChild()

        // then
        actual shouldBe expected
        println(expected)
    }
})