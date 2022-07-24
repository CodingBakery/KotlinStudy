Kotlin Reflection
===

#### 참고 사이트
https://kyyeto.tistory.com/entry/코틀린-코틀린의-리플렉션Reflection

## 1. 리플랙트가 뭘까?
- 단순 클랙스명을 객체로 만들어준다. ( String을 Class 인스턴스로..)
- 클래스의 프로퍼티, 메소드명을 별도로 분석해서 쓸수 있다. (Class를 Json 으로)
- 다양한 방법으로 코딩 시 사용 가능.

## 2. 리플랫트 살짝 맛보기
- 예전에는 String을 Instance로 바꾸는 기능을 많이 썼는데..
- 코틀린은.. 클래스의 구조를 가지고 오는데 많이사용하는 느낌?
- 구조라고??? 고참함수에도 구조 넣는 부분이 있었는데 ?!?!
~~~kotlin
data class Person (val name: String, val age: Int) {
    fun printAllProps() {
        println("name: ${name}, age: $age")
    }
}

fun main() {
    // 클래스 정보 가져오기
    val personClass: KClass<Person> = Person::class
    println(personClass.simpleName) // Person
    
    personClass.java.methods.forEach(::println)
    println()
    personClass.memberProperties.forEach(::println)
    println()

    // 인스턴스 생성 & 메서드 호출
    val constructor = personClass.primaryConstructor
    val params = listOf("Yoon", 10)
    val personInstance = constructor?.call(*params.toTypedArray())
    // vararg 를 인자로 받는 fucntion 에 array 를 넘겨주고 싶은 경우(매개변수로 사용하고 싶은 경우)  * 을 앞에 붙여주면 되네요.
    // 이걸 spread operator 라고 하네요.
    // spread operator -> https://kotlinlang.org/docs/functions.html#variable-number-of-arguments-varargs
    personInstance?.printAllProps() // name: Yoon, age: 10

    println()
    // 인스턴스로부터 클래스정보 가져오기
    println(personInstance?.javaClass?.simpleName) // Person

    println()
    // java lang
    val person = Class.forName("com.brandon.reflection.Person")
    println(person.simpleName) // Person
}
~~~
>Person  
>public final java.lang.String com.brandon.reflection.Person.getName()  
public boolean com.brandon.reflection.Person.equals(java.lang.Object)  
public java.lang.String com.brandon.reflection.Person.toString()  
public int com.brandon.reflection.Person.hashCode()  
public final com.brandon.reflection.Person com.brandon.reflection.Person.copy(java.lang.String,int)  
public final int com.brandon.reflection.Person.getAge()  
public final java.lang.String com.brandon.reflection.Person.component1()  
public final int com.brandon.reflection.Person.component2()  
public static com.brandon.reflection.Person com.brandon.reflection.Person.copy$default(com.brandon.reflection.Person,java.lang.String,int,int,java.lang.Object)  
public final void com.brandon.reflection.Person.printAllProps()  
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException  
public final void java.lang.Object.wait() throws java.lang.InterruptedException  
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException  
public final native java.lang.Class java.lang.Object.getClass()  
public final native void java.lang.Object.notify()  
>public final native void java.lang.Object.notifyAll()  
> 
>val com.brandon.reflection.Person.age: kotlin.Int  
>val com.brandon.reflection.Person.name: kotlin.String
> 
>name: Yoon, age: 10
> 
>Person
> 
>Person

## 2. 생성자
- 예전에는 String을 Instance로 바꾸는 기능을 많이 썼는데..
- 코틀린은.. 클래스의 구조를 가지고 오는데 많이사용하는 느낌?
- 구조라고??? 고참함수에도 구조 넣는 부분이 있었는데 ?!?!
~~~kotlin
open class MyClass(no: Int) {
     constructor(no: Int, name: String): this(10) {}
     constructor(no: Int, name: String, email: String): this(10) {}
}

fun someFun(arg: KClass<*>) {
     val constructors = arg.constructors // 모든 생성자 정보
     for (constructor in constructors) {
          print("constructor->")
          val parameters =constructor.parameters
          for (parameter in parameters) {
               print("${parameter.name}: ${parameter.type}, ");
              }
          println()
         }
    
     print("primary constructor->")
     val primaryConstructor = arg.primaryConstructor // 주 생성자 정보
     if (primaryConstructor != null) {
          val parameters =primaryConstructor.parameters
          for (parameter in parameters) {
              print("${parameter.name}: ${parameter.type}, ");
              }
         }
}
fun main() {
    someFun(MyClass::class)
}
~~~

>constructor->no: kotlin.Int, name: kotlin.String,  
>constructor->no: kotlin.Int, name: kotlin.String, email: kotlin.String,  
>constructor->no: kotlin.Int,
> 
>primary constructor->no: kotlin.Int,
> 
## 3. Method List
- 상속 받지 않은, 확장함수 제외한 함수 리스트
- 확장함수를 제외한 함수리스트
- 확장함수 리스트
~~~kotlin
open class MethodSuperClass {
    fun superFun() {}
}

class MethodMyClass: SuperClass() {
    fun myFun() {}
    fun String.someFun() {}
}

fun methodSomeFun(arg: KClass<*>) {
    // 확장 함수를 제외한 클래스에 선언된 모든함수반환
    val functions= arg.declaredMemberFunctions
    println("[declaredFunctions]")
    for (function in functions) {
        println("${function.name}: ${function.returnType}")
    }
    println()
    // 확장함수를 제외한 클래스와 상위 클래스에 선언된 모든함수반환
    val functions2= arg.memberFunctions
    println("[memberFunctions]")
    for (function in functions2) {
        println("${function.name}: ${function.returnType}")
    }
    println()
    // 클래스에 선언된 확장함수반환
    val functions3= arg.declaredMemberExtensionFunctions
    println("[declaredMemberExtensionFunctions]")
    for (function in functions3) {
        println("${function.name}: ${function.returnType}")
    }
}

