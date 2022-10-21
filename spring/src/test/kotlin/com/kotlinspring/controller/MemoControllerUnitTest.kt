package com.kotlinspring.controller

import com.kotlinspring.dto.MemoDTO
import com.kotlinspring.service.MemoService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(MemoController::class)
@AutoConfigureWebTestClient
class MemoControllerUnitTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var memoServiceMock: MemoService

    @Test
    fun listMemos() {
        every { memoServiceMock.listMemos(any()) }.returnsMany(
            listOf(
                MemoDTO(1L, "Hi Hello"),
                MemoDTO(2L, "and GoodBye.")
            )
        )

        val memos = webTestClient
            .get()
            .uri("/memo")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(MemoDTO::class.java)
            .returnResult()
            .responseBody

        println("memos : $memos")

        assertEquals(2, memos!!.size)
    }

    @Test
    fun addMemo() {
        // given
        val memoDTO = MemoDTO(null, "1234")
        every { memoServiceMock.addMemo(any()) } returns MemoDTO(id = 1234L, "1234")

        // when
        val savedMemo = webTestClient.post()
            .uri("/memo")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(memoDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(MemoDTO::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertTrue {
            savedMemo!!.id != null
        }
    }

    @Test
    fun addMemo_validation() {
        //given
        val memoDTO = MemoDTO(null, "")

        every { memoServiceMock.addMemo(any()) } returns MemoDTO(id = 1234L, "")

        //when
        val response = webTestClient.post()
            .uri("/memo")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(memoDTO)
            .exchange()
            .expectStatus().isBadRequest
            .expectBody(String::class.java)
            .returnResult()
            .responseBody

        println("response : $response")
        assertEquals("memo content should not be blank"
            , response)
    }

}