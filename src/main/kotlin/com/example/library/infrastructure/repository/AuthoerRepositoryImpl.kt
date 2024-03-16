package com.example.library.infrastructure.repository

import com.example.ktknowledgeTodo.infra.jooq.tables.references.BOOKSINFO
import com.example.library.constants.Const
import com.example.library.domain.model.Request
import com.example.library.domain.model.Response
import com.example.library.domain.repository.AuthorRepository
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository

@Repository
class AuthorRepositoryImpl(
    private val dslContext: DSLContext
) : AuthorRepository {

    override fun findByAuthor(author: String): List<Response> {
        return this.dslContext.select()
            .from(BOOKSINFO)
            .where(BOOKSINFO.AUTHOR.eq(author))
            .fetch().map { toModel(it) }
    }

    override fun saveAll(request: Request): String {
        try {
            this.dslContext.insertInto(BOOKSINFO)
                .set(BOOKSINFO.AUTHOR, request.author)
                .set(BOOKSINFO.TITLE, request.title)
                .execute()
            return Const.STATUS_OK
        } catch (e: Exception) {
            println(e)
            return Const.STATUS_NG
        }
    }

    override fun setAllById(request: Request): String {
        try {
            this.dslContext.update(BOOKSINFO)
                .set(BOOKSINFO.AUTHOR, request.author)
                .set(BOOKSINFO.TITLE, request.title)
                .where(BOOKSINFO.ID.eq(request.id))
                .execute()
            return Const.STATUS_OK
        } catch (e: Exception) {
            println(e)
            return Const.STATUS_NG
        }
    }

    // 動作確認用
    override fun deleteAll() {
        this.dslContext.deleteFrom(BOOKSINFO).execute()
    }

    private fun toModel(record: Record) = Response(
        record.getValue(BOOKSINFO.ID)!!,
        record.getValue(BOOKSINFO.AUTHOR)!!,
        record.getValue(BOOKSINFO.TITLE)!!
    )
}