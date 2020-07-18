package com.example.clean

import com.example.clean.framework.db.DocumentDAO
import com.example.clean.framework.db.DocumentEntity
import com.example.clean.framework.db.PDFDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.Mockito.`when`

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest = Config.NONE)
class DocumentEntityUnitTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    @Mock
    val documentDAO: DocumentDAO? = null

    @Mock
    val pdfDataBase: PDFDataBase? = null

    @Before
    fun setUp(){
        `when`(pdfDataBase!!.documentDAO()).thenReturn(documentDAO)
    }

    @Test
    @Throws(Exception::class)
    fun testAddDocumentToDataBase(){
        val entity = DocumentEntity("url", "doc", 12, 9)
        CoroutineScope(Dispatchers.IO).launch {
           val id = documentDAO?.addDocument(entity)
            assertNotNull(id)
        }
    }
}