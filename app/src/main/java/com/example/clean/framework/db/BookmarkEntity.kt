package com.example.clean.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clean.presentation.util.BOOKMARK_TABLE

@Entity(tableName = BOOKMARK_TABLE)
data class BookmarkEntity(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    val page: Int,
    val url: String
)