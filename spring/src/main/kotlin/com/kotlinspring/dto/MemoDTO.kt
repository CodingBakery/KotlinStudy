package com.kotlinspring.dto

import javax.validation.constraints.NotBlank

data class MemoDTO (
    val id: Long?,
    @get:NotBlank(message = "memo content should not be blank")
    val content: String
)