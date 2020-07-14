package com.example.clean.presentation.helper

import com.example.clean.framework.db.BookmarkEntity
import com.example.clean.framework.db.DocumentEntity
import com.example.core.domain.Bookmark
import com.example.core.domain.Document

class Mapper {

    fun mapDocumentEntityToDocument(documentEntity: DocumentEntity): Document {
        return Document(
            uri = documentEntity.url,
            name = documentEntity.name,
            size = documentEntity.size
        )
    }

    fun mapDocumentToDocumentEntity(document: Document): DocumentEntity {
        return DocumentEntity(
            url = document.uri,
            name = document.name,
            size = document.size
        )
    }

    fun mapBookmarkEntityToBookmark(bookmarkEntity: BookmarkEntity): Bookmark {
        return Bookmark(id = bookmarkEntity.id, page = bookmarkEntity.page)
    }

    fun mapBookmarkToBookmarkEntity(bookmark: Bookmark, document: Document): BookmarkEntity {
        return BookmarkEntity(
            id = bookmark.id,
            page = bookmark.page,
            url = document.uri
        )
    }

}