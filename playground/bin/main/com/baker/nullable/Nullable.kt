package com.baker.nullable

import java.lang.IllegalArgumentException

/**
 * 코틀린에는 Null이 될 수 있는 타입과 읽기 전용 컬렉션이 따로 있다.
 * nullability 는 NPE 오류를 피할 수 있게 돕기 위한 코틀린의 타입 시스템이다.
 * Java 의 경우 unchecked 예외로 다루어지는 RuntimeException이나, 코틀린의 경우 이를 컴파일 타임에 핸들링 하도록 한다.
 * Java 에서도 @Nullable 이나 @NotNUll 과 같은 도구를 제공하지만, 표준 절차의 일부가 아니기 때문에 일관성있게 적용되리라 보장할 수 없다.
 *  - 코틀린에서는 nullable 과 notnull 타입을 구분함으로써, 각 타입의 값에 대해 어떤 연산이 가능할 지 '컴파일 시점'에 판단할 수 있다.
 */
fun main(args: Array<String>) {

    /***************
     *    예제 1    *
     ***************/
    println("##### 예제 1 #####")

    // 컴파일 에러 발생. 인자 s 가 nullable 하지 않기 때문
    // strLen(null)
    println(strLenNullable("1234"))
    // 컴파일 에러 발생. 리턴 값이 null 일 수 있기 때문
//    var resultNullable: Int = strLenNullable(null)
    // 에러가 발생하지 않는다. null 을 리턴
    var resultNullable: Int? = strLenNullable(null)
    println(resultNullable)

    // 다음과 같이 안전한 호출 연산자를 연쇄해서 사용할 수도 있다.
    val company = Company("티몬", null)
    println(company?.address?.country)

    /***************
     *    예제 2    *
     ***************/
    println("")
    println("")
    println("##### 예제 2 #####")

    // null 대신 디폴트 값 사용을 위해 엘비스 연산자 ?: 를 사용해볼 수 있다.
    var s: String? = null;
    println(s ?: "기본값")
    println(company?.address?.country ?: "알수없음")

    // 코틀린에서는 throw, return 도 '식' 이기 때문에 엘비스 연산자 우항에 사용할 수 있다.
    val address = Address("대치동", 12345, "서울", "대한민국")
    val tmon = Company("TMON", address)
    val person = Person("베이커", tmon)
    printShippingLabel(person)

    // not-null assertion 으로 null 이 될수도 있지만 감안하겠다는 의미
    var strNull: String? = null
    // 아래 구문은 컴파일 에러는 발생하지 않지만 런타임에 NullPointerException 을 발생시킨다.
    // 이러면 Java 랑 똑같은데...?
//    var strNotNull: String = strNull!!
//    strNotNull.length

    /***************
     *    예제 3    *
     ***************/
    println("")
    println("")
    println("##### 예제 3 #####")

    // let 함수를 사용하면 널이 될 수 있는 식을 더 쉽게 다룰 수 있다.
    // 호출 시 사용하는 파라미터 값이 null 이 아닌 경우만 후행의 람다식을 실행하도록 함
    // 이를 통해 null 이 될 수 있는 값을 이용하여, null 이 아닌 값만 받는 함수를 호출할 때 사용할 수 있다.
    // 단, 여러 값이 null 인지 확인을 위해 let 호출을 중첩할 수 있는데, 가독성이 더 떨어진다. 이 경우는 if 식을 사용하는 걸 추천
    var str:String? = "haha"
    val result = str?.let { strLen(it) }
    println("${str}의 길이 : ${result}")

    /***************
     *    예제 4    *
     ***************/
    println("")
    println("")
    println("##### 예제 4 #####")
    class MyService {
        fun performAction(): String = "foo"
    }
    class LateInitTest {
        // lateinit 키워드를 가진 멤버는 var 여야한다. 단, nullable (?) 선언은 하지 않아도 된다.
        private lateinit var myService: MyService

        constructor() {
            // 단 생성자와 같이 나중에라도 초기화하지 않고 사용하면, 아래와 같은 예외가 발생할 수 있다.
            // kotlin.UninitializedPropertyAccessException: lateinit property myService has not been initialized
            myService = MyService()
        }

        fun testAction() {
            println(myService.performAction())
        }
    }

    val lateInitTest = LateInitTest()
    lateInitTest.testAction()

    /***************
     *    예제 5    *
     ***************/
    println("")
    println("")
    println("##### 예제 5 #####")

    // null 이 될 수 있는 타입에 대한 확장 함수를 정의하면 null 에 대한 처리를 하지 않고도 null 값을 다룰 수 있다.
    fun String?.isNullOrBlank(): Boolean = this == null || this.isBlank()
    val isNullstr: String? = null
    println(isNullstr.isNullOrBlank())


    /***************
     *    예제 6    *
     ***************/
    println("")
    println("")
    println("##### 예제 6 #####")

    // 코틀린에서는 함수나 클래스의 모든 타입 파라미터는 기본적으로 null 이 될 수 있다. (물음표가 없더라도)
    fun <T> printHashCode(t: T) {
        // 따라서 다음과 같이 안전한 호출과 같은 null 처리 구문을 사용해줘야 한다.
        println(t?.hashCode())
    }
    printHashCode(null)

    // 단, 다음과 같이 null 을 사용할 수 없도록 만들수도 있다.
    fun <T: Any> printHashCodeNotNull(t: T) {
        // 오히려 여기에 안전한 호출을 사용하면 불필요한 구문이라고 경고문 제공
        println(t.hashCode())
    }
    // 컴파일 에러 발생
//    printHashCodeNotNull(null)
    printHashCodeNotNull("hahaha")
    /**
     * Java에는 Nullable 이라는 개념이 없기 때문에, 코틀린에서 Java 클래스에 접근하는 경우 주의해야한다.
     * 해당 타입에 대해 제대로 처리할 책임은 개발자에게 있다.
     */

    /***************
     *    예제 7    *
     ***************/
    println("")
    println("")
    println("##### 예제 7 #####")

    // 컬렉션에 대한 nullable 을 정의할 때는 헷갈리지 않도록 주의하자.
    // 컬렉션은 null 이 될 수 없으나 각 항목은 Int 또는 null 일 수 있음
    var nullableElementsList: List<Int?> = listOf(1, 2, null)
    println(nullableElementsList)
    println(nullableElementsList.filterNotNull())
    // 각 항목은 null 이 될 수 없으나 List 자체는 null 일 수 있음
    val nullableListButNotElement: List<Int>? = listOf(1, 2, 3)
    println(nullableListButNotElement)
    // 각 항목은 과 List 모두 null 이 될 수 있음
    val nullableListAndElement: List<Int>? = null
    println(nullableListAndElement)
}

fun strLen(s: String) = s.length

// 아래와 같은 컴파일 에러가 발생한다.
// Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
// fun strLenNullable(s: String?) = s.length

// 안전 호출 연산자를 이용
// if (s != null) s.length else null 과 동일한 구문
fun strLenNullable(s: String?) = s?.length

/**
 * 코틀린에서는 throw, return 도 '식' 이기 때문에 엘비스 연산자 우항에 사용할 수 있다.
 */
fun printShippingLabel(person: Person) {
    val address = person.company?.address ?: throw IllegalArgumentException("No address")

    with (address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)