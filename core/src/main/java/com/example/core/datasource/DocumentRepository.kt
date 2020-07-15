package com.example.core.datasource

import com.example.core.domain.Document

class DocumentRepository(
    private val documentDataSource: DocumentDataSource
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

    suspend fun getDocument(url: String): Document {
        return documentDataSource.getDocument(url)
    }
}