fun main()
{
    methodSomeFun(MethodMyClass::class)
}
~~~~
>[declaredFunctions]  
>myFun: kotlin.Unit  
>
>[memberFunctions]  
>myFun: kotlin.Unit  
equals: kotlin.Boolean  
hashCode: kotlin.Int  
>toString: kotlin.String  
>
>[declaredMemberExtensionFunctions]  
someFun: kotlin.Unit

## 4. Property List
- 상속 받지 않은, 확장함수 제외한 Prop 리스트
- 확장함수를 제외한 Prop리스트
- 확장Prop 리스트
~~~kotlin
open class SuperClass {
     val superVal: Int = 10
}

class ProMyClass(val no: Int): SuperClass() {
     val myVal: String = "hello"
     val String.someVal: String
      get() = "world"
}

fun proSomeFun(arg: KClass<*>) {
     // 확장 프로퍼티를 제외한 클래스에 선언된 모든 프로퍼티 반환
     val properties = arg.declaredMemberProperties
     println("[declaredMemberProperties]")
     for (property in properties) {
          println("${property.name}: ${property.returnType} ")
         }
    println()

    // 확장 프로퍼티를 제외한 클래스와 상위 클래스에 선언된 모든 프로퍼티 반환
     val properties2 = arg.memberProperties
     println("[memberProperties]")
     for (property in properties2) {
          println("${property.name}: ${property.returnType} ")
         }
    println()

    // 클래스에 선언된 확장 프로퍼티 반환
     val properties3 = arg.declaredMemberExtensionProperties
     println("[pdeclaredMemberExtensionProperties]")
     for (property in properties3) {
          println("${property.name}: ${property.returnType} ")
         }
}
fun main(){
    proSomeFun(ProMyClass::class)
}
~~~

>[declaredMemberProperties]  
myVal: kotlin.String  
>no: kotlin.Int  
>
>[memberProperties]  
myVal: kotlin.String  
no: kotlin.Int  
>superVal: kotlin.Int  
>
>[pdeclaredMemberExtensionProperties]  
someVal: kotlin.String 

## 6. Property Reference
- reflectionProperty(obj: Any?, arg: KProperty<*>) 
- 객체(Instance)는 Object는 Any로, 구조는 Object의 프로퍼티 정보를 받는다.


- 받는 인스턴스와 구조를 별개로 받음으로서 어떤 기능?도 만들수 있게 된다~~!!
~~~kotlin
class PropRefMyClass {
     val objVal: Int = 10
     var objVar: Int = 20
}

fun reflectionProperty(obj: Any?, arg: KProperty<*>) {
    println("property name: ${arg.name}, property type: ${arg.returnType}")
    if (obj != null) {
        println(arg.getter.call(obj))
    } else {
        println(arg.getter.call())
    }
}

fun reflectionMutableProperty(obj: Any?, arg: KMutableProperty<*>) {
     println("property name: ${arg.name}, property type: ${arg.returnType}")
    if (obj != null) {
        arg.setter.call(obj, 40)
        println(arg.getter.call(obj))
    } else {
        arg.setter.call(40)
        println(arg.getter.call())
    }
}

fun main() {
    val obj: PropRefMyClass = PropRefMyClass()
    reflectionProperty(obj, PropRefMyClass::objVal) // <- Property Reference
    reflectionMutableProperty(obj, PropRefMyClass::objVar) // Property Reference
}
~~~
>property name: objVal, property type: kotlin.Int  
10  
property name: objVar, property type: kotlin.Int  
40

## 6. Function Reference
- 클래스 외 함수는 바로 Function Reference로 지정할수 있지만...
- 클래스 내 함수는 Companion Object로 지정해야 Function Reference 지정 가능~!
- Property Reference 와는 다르게 순수함수를 제공하여 고차함수에서 사용할수 있다. 5번 보다 더 중요~~!!
~~~kotlin
fun funcRefmyFun(no: Int): Boolean {
    return no > 10
}

class FuncRefMyClass {
     fun myFun2(no: Int): Boolean {
         return no < 10
     }
}

class FuncRefMyClass2 {
    companion object {
        fun myFun2(no: Int): Boolean {
            return no < 10
        }
    }
}

fun main(){
    val funReference:KFunction<*> =::funcRefmyFun // <- Function Reference
    val funReference2:KFunction<*> =FuncRefMyClass::myFun2

    println(funReference.name)

    val array = arrayOf<Int>(10, 5, 30, 15)
    array.filter(::funcRefmyFun).forEach{ print("${it}, ") } //<- Function Reference

    println()
    //ERROR!! array.filter(FuncRefMyClass::myFun2).forEach{ print("${it}, ") } // <- Function Reference
    array.filter(FuncRefMyClass2::myFun2).forEach{ print("${it}, ") } // <- Function Reference 

}
~~~


### 다시한번 Dele 를 볼까용?!
- 인스턴스 var, 구조는 map 를 받아서
- byFunc로 함수화 한다음
- byFunc 함수를 userid, count에 관례(by 키델)에 따라 위임한다.
- 프로퍼티를 지정할때는 "ref:Any?, prop:KProperty<*>" 요런식이 고정~!
- 함수를 지정할때는 "KFunction<*>" 요런식이 고정~!
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
>eager20  
0  
userid = eager20  
count = 0  