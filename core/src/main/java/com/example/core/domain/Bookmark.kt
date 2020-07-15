package com.example.core.domain

import java.io.Serializable

data class Bookmark(
    val id: String,
    val page: Int
) : Serializable {
    override fun toString(): String {
        return "Bookmark: id='$id', page=$page"
    }
}