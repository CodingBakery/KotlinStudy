package com.baker.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    println("예제 1")
    println("--------------------------")
    example1()
    println("")
    println("")
    println("")

    println("예제 2")
    println("--------------------------")
    example2()
    println("")
    println("")

    println("예제 3")
    println("--------------------------")
    example3()
    println("")
    println("")

    println("예제 4")
    println("--------------------------")
    runBlocking {
        example4(10)
    }
    println("")
    println("")

    println("예제 5")
    println("--------------------------")
    runBlocking {
        example5(10)
    }
    println("")
    println("")

    println("예제 6")
    println("--------------------------")
    runBlocking {
        val deferred = GlobalScope.async {
            delay(1000L)
            print("World!")
            "리턴값"
        }
        print("Hello, ")
        val result = deferred.await()
        print(result)
    }
    println("")
    println("")
}


/**
 * runBlocking 내의 코루틴이 모두 완료될때까지 blocking 하는 예제
 */
fun example1() {
    runBlocking {
        launch {
            delay(1000L)
            print("World!")
        }

        print("Hello, ")
    }
}

/**
 * 코루틴을 여러개 실행하면.. 위와 같이 job들을 관리해주기 힘들다. 이런 문제는, GlobalScope 이기 때문이다.
 * top-level 코루틴(GlobalScope)와 runBlocking 코루틴은 구조적으로 관계가 없다. 따라서 GlobalScope 에서 코루틴이 끝나든 말든 상관없이 join이 없으면 메인함수가 끝나버린다.
 */
fun example2() {
    runBlocking {
        val job = GlobalScope.launch {
            delay(1000L)
            print("World!")
        }

        print("Hello, ")
        job.join()  // 작업 일시 중단

        // 중단된 작업 완료 후 아래 코드 재개
        print(" Waiting complete")
    }
}

fun example3() {
    GlobalScope.launch {
        delay(1000L) // non-blocking delay for 1 second
        print("World!") // print after delay
    }
    print("Hello, ") // main thread continues while coroutine is delayed
    runBlocking {
        delay(2000L) // block main thread for 2 seconds to keep JVM alive
    }
}

suspend fun example4(amount: Int) {
    println("Make $amount coroutine(s) threads & time count test")
    println("${Thread.activeCount()} threads active at the start")
    val time = measureTimeMillis {
        val jobs = ArrayList<Job>()
        for (i in 1..amount) {
            jobs += GlobalScope.launch {
                println("Start Corountine: $i at Thread: ${Thread.currentThread().name}")
                delay(1000)
                println("Finish Corountine: $i at Thread: ${Thread.currentThread().name}")
            }
        }
        jobs.forEach {
            it.join()
        }
    }
    println("${Thread.activeCount()} threads active at the end")
    println("Took $time ms\n")
}

suspend fun example5(amount: Int) {
    val dispatcherThreadPool = newFixedThreadPoolContext(10, "threadpool")
    println("Make $amount coroutine(s) threads & time count test")
    println("${Thread.activeCount()} threads active at the start")
    val time = measureTimeMillis {
        val jobs = ArrayList<Job>()
        for (i in 1..amount) {
            jobs += GlobalScope.launch(dispatcherThreadPool) {
                println("Start Corountine: $i at Thread: ${Thread.currentThread().name}")
                delay(1000)
                println("Finish Corountine: $i at Thread: ${Thread.currentThread().name}")
            }
        }
        jobs.forEach {
            it.join()
        }
    }
    println("${Thread.activeCount()} threads active at the end")
    println("Took $time ms\n")
}