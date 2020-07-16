package com.example.clean.framework.action

import com.example.core.interactors.*

data class InterActors(
    val addDocument: AddDocument,
    val removeDocument: RemoveDocument,
    val readAllDocuments: ReadAllDocuments,
    val getDocument: GetDocument
) {
    override fun toString(): String {
        return "InterActors: addDocument=$addDocument, removeDocument=$removeDocument, readAllDocuments=$readAllDocuments, getDocument=$getDocument"
    }
}