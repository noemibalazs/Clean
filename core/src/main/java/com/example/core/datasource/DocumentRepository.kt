package com.example.core.datasource

import com.example.core.domain.Document

class DocumentRepository(
    private val documentDataSource: DocumentDataSource,
    private val openDocumentDataSource: OpenDocumentDataSource
) {

    suspend fun addDocument(document: Document) {
        documentDataSource.addDocument(document)
    }

    suspend fun removeDocument(document: Document) {
        documentDataSource.removeDocument(document)
    }

    suspend fun readAllDocuments(): List<Document> {
        return documentDataSource.readAllDocuments()
    }

    fun setOpenDocument(document: Document) {
        openDocumentDataSource.setOpenDocument(document)
    }

    fun getOpenDocument(): Document {
        return openDocumentDataSource.getOpenDocument()
    }
}