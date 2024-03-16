package com.example.library

import com.example.library.domain.model.Response
import com.example.library.domain.model.Request
import com.example.library.domain.model.Status
import com.example.library.infrastructure.repository.AuthorRepositoryImpl
import org.jooq.DSLContext
import org.springframework.web.bind.annotation.*


// AuthorController.kt
@RestController
class AuthorController(private val dslContext: DSLContext) {

    /*
    検索処理
    リクエストの著者から書籍情報をリスト形式で返却
    課題の[書籍には著者の属性があり、書籍と著者の情報をRDBに登録・変更・検索ができる]の検索と
    [著者に紐づく本を取得できる]に該当する
    ※リクエスト例 curl "http://localhost:8080/search?author=SteveJobs"
     */
    @GetMapping("/search")
    fun findBooksInfo(@RequestParam(name = "author", required = false, defaultValue = "") author: String): List<Response> {
        val authorRepository = AuthorRepositoryImpl(this.dslContext)
        return authorRepository.findByAuthor(author)
    }

    /*
    登録処理
    リクエストの著者と書籍をDB登録
    課題の[書籍には著者の属性があり、書籍と著者の情報をRDBに登録・変更・検索ができる]の登録に該当する
    ※リクエスト例 curl -H 'Content-Type:application/json' -X POST -d '{"author":"testAuthor","title":"testTitle"}' 'http://localhost:8080/reg'
     */
    @PostMapping("/reg")
    fun saveBooksInfo(@RequestBody request: Request): Status {
        val authorRepository = AuthorRepositoryImpl(this.dslContext)
        return Status(authorRepository.saveAll(request))
    }

    /*
    更新処理
    リクエストのIDをキーに、著者と書籍を更新
    課題の[書籍には著者の属性があり、書籍と著者の情報をRDBに登録・変更・検索ができる]の変更に該当する
    ※リクエスト例 curl -H 'Content-Type:application/json' -X PUT -d '{"id":1,"author":"yamazaki","title":"yamazakititle"}' 'http://localhost:8080/update'

     */
    @PutMapping("/update")
    fun setBooksInfo(@RequestBody request: Request): Status {
        val authorRepository = AuthorRepositoryImpl(this.dslContext)
        return Status(authorRepository.setAllById(request))
    }
}