Companion Object & Factory Pattern
===

#### 참고 사이트 
- https://www.bsidesoft.com/8187
- https://www.bsidesoft.com/8174

## 1. Companion Object ?! like Static?! 
- Companion Object은 Java의 Static과 같은가?! No~!!!
- 단순히 인스턴스에 올라가는 메모리 영역이 아닌, 변수도 함수도 될 수 있으며,
- 클래스내 Companion Object 딱하나 쓸 수 있다.

~~~ kotlin
class CmpnonObjTest1 {
    companion object{
        val prop = "나는 Companion object의 속성이다."
        fun method() = "나는 Companion object의 메소드다."
    }
}
fun main(args: Array<String>) {
    //사실은 MyClass2.맴버는 MyClass2.Companion.맴버의 축약표현이다.
    println(CmpnonObjTest1.Companion.prop)
    println(CmpnonObjTest1.Companion.method())
}
~~~
- 이름 지정도 가능하다~!
- But 기존 처럼 "Companion"는 사용할수 없당~!
~~~kotlin
class CmpnonObjTest2 {
    companion object factory{
        val prop = "나는 Companion object의 속성이다."
        fun method() = "나는 Companion object의 메소드다."
    }
}
fun main(args: Array<String>) {
    //사실은 MyClass2.맴버는 MyClass2.Companion.맴버의 축약표현이다.
    println(CmpnonObjTest2.factory.prop)
    println(CmpnonObjTest2.factory.method())

    val comp1 = CmpnonObjTest2.factory // -- (3)
    println(comp1.prop)
    println(comp1.method())
    val comp2 = CmpnonObjTest2 // -- (4)
    println(comp2.prop)
    println(comp2.method())
//    val comp3 = CmpnonObjTest2.Companion // -- (5) 에러발생!!!
//    println(comp3.prop)
//    println(comp3.method())
}
~~~

### 인쟈..... 디자인패턴을 입혀봐야 하는데....
- 함수형 프로그래밍에서 Is-A 구조의 디자인 패턴을 쓰는것이 의미가 있을까?
- Has-A 구조를 선호하는 함수형 프로그래밍에서 생각하는 패턴 전략은 무었인가?
- 내가 생각하기로는 스터를 해오면서.. Composition을 이용한 위임으로 이런 패턴전략을 가져가야 한다고 생각했다.
- 그래서 팩토리패턴을 By을 알아가는 주제로 바꿔야 할것 같았다.


By : 구성(Compoistion)을 이용한 위임(Delegation) 전략
====
#### 참고사이트
- https://velog.io/@haero_kim/Kotlin-by-%ED%82%A4%EC%9B%8C%EB%93%9C%EC%9D%98-%EC%97%AD%ED%95%A0-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0
- https://www.bsidesoft.com/8146
### 아래 코드를 보면 Entity라는 클래스에 By로 먼가를 해놓았다.
~~~kotlin
class Entity{
  var userid by Field("hika")
  var count by Field(0)
}
~~~
- userid 에 Feild라는 클래스를 생성해서 입혀 놓았다.
- "=" 과는 다르게 단순한 변수 선언이 아니라 userid Field클래스의 모든 기능을 위임하게 된다.

## 1. By 그는 무엇인가?
~~~kotlin
interface IWindow {
    fun getWidth() : Int
    fun getHeight() : Int
}
~~~
~~~kotlin
open class TransparentWindow : IWindow {
    override fun getWidth(): Int {
        return 100
    }

    override fun getHeight() : Int{
        return 150
    }
}
~~~
- 위와 같은 TransparentWindow 클래스가 있다.
- TransparentWindow 를 구성을 이용한 위임받은 클래스 UI 클래스를 만들려면 아래와 같이 나온다.
~~~kotlin
class UI(window: IWindow) : IWindow {
    val mWindow: IWindow = window

    override fun getWidth(): Int {
        return mWindow.getWidth()
    }

