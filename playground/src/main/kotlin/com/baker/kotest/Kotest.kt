package com.baker.kotest

class Kotest(val childKotest: ChildKotest) {
    fun executeTest(): String {
        return "executed"
    }

    fun executeChild(): String {
        return childKotest.executeTest()
    }

    fun increaseAndGet(value: Int): Int {
        return childKotest.increaseAndGet(value)
    }

    fun printSquare(value: Int): Int {
        return value * value
    }
}

class ChildKotest {
    fun executeTest(): String {
        return "child executed"
    }

    fun increaseAndGet(value: Int): Int {
        return value + 1
    }
}