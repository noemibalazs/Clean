package com.example.core.domain

data class Bookmark(
    val id: String,
    val page: Int
) {
    override fun toString(): String {
        return "Bookmark: id='$id', page=$page"
    }
}