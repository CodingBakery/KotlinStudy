package com.kotlinspring.repository

import com.kotlinspring.entity.Memo
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface MemoRepository : CrudRepository<Memo, Long> {

    @Query(value = "SELECT * FROM memo WHERE content like %?1%", nativeQuery = true)
    fun findMemosByKeyword(courseName : String) : List<Memo>

}