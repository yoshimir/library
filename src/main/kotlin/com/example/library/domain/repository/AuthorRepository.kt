package com.example.library.domain.repository

import com.example.library.domain.model.Request
import com.example.library.domain.model.Response
import com.example.library.domain.model.Status

interface AuthorRepository {
    fun findByAuthor(author: String): List<Response>
    fun saveAll(request: Request):String
    fun setAllById(request: Request):String
    fun deleteAll()
}