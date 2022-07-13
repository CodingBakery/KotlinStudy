package com.kidel.expend


fun main() {
    /* 같은 패키지에 있으면 임포트하지 않고 쓸 수 있다. */
    val numberList = listOf(1, 55, 66, 20, 5)
    val startIndex = 2
    val endIndex = 3
    val sliceAvg = numberList.getPartialAverage(startIndex, endIndex)

    "".lastIndex

    println("AVG from $startIndex to $endIndex => $sliceAvg")
}
