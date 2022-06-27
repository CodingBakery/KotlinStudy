package com.kidel.expend

fun main() {
    val keyboard = Keyboard(1234, Switch.GATERON_RED, "키크론 K3", "keychron", 120_000)

    releaseKeyboard1(keyboard)
    releaseKeyboard2(keyboard)
    releaseKeyboard3(keyboard)
    releaseKeyboard4(keyboard)
}

enum class Switch {
    GATERON_RED, CHERRY_RED, CHERRY_BLACK, CHERRY_BLUE, EVERGLIDE, HOLLYPANDAR
}

class Keyboard(val id:Int, val switch: Switch, val name: String, val company: String, val price: Int)

/** 중복되는 유효성 검증 로직 */
fun releaseKeyboard1(keyboard: Keyboard) {
    if (keyboard.name.isEmpty()) {
        throw IllegalArgumentException("${keyboard.id}: Empty name!")
    }

    if (keyboard.company.isEmpty()) {
        throw IllegalArgumentException("${keyboard.id}: Empty company!")
    }

    println("### NEW ARRIVAL: ${keyboard.name} [${keyboard.switch}] ###")
}

/** [개선 1] 공통 로직을 로컬 함수로 정의해보자. */
fun releaseKeyboard2(keyboard: Keyboard) {

    fun validate(keyboard: Keyboard, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("${keyboard.id}: Empty $fieldName!")
        }
    }

    validate(keyboard, keyboard.name, "name")
    validate(keyboard, keyboard.company, "company")

    println("### NEW ARRIVAL: ${keyboard.name} [${keyboard.switch}] ###")
}

/** [개선 2] 로컬 함수 내에서 바깥 함수의 파라미터에 직접 접근 */
fun releaseKeyboard3(keyboard: Keyboard) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("${keyboard.id}: Empty $fieldName!")
        }
    }

    validate(keyboard.name, "name")
    validate(keyboard.company, "company")

    println("### NEW ARRIVAL: ${keyboard.name} [${keyboard.switch}] ###")
}

/** [개선 4] 확장 함수로 정의 */
fun Keyboard.validateBeforeRelease() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("$id: Empty $fieldName!")
        }
    }

    validate(name, "name")
    validate(company, "company")
}

fun releaseKeyboard4(keyboard: Keyboard) {
    keyboard.validateBeforeRelease()
    println("### NEW ARRIVAL: ${keyboard.name} [${keyboard.switch}] ###")
}
