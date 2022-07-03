Scope Funciton
===

## 스코프 함수란?
- 람다식을 이용하여 List나 항목들을 중복코드 없이 사용
- Stream Map과도 유사하지만.. Stream이 아니여도 사용이 가능.
- 참고 사이트
  : https://0391kjy.tistory.com/25
  : http://batmask.net/index.php/2021/12/10/286/

## 차이점
### **2가지 주요 차이점이 존재..**
- 람다는 입력 파라미터 지정이 있지만, 위 같은 경우 입력파라메터?에 해당하는 지정이 필요하다
  (it, this:지정 없이 해당 Object메소드 바로 사용)
- 리턴하는 값이 입력으로 지정 받은 값인가?, 아님 다른 값인가? (Lamba OR Context)

ScopeFunction | it | this (none it)
---- | ---- | ---- 
Lamba Return | **1. let** | **3. run**
Context Return | **2.also** | **4. apply**

### **브랜든 해석~?!**
- it Return : 들어온걸 살짝바꾸던지, 그냥 나가던지 들어온 객체가 리턴된다. 다른건 못나감.
- AnyReturn : 들어온걸 리턴해도 되고, 아님 머든지 다 리턴한다.
- it : 들어온 객체를 "it" 으로 지정해서 사용.
- inner method : with 처럼 맴버변수, 메소드를 그냥 사용할수 있다.

ScopeFunction | it | inner method
---- | ---- | ---- 
Any Return | **1. let** | **3. run**
it Return | **2. also** | **4. apply**

### 1. let (it, Any Return)
- 파라메터?! 객체를 it으로 지칭하며, 람다식으로 응답한 값을 리턴(Any Result)한다.
~~~
data class Car(var name : String)
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
~~~
> **Result**
> letTest USE it!
> letTest Car(name=test)
> letTest rslt test

### 2. also (it, it Return)
- 파라메터?! 객체를 it으로 지칭하며, it 객체가 변경된 객체를 리턴
- 물론 it기존 객체도 값이 변경되어 있음.
~~~
data class Car(var name : String)
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
~~~
> **Result**
> alsoTest USE it!
> alsoTest Car(name=test)
> alsoTest rslt Car(name=test)


### 3. run ( this, Any Return)
- 파라메터?! 객체를 메소드를 바로 호출 가능, 람다식으로 응답한 값을 리턴(Any Result)한다.
- 물론 기존 객체도 값이 변경되어 있음.
~~~
data class Car(var name : String){
    fun showName(){
        println("Method~~ ${name}님")
    }
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
~~~
> **Result**
> Method~~ test님
> runTest USE it!
> runTest Car(name=test)
> runTest rslt test


### 4. apply ( this,  it Return)
- 파라메터?! 객체를 메소드를 바로 호출 가능, it 객체가 변경된 객체를 리턴
- 물론 기존 객체도 값이 변경되어 있음.
~~~
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
~~~
> *** Result ***
> Method~~ poo님
> applyTest USE it!
> applyTest Car(name=poo)
> applyTest rslt Car(name=poo)
> with Car(name=root)

결론
====
- 내가 코드한다면 햇갈리니.. let이랑 also 만 쓰겠다...
- 굳이 it 안쓰고 맴버변수나 메소드 쓸필요가.. 이것도한 코드가 많아진다고 한다면 어쩔 수 없으나.. 난 걍 let, also 만 쓰겠다. ^^


### 0. with (코드줄임용~~)
- 이건 사용방법이 고정이라.. it에 해당하는 값만 바꾸는데 사용~
- also, apply랑 같은 기능.
- 정말코드를 줄이는 용도.. Entity에 값을 바꿀때 " EntityObject.setName(~~~) " 요런 코드가 다반사일때, "EntityObject." 를 안쓸수 있다.
~~~
    var car = Car("ray")

    with(car){
        name = "root"
    }
    println("with "+car)
~~~
> *** Result ***
> with Car(name=root)
