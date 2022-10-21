package com.kotlinspring.controller

import com.kotlinspring.dto.MemoDTO
import com.kotlinspring.service.MemoService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Validated
@RequestMapping("/memo")
class MemoController(val memoService: MemoService) {    // 기본 생성자로 받는 인자가 주입됨.

    @GetMapping("/hello")
    fun hello(): String {
        return memoService.hello()
    }

    @GetMapping
    fun listMemos(@RequestParam("keyword") keyword : String?): List<MemoDTO> {
        return memoService.listMemos(keyword)
    }

    @GetMapping("/{memoId}")
    fun getMemo(@PathVariable("memoId") memoId: Long): MemoDTO {
        return memoService.getMemo(memoId)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addMemo(@RequestBody @Valid memoDTO: MemoDTO): MemoDTO {    // DTO에 유효성 검증 로직 넣어둠!
        return memoService.addMemo(memoDTO)
    }
}
