package com.kidel.expend2

import com.kidel.expend.getPartialAverage
import com.kidel.expend.getPartialAverage as partAvg    // 다른 이름으로도 호출할 수 있다~

fun main() {
    /* 다른 패키지에서 확장 함수를 사용하려면 임포트 */
    val numberList = listOf(1, 55, 66, 20, 5)
    val startIndex = 2
    val endIndex = 3
    val sliceAvg = numberList.getPartialAverage(startIndex, endIndex)   // 정적 메소드이지만 멤버처럼 쓸 수 있다.
    val sliceAvg2 = numberList.partAvg(startIndex, endIndex)

    println("AVG from $startIndex to $endIndex => $sliceAvg")
    println("AVG from $startIndex to $endIndex => $sliceAvg2")
}