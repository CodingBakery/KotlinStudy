package com.kotlinspring.service

import com.kotlinspring.dto.MemoDTO
import com.kotlinspring.entity.Memo
import com.kotlinspring.repository.MemoRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MemoService(val memoRepository: MemoRepository) {

    @Value("\${message}")
    lateinit var message: String

    fun hello(): String = message

    fun listMemos(keyword: String?): List<MemoDTO> {
        val memos = keyword?.let {
            memoRepository.findMemosByKeyword(keyword)
        } ?: memoRepository.findAll()

        return memos.map {
            MemoDTO(it.id, it.content)
        }
    }

    fun addMemo(memoDTO: MemoDTO): MemoDTO {
        val memoEntity = memoDTO.let {
            Memo(null, it.content, LocalDateTime.now(), LocalDateTime.now())
        }

        memoRepository.save(memoEntity)

        return memoEntity.let {
            MemoDTO(it.id, it.content)
        }
    }

    fun getMemo(id: Long): MemoDTO {
        val memoEntity = memoRepository.findById(id)

        return if (memoEntity.isPresent) {
            memoEntity.get().let {
                MemoDTO(it.id, it.content)
            }
        } else {
            throw Exception("Cannot find memo [id: $id]")
        }
    }
}
