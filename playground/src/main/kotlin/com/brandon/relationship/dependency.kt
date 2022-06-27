package com.brandon.dependency

// 의사가 생성될때는 1명의 환자가 있어야 한다.
// 의사는 환자에게 의존적인다.
// 환자가 있어야 의사가 존재할수 있다. 즉 의사는 환자에 의존적이다.
class Patient(val name: String, var id: Int) {
    fun doctorList(d: Doctor) {
        println("Patient: $name, Doctor: ${d.name}")
    }
}

class Doctor(val name: String, val p: Patient) {

    val customerId: Int = p.id
    fun patientList() {
        println("Doctor: $name, Patient: ${p.name}")
        println("Patient Id: $customerId")
    }
}

fun main() {
    val patient1 = Patient("Kildong", 1234)
    val doc1 = Doctor("KimSabu", patient1)
    doc1.patientList()
}