package com.example.core.interactors

import com.example.core.datasource.DocumentRepository
import com.example.core.domain.Document

class GetDocument(private val documentRepository: DocumentRepository) {

    suspend operator fun invoke(url: String): Document {
        return documentRepository.getDocument(url)
    }
}