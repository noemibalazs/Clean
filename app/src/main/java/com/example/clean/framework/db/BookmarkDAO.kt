package com.example.clean.framework.db

import androidx.room.*
import com.example.core.domain.Bookmark

@Dao
interface BookmarkDAO {

    @Query("SELECT * FROM bookmark_table WHERE url= :url ")
    suspend fun getAllBookmarks(url: String): List<BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmarkEntity: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)
}