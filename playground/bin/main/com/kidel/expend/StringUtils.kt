package com.kidel.expend

/**
 * 문자열 확장함수 만들기
 */
fun String.getDecorated(prefix: String = "##", postfix: String = "##"): String = "$prefix $this $postfix"

fun String.getHello(): String = "Hello $this"
