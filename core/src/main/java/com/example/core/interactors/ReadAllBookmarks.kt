package com.example.core.interactors

import com.example.core.datasource.BookmarkRepository
import com.example.core.domain.Bookmark
import com.example.core.domain.Document

class ReadAllBookmarks(private val bookmarkRepository: BookmarkRepository) {

    suspend operator fun invoke(document: Document): List<Bookmark> {
        return bookmarkRepository.readAllBookmarks(document)
    }
}