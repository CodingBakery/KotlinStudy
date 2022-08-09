package com.brandon.composition

// 자동차 안에는 엔진이라는 객체가 존재 하며, 엔진이 없으면 차는 구성될 수가 없다.
// 엔진객체는 자동차 객체가 없으질때 같이 소멸됩니다. (생명주기 공동체~)

// 엔진은 자동차 외부에서 정보를 알수 없으며, 단지 행위만을 오픈함.
// 엔진 new 를 자동차 안에서 함~!!

class Car(val name: String, val power: String) {

    private var engine = Engine(power) // Engine클래스 객체는 Car에 의존적이다

    fun startEngine() = engine.start()
    fun stopEngine() = engine.stop()
}

class Engine(power: String) {
    fun start() = println("Engine has been started.")
    fun stop() = println("Engine has been stopped.")
}

fun main() {
    val car = Car("tico", "100hp")
    car.startEngine()
    car.stopEngine()
}