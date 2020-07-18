package com.example.core.domain

import java.io.Serializable

data class Document(
    val uri: String,
    val name: String,
    val size: Int,
    val page: Int = 0
) : Serializable {
    companion object {
        val EMPTY = Document("", "", 0)
    }

    override fun toString(): String {
        return "Document(uri='$uri', name='$name', size=$size, page=$page)"
    }
}