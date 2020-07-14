package com.example.clean.framework.db

import androidx.room.*
import com.example.core.domain.Bookmark

@Dao
interface BookmarkDAO {

    @Query("SELECT * FROM bookmark_table WHERE url= :url ")
    fun getAllBookmarks(url: String): List<BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookmark(bookmarkEntity: BookmarkEntity)

    @Delete
    fun deleteBookmark(bookmarkEntity: BookmarkEntity)
}