package com.baker.kotest

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AnnotationSpecTest: AnnotationSpec() {

    @BeforeEach
    fun beforeTest() {
        println("before each test")
    }

    @Test
    fun `Kotest 클래스의 executeTest를 실행하면 결과값이 리턴된다`() {
        // given
        val kotest = mockk<Kotest>()
        val expected = "메롱"
        every { kotest.executeTest() } returns expected

        // when
        val actual = kotest.executeTest()

        // then
        actual shouldBe expected
        println(expected)
    }

    @Test
    fun `Kotest 클래스에서 printSquare를 호출하면 제곱된값 리턴된다`() {
        // given
        val childKotest = mockk<ChildKotest>()
        val kotest = Kotest(childKotest)
        val value = 3
        val expected = 9

        // when
        val actual = kotest.printSquare(value)

        // then
        actual shouldBe expected
        println(expected)
    }
}