    override fun getHeight(): Int {
        return mWindow.getHeight()
    }
}
~~~
### 코트린에선....
- 아래 한줄로 끝난다.!!!!
- 구성을 이용한 위임을 키워드 하나로 바꿔버리는 코틀린의 위엄~~!!
~~~kotlin
class UI(window: IWindow) : IWindow by window { }
~~~

## 2. by 와 = 차이?!
- 근데 사용하는게 어쩨 "=" 이랑 같은거 아녀?? 라고 생각이 들수 있다. 
- 그래서 준비한 아래 내용.
- by 는 해당 클래스의 getValue을 실행하여 값을 가져온다. 
~~~~kotlin
class byCalss {
    var userid by Field("eager20")
    var count by Field(0)
}

class Field<T:Any>(private var value:T){
    operator fun getValue(ref:Any?, prop: KProperty<*>) = value
    operator fun setValue(ref:Any?, prop: KProperty<*>, v:T){
        value = v
    }
}

fun main(args: Array<String>) {
    val value = byCalss() ;
    println( value.userid   )
    println( value.count )
}
~~~~
> 결과
> eager20
> 0

- = 는 해당 클래스의 Field라는 클랙스는 제네릭으로만 선언되었기 때문에
- 그냥 해당 클래서의 오브젝트만을 가져오게 된다.
~~~kotlin
class byTest {
    var userid = Field2("eager20")
    var count = Field2(0)
}

class Field2<T:Any>(private var value:T){
    operator fun getValue(ref:Any?, prop: KProperty<*>) = value
    operator fun setValue(ref:Any?, prop: KProperty<*>, v:T){
        value = v
    }
}

fun main(args: Array<String>) {
    val value = byTest() ;
    println( value.toString()   )
    println( value.userid   )
    println( value.count )
}
~~~
> 결과
> com.brandon.cmpnObj.byTest@38af3868
> com.brandon.cmpnObj.Field2@77459877
> com.brandon.cmpnObj.Field2@5b2133b1

## 3. 심플한 사용방법
- 멤버필드 userid 가 맵key값으로 인지 되어 들어가는 마법..
~~~kotlin
package com.brandon.cmpnObj

class Entity{
    val map = mutableMapOf<String, Any>()
    var userid:String by map
    var count:Int by map
    init{
        map["userid"] = "hika"
        map["count"] = 0
    }
}

fun main(args: Array<String>) {

    val value = Entity() ;
    println( value.userid )
    println( value.count )

}
~~~
> 결과
> hika
> 0

## 4. 리플렉션과 by를 이용한 콜라브레이숀..
- prop.name로 userid 혹은 count이름을 가져올수 있으며 (Reflection) 해당 값을 Map에 값을 넣는다.
- Dele 클래스를 만들어 입력받은 map과 입력받은 값을 이용하여 해당 클래스에 prop.name을 알아내 값을 이용
~~~kotlin
class ByFunction {
    val map = mutableMapOf<String, Any>()
    private fun <T:Any> byFunc(v:T) = Dele(v, map)

    val userid by byFunc("eager20")
    val count by byFunc(0)

}

class Dele<T:Any>(private var param1:T, private val map:MutableMap<String, Any>){
    operator fun getValue(ref:Any?, prop:KProperty<*>):T{
        if(prop.name !in map) map[prop.name] = param1 // 맵에 없으면 넣는데..
        return map[prop.name] as T
    }
    operator fun setValue(ref:Any?, prop:KProperty<*>, v:T){
        map[prop.name] = v
    }
}

fun main(args: Array<String>) {

    val value = ByFunction()
    value.map.forEach( { k, v ->
        println("$k = $v")
    });
    // Dele에서 get을 할때 값을 최초로 넣기 때문에 값이 없다.. 해결은 ByFunction2 에서

    println( value.userid )
    println( value.count )

    value.map.forEach( { k, v ->
        println("$k = $v")
    });

}
~~~
> 결과
> eager20
> 0
> userid = eager20
> count = 0
