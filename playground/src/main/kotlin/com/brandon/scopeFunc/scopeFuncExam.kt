package com.brandon.scopeFunc

fun  main(){

    letTest()
    println("===========================")
    alsoTest()
    println("===========================")
    runTest()
    println("===========================")
    applyTest()

    var car = Car("ray")
    with(car){
        name = "root"
    }
    println("with "+car)

}

fun letTest(){
    var car = Car("letOrigin")
    var tmp1 = car.let{
        it.name="test"
        "test"
    }
    println("letTest USE it! ")
    println("letTest "+car)
    println("letTest rslt "+tmp1)
}

fun alsoTest(){
    var car = Car("alsoOrigin")
    var tmp = car.also{
        it.name="test"
        "test"
    }
    println("alsoTest USE it! ")
    println("alsoTest "+car)
    println("alsoTest rslt "+tmp)
}

fun runTest(){
    var car = Car("runOrigin")
    var tmp2 = car.run{
        name="test"
        showName()
        "test"
    }
    println("runTest USE it! ")
    println("runTest "+car)
    println("runTest rslt "+tmp2)
}
data class Car(var name : String){
    fun showName(){
        println("Method~~ ${name}님")
    }
}
fun applyTest(){
    var car = Car("applyOrigin")
    var tcar = car.apply{
        name = "poo"
        showName()
        "test"
    }
    println("applyTest USE it! ")
    println("applyTest "+car)
    println("applyTest rslt "+tcar)

}

