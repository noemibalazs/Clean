package com.example.core.interactors

import com.example.core.datasource.BookmarkRepository
import com.example.core.domain.Bookmark
import com.example.core.domain.Document

class RemoveBookmark(private val bookmarkRepository: BookmarkRepository) {

    suspend operator fun invoke(document: Document, bookmark: Bookmark) {
        bookmarkRepository.removeBookmark(document, bookmark)
    }
}