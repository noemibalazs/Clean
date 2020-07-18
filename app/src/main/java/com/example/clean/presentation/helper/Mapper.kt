package com.example.clean.presentation.helper

import com.example.clean.framework.db.DocumentEntity
import com.example.core.domain.Document

class Mapper {

    fun mapDocumentEntityToDocument(documentEntity: DocumentEntity): Document {
        return Document(
            uri = documentEntity.url,
            name = documentEntity.name,
            size = documentEntity.size,
            page = documentEntity.page
        )
    }

    fun mapDocumentToDocumentEntity(document: Document): DocumentEntity {
        return DocumentEntity(
            url = document.uri,
            name = document.name,
            size = document.size,
            page = document.page
        )
    }
}