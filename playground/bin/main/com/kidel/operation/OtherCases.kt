package com.kidel.operation

fun main() {

    /**
     * -------------------------------
     * #### 추가: 컬렉션 원소 접근 관례 ####
     * -------------------------------
     */
    println("#### 추가예제 1: 인덱스 연산자 ####")

    val priceMap = mutableMapOf(Pair("오이", 1500), Pair("양배추", 3000), Pair("상추", 3000))
    println("오이 1개 가격은 ${priceMap["오이"]}")
    priceMap["양파"] = 500
    println("양파 1개 가격은 ${priceMap["양파"]}")

    val person = Person("Kidel", "게임")
    println("이름은 ${person["이름"]}")
    println("취미는 ${person["취미"]}")
    person["취미"] = "맛집탐방"
    println("바뀐 취미는 ${person["취미"]}")
    println()

    println("#### 추가예제 2: in 연산자 ####")

    println("오이는 priceMap에 있는가? ${"오이" in priceMap}")

    val club = Club("게임동아리", ArrayList())
    club.join(person)
    println("${person.name}은 ${club.name}에 가입했는가? ${person in club}")

    val customClub = CustomClub("그냥동아리", ArrayList())
    customClub.join(person)
    println("${person.name}은 ${customClub.name}에 가입했는가? ${person in customClub}")

    /**
     * --------------------------
     * #### 추가: 구조 분해 선언 ####
     * --------------------------
     */
    println("#### 추가예제 3: 구조 분해 선언 ####")

    // data 클래스는 구조 분해 선언을 자동으로 지원
    val korea = Nation("Korea", "Korean")
    val (name, language) = korea
    println("korea :: name=${name}, language=${language}")

    // 직접 오버로딩
    val jammanboPerson = Person("잠만보", "낮잠")
    val (jbName, jbHobby) = jammanboPerson
    println("jammanboPerson :: jbName=${jbName}, jbHobby=${jbHobby}")

}

data class Nation(val name: String, val language: String)

class Person(var name: String, var hobby: String) {
    operator fun get(key: String): String {
        return when(key) {
            "이름" -> name
            "취미" -> hobby
            else -> throw Exception("존재하지 않는 키값입니다")
        }
    }

    operator fun set(key: String, value: String) {
        return when(key) {
            "이름" -> name = value
            "취미" -> hobby = value
            else -> throw Exception("존재하지 않는 키값입니다")
        }
    }

    operator fun component1() = name
    operator fun component2() = hobby
}

class Club(var name: String, val memberList: ArrayList<Person>): List<Person> by memberList {
    fun join(person: Person) {
        memberList.add(person)
    }
}

class CustomClub(var name: String, val memberList: ArrayList<Person>) {
    
    fun join(person: Person) {
        memberList.add(person)
    }

    operator fun contains(person: Person): Boolean {
        return person in memberList
    }
}