package com.kidel.expend

fun List<Int>.getPartialAverage(fromIndex: Int, toIndex: Int): Double = this.subList(fromIndex, toIndex).average()
