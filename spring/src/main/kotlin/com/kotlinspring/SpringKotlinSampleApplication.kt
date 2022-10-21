package com.kotlinspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringKotlinSampleApplication

fun main(args: Array<String>) {
    runApplication<SpringKotlinSampleApplication>(*args)
}
