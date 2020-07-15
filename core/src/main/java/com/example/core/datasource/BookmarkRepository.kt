package com.example.core.datasource

import com.example.core.domain.Bookmark
import com.example.core.domain.Document

class BookmarkRepository(private val bookmarkDataSource: BookmarkDataSource) {

    suspend fun addBookmark(document: Document, bookmark: Bookmark) {
        bookmarkDataSource.addBookmark(document, bookmark)
    }

    suspend fun removeBookmark(document: Document, bookmark: Bookmark) {
        bookmarkDataSource.removeBookmark(document, bookmark)
    }

    suspend fun readAllBookmarks(document: Document): List<Bookmark> {
        return bookmarkDataSource.readAllBookmarks(document)
    }
}