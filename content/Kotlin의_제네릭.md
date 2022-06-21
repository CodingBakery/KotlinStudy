# Kotlin의 제네릭

[TOC]

> Do it! 코틀린 프로그래밍 책을 기본으로 스터디 

### 제네릭(Generic)

: 클래스 내부에서 사용할 자료형을 인스턴스를 생성할 때 확정하는 것.

객체의 자료형을 컴파일 할 때 체크하기 때문에 객체 자료형의 안정성을 높이고 형 변환의 번거로움을 줄인다.

의도하지 않은 자료형의 객체를 지정하는 것을 막고 객체를 사용할 때 원래의 자료형으로 형 변환 시 발생할 수 있는 오류를 줄여주는 장점이 있고, 
인자의 자료형을 고정할 수 없거나 예측할 수 없을 때 타입 파라미터를 이용해 실행 시간에 자료형을 결정할 수 있게 되므로 편리하다.

------

#### 제네릭의 일반적인 사용법

- 앵글 브래킷(<>) 사이에 형식 매개변수(하나 이상)를 넣어 선언
- 형식 매개변수(Type Parameter) : 자료형을 대표하는 용어로 T와 같이 영문의 대문자로 사용하며 나중에 필요한 자료형으로 대체 됨
  - 일종의 규칙처럼 사용되는 이름 (변경 가능)
  - E (Element), K (Key), N (Number), T (Type), V (Value), S(두번째), U(세번째), V(네번째), ...

```kotlin
class Box<T>(t: T) {
  var name = t
}

fun main() {
  // 객체 생성 시 자료형이 결정 됨.
  val box1: Box<Int> = Box<Int>(1)
  val box2: Box<String> = Box<String>("Hello")
  
  // 객체 생성 시 생성자에서 유추될 수 있는 자료형이 있다면 <Int>, <String>은 다음과 같이 생략 가능.
  val box3 = Box(1)
  val box4 = Box("Hello")
}
```



##### 제네릭 클래스

: 형식 매개변수를 1개 이상 받는 클래스

- 형식 매개변수를 프로퍼티에 사용하는 경우 클래스 내부에서는 사용할 수 없다.(자료형이 특정되지 못하므로 인스턴스를 생성 할 수 없기 때문!)

```kotlin
class MyClass<T> {
  var myProp: T // 오류! 프로퍼티는 초기화되거나 abstract로 선언되어야 함
}
```

-  그 대신 주 생성자나 부 생성자에 형식 매개변수를 지정해서 사용할 수 있다.

> **주 생성자** 
> : 클래스 이름 옆에 괄호로 둘러쌓인 코드. 생성자 파라미터를 지정하고 그 생성자 파라미터에 의해 초기화되는 프로퍼티를 정의하는 두 가지 목적.
>
> **부 생성자**
> : 클래스 블록 내에 존재하는 생성자. constructor 키워드를 사용한다. (constructor 생략 불가)

```kotlin
class MyClass<T>(val myProp: T) {} // 주 생성자의 프로퍼티

class MyClass<T> {
  val myProp: T // 프로퍼티
  constructor(myProp: T) { // 부 생성자 이용
    this.myProp = myProp
  }
}

// 인스턴스를 생성할 때 명시적으로 자료형을 지정 할 수 있다.
var a = MyClass<Int>(12)
```



##### 제네릭 함수 / 메서드

: 형식 매개변수를 받는 함수나 메서드

* 형식매개변수를 fun 오른쪽에 선언

* 자료형의 결정은 함수가 호출될 때 컴파일러가 추론할 수 있고, 이 자료형은 반환 자료형과 매개변수 자료형에 사용 할 수 있다.

```markdown
fun <형식 매개변수[,...]> 함수이름(매개변수: <매개변수 자료형>[,...]): <반환 자료형>
```

```kotlin
fun <T> genericFunc(arg: T): T? {...} // 매개변수와 반환 자료형에 T가 사용됨

fun <K, V> put(key: K, value: V): Unit {...} // 형식 매개변수가 2개인 경우
```



##### 제네릭과 람다식

