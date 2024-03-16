package com.example.library

import com.aallam.openai.client.OpenAI
import com.example.library.domain.model.Request
import com.example.library.domain.model.Response
import com.example.library.infrastructure.repository.AuthorRepositoryImpl
import org.assertj.core.api.Assertions.assertThat
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class AuthorControllerTests() {
	private val dslContextMock: DSLContext = mock()

	@BeforeEach
	@Throws(Exception::class)
	fun setUp() {
		// TODO ここで何かしらコンテキストの初期化をできれば実行できそう
		// テスト実行エラー「ParameterResolutionException : no parameterresolver registered for parameter」
		// DSL.using("postgresql://localhost:5432/postgres")
	}
	// 検索テスト1
	// 期待値の検索結果がレスポンスされること
	// データ数：1
	@Test
	fun findBooksInfoTest() {
		val openAI = OpenAI(token = "")
		val response = Response(1,"author","title")
		val rezultResponse: List<Response> = listOf(response)
		val expectResponse: List<Response> = listOf(response)

		val authorRepositoryImpl = AuthorRepositoryImpl(this.dslContextMock)
		`when`(authorRepositoryImpl.findByAuthor("author")).thenReturn(rezultResponse)

		val authorController = AuthorController(this.dslContextMock)
		assertThat(authorController.findBooksInfo("author")).isEqualTo(expectResponse)

	}

	// 検索テスト2
	// 期待値の検索結果がレスポンスされること
	// データ数：2
	@Test
	fun findBooksInfoTest2() {
		val response1 = Response(1,"author","title")
		val response2 = Response(2,"author2","title2")
		val rezultResponse: List<Response> = listOf(response1,response2)
		val expectResponse: List<Response> = listOf(response1,response2)
		val authorRepositoryImpl = AuthorRepositoryImpl(this.dslContextMock)
		`when`(authorRepositoryImpl.findByAuthor("author")).thenReturn(rezultResponse)

		val authorController = AuthorController(this.dslContextMock)
		assertThat(authorController.findBooksInfo("author")).isEqualTo(expectResponse)
	}

	// 登録テスト1
	// 登録判定がレスポンスされること
	// 期待値：OK
	@Test
	fun saveBooksInfoTest1() {
		val request = Request(1,"author","title")
		val authorRepositoryImpl = AuthorRepositoryImpl(this.dslContextMock)
		`when`(authorRepositoryImpl.saveAll(request)).thenReturn("0")

		val authorController = AuthorController(this.dslContextMock)
		assertThat(authorController.saveBooksInfo(request)).isEqualTo("0")
	}

	// 登録テスト2
	// 登録判定がレスポンスされること
	// 期待値：NG
	@Test
	fun saveBooksInfoTest2() {
		val request = Request(1,"author","title")
		val authorRepositoryImpl = AuthorRepositoryImpl(this.dslContextMock)
		`when`(authorRepositoryImpl.saveAll(request)).thenReturn("1")

		val authorController = AuthorController(this.dslContextMock)
		assertThat(authorController.saveBooksInfo(request)).isEqualTo("1")
	}

	// 更新テスト1
	// 更新判定がレスポンスされること
	// 期待値：OK
	@Test
	fun setBooksInfoTest1() {
		val request = Request(1,"author","title")
		val authorRepositoryImpl = AuthorRepositoryImpl(this.dslContextMock)
		`when`(authorRepositoryImpl.setAllById(request)).thenReturn("0")

		val authorController = AuthorController(this.dslContextMock)
		assertThat(authorController.setBooksInfo(request)).isEqualTo("0")
	}

	// 更新テスト2
	// 更新判定がレスポンスされること
	// 期待値：NG
	@Test
	fun setBooksInfoTest2() {
		val request = Request(1,"author","title")
		val authorRepositoryImpl = AuthorRepositoryImpl(this.dslContextMock)
		`when`(authorRepositoryImpl.setAllById(request)).thenReturn("1")

		val authorController = AuthorController(this.dslContextMock)
		assertThat(authorController.setBooksInfo(request)).isEqualTo("1")
	}
}
