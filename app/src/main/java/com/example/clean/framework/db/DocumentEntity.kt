package com.example.clean.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.clean.presentation.util.DOCUMENT_TABLE

@Entity(tableName = DOCUMENT_TABLE)
data class DocumentEntity(
    @PrimaryKey
    val url: String,
    val name: String,
    val size: Int,
    val page: Int
)