* 형식 매개변수로 선언된 함수의 매개변수를 연산할 경우에는 자료형을 결정할 수 없기 때문에 오류가 난다.

```kotlin
fun <T> add(a: T, b: T): T {
  return a + b // 오류! 자료형을 아직 결정할 수 없음.
}
```

* 람다식을 매개변수로 받으면 자료형을 결정하지 않아도 실행 시 람다식 본문을 넘겨줄 때 결정되므로 문제 해결 가능.

```kotlin
fun <T> add(a: T, b: T, op: (T, T) -> T): T {
  return op(a, b)
}

fun main() {
  val result = add(2, 3, {a, b -> a + b})
  // val result = add(2,3){a, b -> a + b}와 같이 표현 가능
  // 람다식 {a, b -> a + b}는 add()함수가 실행될 때 넘겨지는 인자 이므로 연산식을 함수 선언부에 직접 구현하지 않고 전달하는 방법 사용.
  println(result)
}
```



##### 형식 매개변수의 NULL 제어

* 제네릭의 형식 매개변수는 기본적으로 null 가능한 형태로 선언된다.
  * ?가 붙지 않아도 붙은 것과 같다.
  * 함수 내부에서 반드시 null check가 필요하다!

```kotlin
class Box<T>(t: T) {
  var name = t
}

val box1 = Box(null) // 기본적으로 null 허용
val box2: Box<String> = Box("nonNull") // non-null
val box3: Box<String?> = Box(null) // null 가능
```

* **<T: Any>** - null을 허용하지 않으려면 형식 매개변수에 upper bound를 지정

```kotlin
class GenericNull<T: Any> { // 자료형 Any가 upper bound로 지정되어 nul을 허용하지 않음
  ...
}

fun main() {
  val obj2 = GenericNull<Int?>() // 오류!
}
```



#### 자료형 제한 (Upper bound)

제네릭 클래스나 메서드가 받는 형식 매개변수를 특정한 자료형으로 제한할 수 있다. 
자바에서는 extends나 super를 사용했지만, 코틀린에서는 콜론(:)을 사용해 제한한다.

* 클래스 - class Calc <T: Number>
* 함수  - fun <T: Number> addLimit(a: T, b: T, op: (T, T) -> T): T { ... }
  * T에 Number 와 그 하위 자료형이 아닌 자료형이 들어가면 오류 발생
* <> 안에는 하나의 upper bound만 쓸 수 있다

* 같은 형식 매개변수가 여러개의 upper bound가 필요하면 **'where'** 키워드 사용
  * where을 통해 지정된 제한을 모두 포함하는 경우만 허용

```kotlin
interface InterfaceA
interface InterfaveB

class HandlerA: InterfaceA, InterfaceB
class HandlerB: InterfaceA

class ClassA<T> where T:InterfaceA, T:InterfaceB // 2개의 인터페이스를 구현하는 클래스로 제한

fun main() {
  val obj1 = ClassA<HandlerA>() //객체 생성 가능
  val obj2 = ClassA<HandlerB>() // 범위에 없으므로 오류 발생
}

// myMax의 인자 a,b에 들어갈 자료형을 숫자형과 비교형만으로 한정
fun <T> myMax(a: T, b: T): T where T:Number, T:Comparable<T> {
  return if (a > b) a else b
}
```



#### 상 하위 형식의 가변성

가변성(Variance)란 형식 매개변수가 클래스 계층에 영향을 주는 것.

##### 클래스와 자료형

|     형태      | 클래스인가? | 자료형인가? |
| :-----------: | :---------: | :---------: |
|    String     |      O      |      O      |
|    String?    |      X      |      O      |
|     List      |      O      |      O      |
| List< String> |      X      |      O      |

* 보통 클래스는 파생된 하위 클래스와 상위 클래스가 존재 ex) Int는 Number의 하위 클래스
* 상위 클래스는 하위 클래스를 수용할 수 있기 때문에 Int형 변수는 Number형 변수로 할당되어 형변환이 이루어짐
* Int는 Int?의 하위 자료형 이다. 
  * Int는 Int?에 할당하는 것이 가능하다



