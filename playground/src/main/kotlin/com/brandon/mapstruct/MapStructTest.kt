package com.brandon.mapstruct

import org.mapstruct.factory.Mappers
import java.time.LocalDate

class MapStructTest {
}

data class Person(var firstName: String?, var lastName: String?, var phoneNumber: String?, var birthdate: LocalDate?)
data class PersonDto(var firstName: String?, var lastName: String?, var phone: String?, var birthdate: LocalDate?)


fun main(){
    val converter = Mappers.getMapper(PersonConverter::class.java) // or PersonConverterImpl()

    val person = Person("Samuel", "Jackson", "0123 334466", LocalDate.of(1948, 12, 21))

    val personDto = converter.convertToDto(person)
    println(personDto)

    val personModel = converter.convertToModel(personDto)
    println(personModel)
}