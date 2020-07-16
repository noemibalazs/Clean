package com.example.core.domain

import java.io.Serializable

data class Bookmark(
    val id: String = java.util.UUID.randomUUID().toString(),
    val page: Int
) : Serializable {
    override fun toString(): String {
        return "Bookmark: id='$id', page=$page"
    }
}