##### 자료형 변환

* 가변성을 지정하지 않으면 형식 매개변수에 상/하위 클래스가 지정되어도 서로 자료형이 변환되지 않는다.
  * 제네릭 클래스에서는 형식 매개변수인 T에 상/하위 클래스를 지정하더라도 서로 관련이 없는 형식이다



##### 가변성의 3가지 유형

|           용어           | 의미                                                         |
| :----------------------: | ------------------------------------------------------------ |
|    공변성(Covariance)    | T'가 T의 하위 자료형이면, C<T'>는 C< T>의 하위 자료형이다.<br />생산자 입장의 out 성질 |
| 반공변성(Contravariance) | T'가 T의 하위 자료형이면, C< T>는 C<T'>의 하위 자료형이다.<br />소비자 입장의 in 성질 |
|    무변성(Invariance)    | C< T>와 C<T'>는 아무 관계가 없다.<br />생산자 + 소비자       |

* **무변성 (Invariance)**
  * in이나 out으로 공변성이나 반공변성을 따로 지정하지 않으면 무변성.

```kotlin
class Box<T>(val size: Int)

// 클래스의 관계 : Any <- Int <- Nothing
// Nothing은 코틀린의 최하위 자료형
fun main() {
  val anys: Box<Any> = Box<Int>(10) // 오류
  val nothings: Box<Nothing> = Box<Int> // 오류
}
```

* **공변성 (Covariance)**
  * 형식 매개변수의 상하 자료형 관계가 성립하고, 그 관계가 그대로 인스턴서 자료형 관계로 이어지는 경우
  * **out** 키워드를 사용

```kotlin
class Box<out T>(val size: Int)

fun main() {
  val anys: Box<Any> = Box<Int>(10) // 관계성립으로 객체 생성 가능
  val nothings: Box<Nothing> = Box<Int>(20) // 오류
}
```

* **반공변성 (Contravariance)**
  * 자료형의 상하 관계가 반대가 되어 인스턴스의 자료형이 상위 자료형이 됨
  * 공변성의 반대 경우
  * **in** 키워드를 사용

```kotlin
class Box<in T>(val size: Int)

fun main() {
  val anys: Box<Any> = Box<Int>(10) // 오류
  val nothings: Box<Nothing> = Box<Int>(20) // 관계성립으로 객체 생성 가능
}
```



##### 가변성 지정 방법

1. 선언 지점 변성(Declaration-Site Variance) : 클래스 자체에 가변성을 지정하는 방식

```kotlin
class Box<in T: Animal>(var item: T) //선언 지점에서 가변성 지정
```

2. 사용 지점 변성(Use-Site Variance): 메서드의 매개변수나 제네릭 클래스를 생성할 때와 같이 사용 위치에서 가변성 지정하는 방식

```kotlin
clss Box<T>(var item: T) // 무변성

fun <T> printObj(obj: Box<out Animal>) { // Box<>의 사용 지점에서 가변성 지정
  val obj: Animal = box.item
  println(obj)
}
```



#### 자료형 프로젝션

: 사용하고자 하는 요소의 특정 자료형에 in 또는 out을 지정해 제한하는 것

* 스타 프로젝션 : 
  스타(*)를 통해 지정, 어떤 자료형이라도 들어올 수 있으나 구체적으로 자료형이 결정되면 그 자료형과 하위 자료형의 요소만 허용한다.
  * in으로 정의된 형식 매개변수를 *로 받으면 in Nothing으로 간주
  * out으로 정의된 형식 매개변수를 *로 받으면 out Any?로 간주
* get 하기 위해서는 out, set하기 위해서는 in이 지정되어야 한다.
  (out은 반환 자료형, in은 매개변수의 자료형 에만 사용 가능....??)

```kotlin
class InOutTest<in T, out U>(t: T, u: U) {
  //T: in 프로젝션, U: out 프로젝션
  val propertyT: T = t // 오류! T는 in프로젝션이기 때문에 out위치에 사용 불가
  val propertyU: U = u
  
  fun func1(u: U) //오류! U는 out위치이기 때문에 in 위치 사용 불가
  fun func2(t: T) {
    print(t)
  }
}
```



