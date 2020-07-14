package com.example.core.domain

import java.io.Serializable

data class Document(
    val uri: String,
    val name: String,
    val size: Int
) : Serializable {
    companion object {
        val EMPTY = Document("", "", 0)
    }

    override fun toString(): String {
        return "Document: url='$uri', name='$name', size=$size"
    }
}