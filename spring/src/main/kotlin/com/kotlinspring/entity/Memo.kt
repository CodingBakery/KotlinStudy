package com.kotlinspring.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "memo")
data class Memo(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?,
    val content: String,
    val createDt: LocalDateTime,
    val updateDt: LocalDateTime
)