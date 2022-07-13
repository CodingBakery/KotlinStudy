package com.kidel.operation

fun main() {

    /**
     * -------------------------------------------
     * #### 예제1: 연산자는 함수 호출에 대한 편의 문법 ####
     * -------------------------------------------
     */
    "cmd 버튼을 누른 상태로 + 연산자를 클릭하면 " + "내부 호출이 나옵니다."
    20 + 21  // 타입별로 다르게 정의된 plus() 연산이 있어요.

    /**
     * -----------------------------
     * #### 예제2: 연산자 오버라이딩 ####
     * -----------------------------
     */
    val fourDollar = Money(4)
    val oneDollar = Money(1)

    println("#### 예제2 ####")
    println("fourDollar + oneDollar = ${(fourDollar + oneDollar).value} 달러")
    println("fourDollar.minus(oneDollar) == ${(fourDollar.minus(oneDollar)).value} 달러")
    println("fourDollar * 3 == ${(fourDollar * 3).value} 달러")
    println("3 * fourDollar == ${(3 * fourDollar).value} 달러")   // 교환법칙
    // 20 + "번" // 문제 없는 식 같지만? 20의 타입에 정의된 plus 함수를 호출해야 한다. => 20이 Int인지, Float인지 정확하게 추론할 수 없어 컴파일 에러 발생

    /**
     * ----------------------------
     * #### 예제3: 복합 대입 연산자 ####
     * ----------------------------
     */
    println("#### 예제3 ####")

    var money = Money(5)
    money *= 3
    println("money *= 3: ${money.value} 달러")

    var balloon = Balloon(5)
    // balloon *= 3 // 컴파일 에러

    /**
     * --------------------------------
     * #### 예제4: 자바와 함께 사용할 때 ####
     * --------------------------------
     */
    println("#### 예제4 ####")
//    val oneLevel = Level(1)
//    val twoLevel = Level(2)
//    println("oneLevel + twoLevel = ${oneLevel + twoLevel}") // 컴파일 에러는 안 남 (실행하면 NoClassDefFoundError...)

    /**
     * ------------------------
     * #### 예제5: 동등성 비교 ####
     * ------------------------
     */
    println("#### 예제5 ####")
    val saDollar = Money(4)
    println("data 클래스로 자동 생성: 사딸라! ${saDollar == fourDollar}")

    val smallBalloon = Balloon(1)
    val bigBalloon = Balloon(100)
    println("equals를 직접 재정의: ${smallBalloon == bigBalloon}")
}

class Balloon(var size: Int) {
    operator fun times(time: Int): Balloon {
        return Balloon(size * time)
    }

    operator fun timesAssign(time: Int) {
        size *= time
    }

    override fun equals(other: Any?): Boolean {
        return other is Balloon && size == other.size
    }
}

data class Money(val value: Int) {
    operator fun plus(m: Money): Money {
        return Money(this.value + m.value)
    }

    fun minus(m: Money): Money {
        return Money(this.value - m.value)
    }
}

operator fun Money.times(time: Int): Money {
    return Money(this.value * time)
}

/**
 * 교환법칙을 위한 정의
 */
operator fun Int.times(m: Money): Money {
    return Money(m.value * this)
}

private operator fun String.times(s: Int): String {
    var result = ""
    for (time in 1..s)
        result += this
    return result
}
