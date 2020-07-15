package com.example.core.datasource

import com.example.core.domain.Bookmark
import com.example.core.domain.Document

interface BookmarkDataSource {

    suspend fun addBookmark(document: Document, bookmark: Bookmark)

    suspend fun removeBookmark(document: Document, bookmark: Bookmark)

    suspend fun readAllBookmarks(document: Document): List<Bookmark>
}