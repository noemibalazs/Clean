package com.example.core.interactors

import com.example.core.datasource.DocumentRepository
import com.example.core.domain.Document

class ReadAllDocuments(private val documentRepository: DocumentRepository) {

    suspend operator fun invoke(): List<Document> {
        return documentRepository.readAllDocuments()
    }
}