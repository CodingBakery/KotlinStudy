package com.brandon.collect

import java.security.SecureRandom

data class Struct(val value: String, val length: Int)



fun main(args: Array<String>) {

    var listA = listOf<String>("res", "Program", "Tutorial")

    var lenA = listA.map { it -> it.length }

    var classA = listA.map { it -> Struct(it, it.length) }
    var total1 = lenA.reduce { acc, i -> acc +i  }
    var total2 = lenA.foldRightIndexed(100 , { idx, acc, i -> acc +i  })
    println("TEST")

    println(lenA)
    println(classA)
    println(total1)
    println(total2)
    println("##################################")

    val list = listOf(1, 2, 3, 4, 5)
    val sumLeftTotal = list.fold(100, { next, total ->
        println("next $next total $total")
        total + next
    })
    println("sum LeftTotal $sumLeftTotal")


    println("##################################")


    val sumRightTotal = list.foldRight(100, { next, total ->
        println("next $next total $total")
        total + next
    })
    println("sum RightTotal $sumRightTotal")


    println("##################################")
    val sumLeftIndexTotal = list.foldIndexed(100, { idx, next, total ->
        println("idx $idx next $next total $total")
        total + next
    })
    println("sum LeftIndexTotal $sumLeftIndexTotal")



    val temp = list.filter { it > 3 }. foldIndexed(100, { idx, next, total ->
        println("idx $idx next $next total $total")
        total + next
    })

    println("sum temp $temp")

    val secureRandom = SecureRandom()

    println(secureRandom.nextInt(100))  // 0 <= n < 100
    println(secureRandom.nextInt(100))  // 0 <= n < 100
    println(secureRandom.nextDouble())
    println(secureRandom.nextDouble())

}