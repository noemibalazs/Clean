package com.example.core.datasource

import com.example.core.domain.Bookmark
import com.example.core.domain.Document

interface BookmarkDataSource {

    fun addBookmark(document: Document, bookmark: Bookmark)

    fun removeBookmark(document: Document, bookmark: Bookmark)

    fun readAllBookmarks(document: Document): List<Bookmark>
}