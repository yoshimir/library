package com.example.library.infrastructure.repository

import com.example.ktknowledgeTodo.infra.jooq.tables.references.BOOKSINFO
import org.jooq.Record
import com.example.library.domain.model.Response
import org.assertj.core.api.Assertions
import org.jooq.DSLContext
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AuthoerRepositoryImplTests(private val dslContext: DSLContext) {

	@Test
	fun findByAuthorTest1() {
	}

}
