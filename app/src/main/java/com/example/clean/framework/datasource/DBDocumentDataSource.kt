package com.example.clean.framework.datasource

import com.example.clean.framework.db.PDFDataBase
import com.example.clean.presentation.helper.Mapper
import com.example.core.datasource.DocumentDataSource
import com.example.core.domain.Document

class DBDocumentDataSource(
    private val pdfDataBase: PDFDataBase,
    private val mapper: Mapper
) : DocumentDataSource {

    override suspend fun addDocument(document: Document) {
        pdfDataBase.documentDAO().addDocument(mapper.mapDocumentToDocumentEntity(document))
    }

    override suspend fun removeDocument(document: Document) {
        pdfDataBase.documentDAO().deleteDocument(mapper.mapDocumentToDocumentEntity(document))
    }

    override suspend fun readAllDocuments(): List<Document> {
        val listOfEntity = pdfDataBase.documentDAO().getAllDocument()
        return listOfEntity.map { entity -> mapper.mapDocumentEntityToDocument(entity) }
    }
}