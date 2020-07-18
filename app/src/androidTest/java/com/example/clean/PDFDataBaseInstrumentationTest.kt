package com.example.clean

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.clean.framework.db.DocumentDAO
import com.example.clean.framework.db.DocumentEntity
import com.example.clean.framework.db.PDFDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class PDFDataBaseInstrumentationTest {

    private lateinit var pdfDataBase: PDFDataBase
    private lateinit var documentDAO: DocumentDAO

    @Before
    fun setUp() {
        pdfDataBase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            PDFDataBase::class.java
        ).build()
        documentDAO = pdfDataBase.documentDAO()
    }

    @Test
    @Throws(Exception::class)
    fun testAddDocumentToDB(){
        val entity = DocumentEntity("url", "doc", 12, 9)
        CoroutineScope(Dispatchers.IO).launch {
            val id = documentDAO.addDocument(entity)
            assertNotNull(id)
        }
    }

    @Test
    fun testUpdateDocument(){
        val entity = DocumentEntity("url", "doc", 12, 9)
        val updatedEntity = DocumentEntity("url", "doc", 12, 12)
        CoroutineScope(Dispatchers.IO).launch {
            documentDAO.addDocument(entity)
            documentDAO.addDocument(updatedEntity)
            val result = documentDAO.getDocument("url")
            assertEquals(result, updatedEntity)
        }
    }
}