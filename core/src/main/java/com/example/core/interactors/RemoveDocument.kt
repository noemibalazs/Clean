package com.example.core.interactors

import com.example.core.datasource.DocumentRepository
import com.example.core.domain.Document

class RemoveDocument(private val documentRepository: DocumentRepository) {

    suspend operator fun invoke(document: Document){
        documentRepository.removeDocument(document)
    }
}