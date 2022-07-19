package com.baker.kotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify

class MockkTest : StringSpec({

    "Mockk를 이용하여 검증하기" {
        // given
        val childKotestMock = mockk<ChildKotest>()
        val kotest = Kotest(childKotestMock)
        every { childKotestMock.executeTest() } returns "child"

        // when
        val result:String = kotest.executeChild()
        println(result)

        // then
        verify(exactly = 1) { childKotestMock.executeTest() }
    }

    "파라미터 캡쳐하기" {
        // given
        val childKotest = mockk<ChildKotest>()
        val kotest = Kotest(childKotest)
        val value = 3
        val argSlot = slot<Int>()
        every { childKotest.increaseAndGet(capture(argSlot)) } returns 4

        // when
        val actual = kotest.increaseAndGet(value)

        // then
        val realArg = argSlot.captured
        realArg shouldBe value
        actual shouldBe value + 1
        println(actual)
    }
})