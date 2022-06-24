package com.baker.scopefunc

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * 스코프 함수들은 코드들을 간결하게 / 가독성 좋게 작성하도록 하는 목적을 가짐
 * 아래 스코프 함수 사용 가이드를 참조해서 컨벤션을 정하고 사용하는 것도 좋을 것 같다.
 *  - https://kotlinlang.org/docs/scope-functions.html#function-selection
 *
 * 참고자료
 *  - https://mycool0905.github.io/kotlin/2020/12/15/kotlin-scope-function.html
 *  - https://shinjekim.github.io/kotlin/2019/12/05/Kotlin-%EC%BD%94%ED%8B%80%EB%A6%B0%EC%9D%98-Scope-%ED%95%A8%EC%88%98/
 *
 */
fun main(args: Array<String>) {
    /**
     * 기억할 내용
     * - 함수의 마지막 인자가 람다식인 경우 괄호 밖으로 뺄수 있다. 아래 3개 식은 동일하다.
     * - run({"haha"})
     * - run() { "haha" }
     * - run { "haha" }
     */


    /**
     * @kotlin.internal.InlineOnly
     *
     * 컴파일 과정에서 인라이닝은 Kotlin 컴파일러가 하는 것이기 때문에,
     * Java 코드에서 Kotlin 의 인라인 함수를 사용할 경우 사용은 가능하지만 컴파일시 인라이닝 처리는 동작하지 않습니다.
     *
     * 이에 따라 대부분의 인라인 라이브러리 함수는 @InlineOnly 어노테이션이 포함되어 있는데
     * 이는 오직 인라인으로만 사용할 수 있다는 뜻으로,
     * Java 코드에서 해당 인라인 함수에 접근할 수 없도록 (=Kotlin에서만 해당 함수를 사용할 수 있도록) 가시성을 제어하는 어노테이션입니다.
     *
     * 참고자료
     *  - https://medium.com/@mook2_y2/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%84%B0%EB%94%94-14-inline-functions-f3628bb347ca
     */


    /**
     * @kotlin.internal.InlineOnly
     * public inline fun <R> run(block: () -> R): R {
     *   contract {
     *     callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *   }
     *   return block()
     * }
     *
     * run 함수는 확장 함수이기 때문에 context object를 receiver(this)로 이용할 수 있다.
     * run 함수는 반환 결과가 람다의 결과이다.
     * run 함수는 객체의 초기화와 리턴 값의 계산을 람다가 포함할 때 유용하다.
     * 확장함수이기 때문에 safe call(.?)을 붙여 non-null 일 때에만 실행할 수 있다.
     * 주로 어떤 값을 계산할 필요가 있거나 여러 개의 지역변수 범위를 제한할 때 사용한다.
     */
    val member = run {
        Member("Baker", 37u)
    }

    /**
     * @kotlin.internal.InlineOnly
     * public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
     *   contract {
     *     callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *   }
     *   return receiver.block()
     * }
     *
     * with 함수는 확장 함수가 아니기 때문에 context object를 argument로 전달한다. 그러나, 람다의 내부에는 확장함수로 적용되어서 this로 사용가능하다.
     * with 함수는 반환 결과가 람다의 결과이다.
     * with 함수는 수신 객체는 non-nullable이고, 결과가 필요하지 않은 경우에 유용하다.
     */
    with(member) {
        println(isUncle())
        println(plusAge())
    };

    // 위와 동일한 식
    with(member, {
        println(isUncle())
        println(plusAge())
    })

    // 위와 동일한 식
    val lambda = {member: Member -> println(member.isUncle()); println(member.plusAge())}
    with(member, lambda)

    /**
     * @kotlin.internal.InlineOnly
     * public inline fun <T> T.apply(block: T.() -> Unit): T {
     *   contract {
     *     callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *   }
     *   block()
     *   return this
     * }
     *
     * apply 함수는 확장 함수이기 때문에 context object를 receiver(this)로 이용할 수 있다.
     * apply 함수는 반환 결과가 객체 자신이다. Builder 패턴과 동일한 용도로 사용된다.
     * apply 함수는 객체의 프로퍼티 만을 사용하는 경우가 많으며, 대표적인 사례는 객체의 초기화이다.
     */

    val member2 = Member("Baker", 0u).apply {
        age = 25u
    }
    println(member2)

    /**
     * @kotlin.internal.InlineOnly
     * @SinceKotlin("1.1")
     * public inline fun <T> T.also(block: (T) -> Unit): T {
     *   contract {
     *     callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *   }
     *   block(this)
     *   return this
     * }
     * also 함수는 확장 함수이기 때문에 context object를 receiver(this)로 전달한다. 그러나, 코드 블럭 내에서 this를 파라미터로 입력하기 때문에 it을 사용해 프로퍼티에 접근할 수 있다.
     * also 함수는 반환 결과가 객체 자신이다. Builder 패턴과 동일한 용도로 사용된다.
     * also 함수는 객체의 속성을 전혀 사용하지 않거나 변경하지 않고 사용하는 경우에 유용하다. 예를 들면, 객체의 데이터 유효성을 확인하거나, 디버그, 로깅 등의 부가적인 목적으로 사용할 때에 적합하다.
     */
    class Membership(member: Member) {
        // 아래 init 과 동일한 효과
        val member = member.also {
            requireNotNull(it.age)
            println(it.name)
        }

        // 위 also 와 동일한 효과
        init {
            requireNotNull(member.age)
            println(member.name)
        }
    }

    /**
     * @kotlin.internal.InlineOnly
     * public inline fun <T, R> T.let(block: (T) -> R): R {
     *   contract {
     *     callsInPlace(block, InvocationKind.EXACTLY_ONCE)
     *   }
     *   return block(this)
     * }
     *
     * let 함수는 확장 함수이기 때문에 context object를 receiver(this)로 전달한다. 그러나, 코드 블럭 내에서 this를 파라미터로 입력하기 때문에 it을 사용해 프로퍼티에 접근할 수 있다.
     * let 함수는 반환 결과가 람다의 결과이다.
     * let 함수는 지정된 값이 null이 아닌 경우에 코드를 실행해야 하는 경우, Nullable 객체를 다른 Nullable 객체로 변환하는 경우, 단일 지역 변수의 범위를 제한하는 경우에 유용하다.
     */

    val str: String? = "hahaha"
    val length = str?.let {
        it.length
    }

    println(length)
}

data class Member(val name: String, var age: UInt) {
    fun isUncle(): Boolean {
        return true
    }

    fun plusAge(): UInt {
        age++
        return age
    }
}

/**
 * contract 블록
 *
 * contract 블록 내에 정의된 구문을 컴파일러에게 미리 알려줌으로써
 * contract 블록 이후의 동작에서 해당 구문의 조건이 보장됨을 명시한다.
 *  - ex> 특정 객체는 null이 아님, 특정 람다는 한번만 실행됨 등
 *
 * 그래서 보통 contract가 포함된 함수를 호출한 다음 로직부터는
 * contract에 정의한 조건을 계속 명시하지 않아도 된다는 장점이 있다.
 *
 * - 그렇다고 하는데, 아래와 같이 run() 을 3번씩 호출해도 에러가 안나는데, 잘못 사용한 것인지...
 * - FIXME: 조건문을 잘못 사용한 것인지 이해를 잘못한 것인지 좀 더 확인 필요하다.
 */
@ExperimentalContracts
fun <R> runOnetime(run: () -> R): R {
    contract {
        callsInPlace(run, InvocationKind.EXACTLY_ONCE)
    }

    run()
    run()

    return run()
}