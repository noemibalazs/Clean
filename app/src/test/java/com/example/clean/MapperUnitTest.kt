package com.example.clean

import com.example.clean.framework.db.DocumentEntity
import com.example.clean.presentation.helper.Mapper
import com.example.core.domain.Document
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest = Config.NONE)
class MapperUnitTest {

    private var mapper: Mapper? = null

    @Before
    fun setUp() {
        mapper = Mapper()
    }

    @Test
    fun testDocEntityToDocument() {
        val entity = DocumentEntity("http", "entity", 12, 9)
        val document = Document("http", "entity", 12, 9)
        val mappedDocument = mapper?.mapDocumentEntityToDocument(entity)
        assertEquals(document, mappedDocument!!)
    }

    @Test
    fun testDocumentToDocEntity() {
        val entity = DocumentEntity("http", "entity", 12, 9)
        val document = Document("http", "entity", 12, 9)
        val mappedEntity = mapper?.mapDocumentToDocumentEntity(document)
        assertEquals(entity, mappedEntity!!)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}