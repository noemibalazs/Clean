package com.example.core.interactors

import com.example.core.datasource.DocumentRepository
import com.example.core.domain.Document

class GetOpenDocument(private val documentRepository: DocumentRepository) {

    operator fun invoke(): Document {
        return documentRepository.getOpenDocument()
    }
}