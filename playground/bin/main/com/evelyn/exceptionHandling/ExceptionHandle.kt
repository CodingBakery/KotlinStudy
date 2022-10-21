package com.evelyn.exceptionHandling

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.io.StringReader
import kotlin.jvm.Throws

fun main() {
    checkPercentage(100)
    checkNumber(10)
//    checkNumber2(null)

//    val reader = BufferedReader(StringReader("123"))
//    println(readNumber(reader))
//
//    val reader2 = BufferedReader(StringReader("abcd"))
//    println(readNumber2(reader2))

//    val reader3 = BufferedReader(StringReader("not a number"))
//    readNumber3(reader3)

    useRunCatching(60);
}

// throw로 exception 던지기, new 키워드는 필요없다.
fun checkPercentage(percentage: Int) {
    if (percentage !in 0..100) {
        throw IllegalArgumentException("A percentage value must be between 0 and 100: $percentage")
    }
}

// throw는 식이므로 다른 식에 포함될 수 있다.
fun checkNumber(number: Int): Int {
    val percentage =
        if (number in 0..100) {
            number
        } else {
            throw IllegalArgumentException("A number value must be between 0 and 100: $number")
        }
    return percentage
}
// Elvis 표현식의 일부로 사용
fun checkNumber2(message: String?): Int {
    return message?.length ?: throw IllegalArgumentException("String is null")
}

// throw 표현식은 Nothing 유형의 값을 반환 한다.
fun abstractException(message: String): Nothing {
    throw RuntimeException(message)
}

// try ~ catch
fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line) // 숫자형식이 아닌 경우 에러 발생.
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close() // 무조건 실행되는 부분.
    }
}
// 다중 캐치 블록
fun multipleCatch() {
    try {
        val result = 25 / 0
        result
    } catch (exception: NumberFormatException) {
        // ...
    } catch (exception: ArithmeticException) {
        // ...
    } catch (exception: Exception) {
        // ...
    }
}

// checked/unchecked exception 구별이 없음. 예외처리를 강제하지 않는다.
@Throws(Exception::class)
fun readNumber2(reader: BufferedReader): Int {
    val line = reader.readLine()
    return Integer.parseInt(line)
}

// try 식 사용하기
fun readNumber3(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return // null로 바꾸면 null을 반환
    } finally {
        println("this is finally block") // 항상 실행
    }
    println(number)
}

// 코틀린 use함수로 자원 관리
// java의 try-with-resource 와 같은 기능을 제공한다.
fun readLineFromFile(path: String): String {
    BufferedReader(FileReader(path)).use { br ->
        return br.readLine()
    }
}

// Result , runCatching
fun useRunCatching(num: Int) {
    val result:Result<String> = runCatching {
        if(num > 50) "Success!!"
        else throw Exception()
    }.onSuccess{
        println("runCatching - success")
    }.onFailure {
        println("runCatching - failure")
    }

    if (result.isSuccess) { }
    if (result.isFailure) { }

    println("result : " + result.getOrNull())
}


