package com.example.clean.framework.datasource

import com.example.clean.framework.db.PDFDataBase
import com.example.clean.presentation.helper.Mapper
import com.example.core.datasource.BookmarkDataSource
import com.example.core.domain.Bookmark
import com.example.core.domain.Document

class DBBookmarkDataSource(
    private val pdfDataBase: PDFDataBase,
    private val mapper: Mapper
) : BookmarkDataSource {

    override suspend fun addBookmark(document: Document, bookmark: Bookmark) {
        pdfDataBase.bookmarkDAO()
            .addBookmark(mapper.mapBookmarkToBookmarkEntity(bookmark, document))
    }

    override suspend fun removeBookmark(document: Document, bookmark: Bookmark) {
        pdfDataBase.bookmarkDAO()
            .deleteBookmark(mapper.mapBookmarkToBookmarkEntity(bookmark, document))
    }

    override suspend fun readAllBookmarks(document: Document): List<Bookmark> {
        val entityList = pdfDataBase.bookmarkDAO().getAllBookmarks(document.uri)
        return entityList.map { entity -> mapper.mapBookmarkEntityToBookmark(entity) }
    }
}