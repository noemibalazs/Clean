package com.example.clean.framework.action

import com.example.core.interactors.*

data class InterActors(
    val addDocument: AddDocument,
    val addBookmark: AddBookmark,
    val removeDocument: RemoveDocument,
    val removeBookmark: RemoveBookmark,
    val readAllDocuments: ReadAllDocuments,
    val readAllBookmarks: ReadAllBookmarks,
    val getOpenDocument: GetOpenDocument,
    val setOpenDocument: SetOpenDocument
) {
    override fun toString(): String {
        return "InterActors: addDocument=$addDocument, addBookmark=$addBookmark, removeDocument=$removeDocument, removeBookmark=$removeBookmark, readAllDocuments=$readAllDocuments, readAllBookmarks=$readAllBookmarks, getOpenDocument=$getOpenDocument, setOpenDocument=$setOpenDocument"
    }
}