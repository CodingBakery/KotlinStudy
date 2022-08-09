package com.kidel.coroutines

import kotlinx.coroutines.*
import java.lang.System.*

fun main() {
    val time = currentTimeMillis()

    GlobalScope.launch {
        delay(100)
        println("Task 1 finished ## ${currentTimeMillis() - time}ms")
    }

    GlobalScope.launch {
        delay(100)
        println("Task 1 finished ## ${currentTimeMillis() - time}ms")
    }

    Thread.sleep(200)
}