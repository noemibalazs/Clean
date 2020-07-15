package com.example.clean.framework.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.core.domain.Document

@Dao
interface DocumentDAO {

    @Query("SELECT * FROM DOCUMENT_TABLE")
    suspend fun getAllDocument(): List<DocumentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDocument(documentEntity: DocumentEntity)

    @Delete
    suspend fun deleteDocument(documentEntity: DocumentEntity)

    @Query("SELECT COUNT(url) FROM DOCUMENT_TABLE")
    fun numberOfRows(): LiveData<Int>

    @Query("SELECT * FROM DOCUMENT_TABLE WHERE url =:url")
    suspend fun getDocument(url: String): DocumentEntity
}