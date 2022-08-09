package com.kidel.expend

fun main() {
    fun Mouse.hello() = println("Hello!")
    fun WirelessMouse.hello() = println("Wireless Hello!")

    // 오버라이드되지 않는다. 정적으로 추가되기 때문
    val mouse: Mouse = WirelessMouse()
    mouse.hello()
}

open class Mouse {
    open fun click() = println("click!")
}

class WirelessMouse: Mouse() {
    override fun click() {
        println("wireless click!")
    }
}