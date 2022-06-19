package com.brandon.association

// ### 여담
// 패키지 구조에서 폴더명과 위 패지지 명이 달라도 된다.?!
// 일전에 베이커가 말한 네임스페이스 영역으로 인식하는것 같다.

// 연관관계.
// 의사는 여러명의 환자를 가지고 있다.
// 환자는 여러명의 의사를 가지고 있다.

// 하나의 객체가 다른 객체의 구성요소가 된다.
// 둘다 독립적인 생명주기를 가지고 있다.
class Patient(val name: String) {
    fun doctorList(d: Doctor) {  // 인자로 참조
        println("Patient: $name, Doctor: ${d.name}")
    }
}

class Doctor(val name: String) {
    fun patientList(p: Patient) { // 인자로 참조
        println("Doctor: $name, Patient: ${p.name}")
    }
}

fun main() {
    val doc1 = Doctor("KimSabu")
    val patient1 = Patient("Kildong")
    doc1.patientList(patient1)
    patient1.doctorList(doc1)
}