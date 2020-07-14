package com.example.clean.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clean.presentation.util.PDF_DATA_BASE

@Database(
    entities = [BookmarkEntity::class, DocumentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PDFDataBase : RoomDatabase() {

    abstract fun bookmarkDAO(): BookmarkDAO
    abstract fun documentDAO(): DocumentDAO

    companion object {

        fun getPDFDBInstance(context: Context): PDFDataBase {
            return Room.databaseBuilder(context, PDFDataBase::class.java,
                PDF_DATA_BASE
            ).build()
        }
    }
}