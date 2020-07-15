package com.example.clean.framework.di

import com.example.clean.framework.action.DataManager
import com.example.clean.framework.action.InterActors
import com.example.clean.framework.datasource.DBBookmarkDataSource
import com.example.clean.framework.datasource.DBDocumentDataSource
import com.example.clean.framework.db.PDFDataBase
import com.example.clean.presentation.helper.Mapper
import com.example.clean.presentation.library.LibraryViewModel
import com.example.clean.presentation.reader.ReaderViewModel
import com.example.core.datasource.*
import com.example.core.interactors.*
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapperModule = module {
    factory { Mapper() }
}

val pdfDataBaseModule = module {
    single { PDFDataBase.getPDFDBInstance(androidApplication().applicationContext) }
}

val dbDocumentDataSourceModule = module {
    single<DocumentDataSource> {
        DBDocumentDataSource(
            pdfDataBase = get(),
            mapper = get()
        )
    }
}

val dbBookmarkDataSourceModule = module {
    single<BookmarkDataSource> {
        DBBookmarkDataSource(
            pdfDataBase = get(),
            mapper = get()
        )
    }
}

val documentRepositoryModule = module {
    single { DocumentRepository(documentDataSource = get()) }
}

val bookmarkRepositoryModule = module {
    single { BookmarkRepository(bookmarkDataSource = get()) }
}

val actorsModule = module {
    single { AddDocument(documentRepository = get()) }
    single { AddBookmark(bookmarkRepository = get()) }
    single { RemoveDocument(documentRepository = get()) }
    single { RemoveBookmark(bookmarkRepository = get()) }
    single { GetDocument(documentRepository = get()) }
    single { ReadAllDocuments(documentRepository = get()) }
    single { ReadAllBookmarks(bookmarkRepository = get()) }
}

val interActorsModule = module {
    single {
        InterActors(
            addBookmark = get(),
            addDocument = get(),
            removeBookmark = get(),
            removeDocument = get(),
            readAllBookmarks = get(),
            readAllDocuments = get(),
            getDocument = get()
        )
    }
}

val libraryViewModelModule = module {
    viewModel { LibraryViewModel(interActors = get()) }
}

val readerViewModelModule = module {
    viewModel {
        ReaderViewModel(
            interActors = get(),
            application = androidApplication(),
            dataManager = get()
        )
    }
}

val dataManagerModule = module {
    single { DataManager(androidApplication().applicationContext) }
}