#### Type erasure

자바와 마찬가지로 코틀린 제네릭 타입 인자 정보는 런타임에 지워진다. 이는 제네릭 클래스 인스턴스가 그 인스턴스를 생성할 때 쓰인 타입 인자에 대한 정보를 유지하지 않는 다는 뜻.

```kotlin
val list1: List<String> = listOf("a", "b")
val list2: List<Int> = listOf(1,2,3)
```

* 컴파일러는 두 리스트를 서로 다른 타입으로 인식하지만, 실행 시점에 둘은 완전히 같은 타입의 객체가 됨
* 그렇지만 컴파일 타임에 타입 인자를 알고 올바른 타입의 값만 넣도록 보장해주기 때문에 실행 시점에서도 올바른 타입만 들어있을 것이라 가정할 수 있다.



##### 타입소거의 한계

* 런타임 시에 List< String> 이나 List<Int?> 는 List<*> 가 된다.

* 따라서 실행 시점에 타입 인자를 검사할 수 없게 된다. -> is type 체크를 금지한다

```kotlin
if (value is List<String>) // ERROR: Cannot check for instance of erased type

if (value is List<*>) {...} // 값이 List 인지를 확인하려면 스타 프로젝션 사용
```

* 실행 시점에는 제네릭 타입의 인자를 알 수 없으므로 캐스팅은 항상 성공, 그런 타입 캐스팅을 사용하면 컴파일러가 "unchecked case" 경고를 해준다.
* 코드는 문제없이 컴파일 되지만 잘못된 타입의 원소가 들어있는 리스트를 전달하면 실행 시점에 Exception이 발생한다.

```kotlin
fun printSum(c: Collection<*>) {
  val intList = c as? List<Int>
  				?: throw IllegalArgumentException("List is expected")
  println(intList.sum())
}

printSum(listOf(1, 2, 3)) // 6
printSum(setOf(1, 2, 3)) // IllegalArgumentException: List is expected
printSum(listOf("a", "b", "c")) // ClassCastException: String cannot be cast to Number
```

* 다만 컴파일 타임 시점에 타입 정보가 주어진 경우에는 is 검사를 수행하게 허용할 수 있다

```kotlin
fun printSum(c: Collection<Int>) {
  if(c is List<Int>) {
    println(intList.sum())
  }
}
```



##### 실체화된 타입 파라미터

타입 소거의 제약을 피할 수 있는 방법.

* 인라인 함수의 타입 파라미터는 실체화 되므로 실행 시점에 인라인 함수의 타입 인자를 알 수 있다.
* 함수를 **인라인 함수**로 만들고 타입 파라미터를 **reified**로 지정하면 어떤 타입이 T의 인스턴스인지 실행 시점에 검사할 수 있다.

```kotlin
fun <T> isA(value: Any) = value is T // 타입 인자를 알 수 없기 때문에 불가능
inline fun <reified T> isA(value: T) = value is T // 가능
```

```kotlin
//응용
inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> {
  val destination = mutableListOf<T>()
  for (element in this) {
    if(element is T) {
      destination.add(element)
    }
  }
}
```

> inline 함수는 람다식을 사용했을 때 무의미하게 객체가 생성되는 것을 막을 수 있음
>
> reified는 inline function과 조합해서만 사용가능하다.
> inline 을 함수에 붙이면 컴파일러는 그 함수를 호출하는 모든 문장을 함수본문에 해당하는 바이트코드로 바꿔치기 한다. 
> refined type과 함께 인라인 함수가 호출되면 컴파일러는 type argument로 사용된 실제 타입을 알고 만들어진 바이트코드를 직접 클래스에 대응되도록 바꿔준다.
>
> -> **value is T** 는 런타임과 바이트코드에서 **value is String** 이 될 수 있음
>
> 타입 파라미터가 아닌 구체적인 타입을 사용하므로 만들어진 바이트코드는 타입 소거의 영향을 받지 않음