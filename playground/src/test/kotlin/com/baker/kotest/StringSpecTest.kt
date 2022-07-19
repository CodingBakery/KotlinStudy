package com.baker.kotest

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.startWith
import io.mockk.every
import io.mockk.mockk

class StringSpecTest : StringSpec({

    "length should return size of string" {
        println("hello")
        "hello".length shouldBe 5
    }

    "startsWith should test for a prefix" {
        println("world")
        "world" should startWith("wor")
    }

    "클래스 호출하기" {
        // given
        val kotestMock = mockk<Kotest>()
        every { kotestMock.executeTest() } returns "haha"

        // when && then
        kotestMock.executeTest() shouldNotBe null
        kotestMock.executeTest() should startWith("haha")
    }
})