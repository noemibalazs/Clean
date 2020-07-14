package com.example.clean.framework.datasource

import com.example.core.datasource.OpenDocumentDataSource
import com.example.core.domain.Document

class InMemoryOpenDocument : OpenDocumentDataSource {

    private var openDocument = Document.EMPTY

    override fun setOpenDocument(document: Document) {
        openDocument = document
    }

    override fun getOpenDocument(): Document {
        return openDocument
    }
}