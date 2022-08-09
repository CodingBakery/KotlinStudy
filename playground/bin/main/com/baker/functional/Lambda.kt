package com.baker.functional

/**
 * 함수형 프로그래밍
 * - 이벤트가 발생하면 이 핸들러를 실행하자
 * - 데이터 구조의 모든 원소에 이 연산을 적용하자
 *
 * 공통 코드 구조를 라이브러리 함수로 뽑아낼수 있다 (단위 동작을 정의하므로 공통화 하기 좋다)
 * 수신객체 지정 람다 - 함수를 인자로 받는다.
 */
fun main(args: Array<String>) {
    // 코틀린의 람다식은 항상 중괄호(브레이스)로 둘러싸여있다
    // 선언
    val sum = { x: Int, y: Int -> x + y }
    // 호출
    println(sum(1, 2))

    /***************
     *    예제 1    *
     ***************/
    println("##### 예제 1 #####")
    /**
     * 인자가 단 하나뿐인 경우, 굳이 인자에 이름을 붙이지 않아도 된다
     * 컴파일러가 유추 가능한 상황에서는 타입을 적을 필요 없다
     * 코틀린에서는 함수 호출 시 맨 뒤에 있는 인자가 람다 식이라면, 그 람다를 괄호 밖으로 빼낼 수 있다. (관습?)
     * - intellij 에서 기능도 제공한다. Move lambda expression out of parentheses, move lambda expression into parentheses
     * 람다가 어떤 함수의 유일한 인자이고 괄호 뒤에 람다를 썼다면 호출 시 빈 괄호를 없애도 된다.
     * 람다 파라미터 이름을 따로 지정하지 않은 경우에는 it 이라는 이름이 자동으로 만들어진다.
     * 람다식의 문장이 여러 줄인 경우, 가장 마지막에 있는 식이 람다의 결과값이 된다.
     * '식' 이라는 표현이 중요한듯
     *
     * 위와 같은 규칙을 통해 collection의 maxBy 함수를 사용하는 방법은 다음과 같이 개선할 수 있다.
     */

    val peopleList: List<Person> = listOf(Person("AA", 15), Person("BB", 20), Person("CC", 25), Person("DD", 30));

    // 람다를 괄호 밖으로
    println(peopleList.maxByOrNull() { p: Person -> p.age })

    // 람다가 유일한 인자이고 괄호 뒤에 람다를 썼으므로 빈 괄호를 제거
    println(peopleList.maxByOrNull { p: Person -> p.age })

    // 타입 추론이 가능한 경우 타입을 제거
    // 람다를 변수에 저장할 때는 추론이 불가하므로 타입을 명시해야함. 아래와 같이 쓸 수 없음
    println(peopleList.maxByOrNull { p -> p.age })
    // val getAge = { p -> p.age } // 불가

    // 람다 파라미터 이름을 디폴트 이름인 it 으로 바꿈.
    println(peopleList.maxByOrNull { it.age })

    // 멤버 참조 (자바의 메서드 참조) 형식으로 다음과 같이 바꿀수도 있다
    println(peopleList.maxByOrNull(Person::age))

    /***************
     *    예제 2    *
     ***************/
    println("")
    println("")
    println("##### 예제 2 #####")
    /**
     * 코틀린에서는 람다 안에서도 final (또는 effective final) 이 아닌 변수에 접근하고 변경도 할 수 있다.
     * - 이를 '람다가 포획한 변수 - capture variable' 라고 부른다.
     * - Javascript의 closure (클로저) 함수 느낌
     * - 함수형 프로그래밍 괜찮나? 안에서 분기 처리 안해서 괜찮?
     */
    var clientErrors = 0
    var serverErrors = 0
    val responses = listOf("404", "400", "500")
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }

    println("$clientErrors client errors, $serverErrors serverErrors")

    /**
     * 로컬 함수 내에서 선언된 변수의 경우에는 그 생명주기가 기본적으로는 그 변수를 선언한 로컬 함수와 같다.
     * 하지만 로컬 함수 내에 있는 람다에 의해 포획된 로컬 변수는 그 생명주기를 람다와 함께 한다.
     *
     * 참고: https://github.com/3tudy/kotlin/issues/5
     */
    for (i in 1..10) {
        var(clicks, increaseClicks) = tryToCountButtonClicks(Button())
        println("호출 결과 : clicks - ${clicks}, increaseClicks lambda - ${increaseClicks()}")
        println("호출 결과 : clicks - ${clicks}, increaseClicks lambda - ${increaseClicks()}")
        println("호출 결과 : clicks - ${clicks}, increaseClicks lambda - ${increaseClicks()}")
        println("호출 결과 : clicks - ${clicks}, increaseClicks lambda - ${increaseClicks()}")
    }

    println("")
    println("")
    println("##### 예제 3 #####")

    // 최상위에 선언된 (다른 클래스의 멤버가 아닌) 함수나 프로퍼티는 다음과 같이 참조할 수 있다. Java에서의 this 를 생략한 느낌
    run(::salute) // 최상위를 참조 (REPL 에선 쓸수 없음)

    // 생성자 참조를 만들때도 동일하게 :: 를 사용할 수 있다.
    val createPerson = ::Person			// 참조 생성
    val p = createPerson("Alice", 29)	// 실제 인스턴스 생성
    println("Person 정보 - ${p}")

    //확장 함수도 동일한 방식으로 참조 가능
    fun Person.isAdult() = age >= 21
    val predicate = Person::isAdult
    println(predicate(p))

    println("")
    println("")
    println("##### 예제 4 #####")
    //특정 인스턴스에 대한 멤버 참조도 코틀린 1.1 부터 사용 가능하다 (바운드 멤버 참조)
    val p2 = Person("Dmitry", 34)
    val ageFunction = p2::age
    println(ageFunction())

    /**
     *
     * SAM 생성자 - 람다를 함수형 인터페이스의 인스턴스로 변환할 수 있게 컴파일러가 자동으로 생성한 함수이다.
     * 함수형 인터페이스의 인스턴스를 반환하는 메소드가 있다면, 람다를 직접 반환할 수 없고 반환하려는 람다를 SAM 생성자로 감싸야한다.
     * fun createAllDoneRunnable(): Runnable {
     *     return Runnable { println("All done!") }
     * }
     */

    /**
     * 수신 객체 지정 람다: with, apply
     *
     * with: 특정 인스턴스의 변수 이름을 반복하지 않고 다양한 연산을 수행할 수 있도록 해준다.
     * 특별한 구문인게 아니라 실제로 파라미터가 2개인 함수이다.
     * 위 예제로 볼때 첫번째 파라미터는 result 이고 두번째 파라미터는 람다이다.
     * with 함수는 첫번째 파라미터를 두번째 파라미터인 람다의 수신 객체로 만든다.
     * 인자로 받은 람다 본문에서는 this를 사용해 수신 객체에 접근할 수 있는 점을 이용한 것
     */
    println("")
    println("")
    println("##### 예제 5 #####")
    // 변경 전
    var r2 = StringBuilder()
    for (letter in 'A'..'Z') {
        r2.append(letter)
    }

    r2.append("\nNow I know the alphabet!")
    println(r2.toString())

    // 변경 후
    var r3 = StringBuilder()
    println {
        with(r3) {
            for (letter in 'A'..'Z') {
                this.append(letter)    // this 라고 명시
            }
            append("\nNow I know the alphabet!")        // this 생략
            this.toString()
        }
    }

    // apply: with 와 동일하지만, 자신에게 전달된 객체 (수신 객체)를 항상 반환하는 점만 다르다.
    // 앞선 예제를 apply 로 구현해보면 다음과 같다.
    fun alphabet() = StringBuilder().apply {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet!")
    }.toString()

    println("apply result - ${alphabet()}")

    /**
     * 고차 함수
     * - 다른 함수를 인자로 받거나, 함수를 반환하는 함수
     * - map, filter, with 등과 같이 다른 함수(람다) 를 인자로 받으므로 고차 함수
     *
     * 코틀린에서 함수 타입은 이렇게 지정하면 된다.
     *  파라미터 타입      반환 타입
     * (Int, String) -> Unit
     */
    println("")
    println("")
    println("##### 예제 6 #####")

    // 함수를 리턴하는 함수
    val sum2: (Int, Int) -> Int = { x, y -> x + y }
    println("sum2 - ${sum2(2, 3)}")
    // 'null이 될 수 있는 함수' 를 리턴하는 함수
    val sum3: ((Int, Int) -> Int)? = null
    // 아래 코드는 컴파일 에러가 난다.
//    println("sum3 - ${sum3(1, 2)}")
}

fun tryToCountButtonClicks(button: Button): Pair<Int, () -> Int> {
    var clicks = 0
    var increaseClick = { clicks++ }
    button.onClick {
        increaseClick()
    }
    return Pair(clicks, increaseClick)
}

fun salute() = println("Salute!")

class Person(var name: String, val age: Int) {
    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}

class Button() {
    fun onClick(function: () -> Int) {
        function()
    }
}