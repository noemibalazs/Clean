package com.example.core.datasource

import com.example.core.domain.Document

interface DocumentDataSource {

    suspend fun addDocument(document: Document)

    suspend fun removeDocument(document: Document)

    suspend fun readAllDocuments(): List<Document>

    suspend fun getDocument(url:String):Document
}