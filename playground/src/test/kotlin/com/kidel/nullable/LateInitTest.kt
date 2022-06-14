package com.kidel.nullable

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


/**
 * 케이스 1: 선언하고 null로 초기화해두면?
 */
class LateInitTest {
    private var testService: TestService? = null

    @BeforeEach
    fun setUp() {
        // 테스트에서는 쓸 수밖에 없는 로직.
        testService = TestService()
    }

    @Test
    fun testServicePerform_safe_call() {
        println(testService?.perform())     // null이 아닌 걸 아는데 매번 safe call..
    }

    @Test
    fun testServicePerform_not_null() {
        println(testService!!.perform())    // 컴파일러 말고 사람이 파악해야 하는 로직..
    }
}


/**
 * 케이스 2: 지연 초기화 이용
 */
//class LateInitTest {
//    private lateinit var testService: TestService
//
//    // TODO: JUnit5의 @BeforeAll: companion object 생성, JvmStatic으로 지정..
//
//    @BeforeEach
//    fun setUp() {
//        testService = TestService()
//    }
//
//    @Test
//    fun testServicePerform_safe_call() {
//        println(testService.perform())
//    }
//}

///**
// * 케이스 2-?: 지연 초기화 이용
// */
//class LateInitTest {
//    private lateinit var testService: TestService
//
//    @BeforeEach
//    fun setUp() {
//        println("lateinit was fake!")
//    }
//
//    @Test
//    fun testServicePerform_safe_call() {
//        println(testService.perform())  // lateinit property testService has not been initialized
//    }
//}

/***/
class TestService {
    fun perform(): String = "performed."
}