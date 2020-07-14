package com.example.clean.framework.di

import com.example.clean.framework.action.InterActors
import com.example.clean.framework.datasource.DBBookmarkDataSource
import com.example.clean.framework.datasource.DBDocumentDataSource
import com.example.clean.framework.datasource.InMemoryOpenDocument
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

val openDocumentDataSourceModule = module {
    single<OpenDocumentDataSource> { InMemoryOpenDocument() }
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
    single { DocumentRepository(documentDataSource = get(), openDocumentDataSource = get()) }
}

val bookmarkRepositoryModule = module {
    single { BookmarkRepository(bookmarkDataSource = get()) }
}

val actorsModule = module {
    single { AddDocument(documentRepository = get()) }
    single { AddBookmark(bookmarkRepository = get()) }
    single { RemoveDocument(documentRepository = get()) }
    single { RemoveBookmark(bookmarkRepository = get()) }
    single { GetOpenDocument(documentRepository = get()) }
    single { SetOpenDocument(documentRepository = get()) }
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
            getOpenDocument = get(),
            setOpenDocument = get()
        )
    }
}

val libraryViewModelModule = module {
    viewModel { LibraryViewModel(interActors = get()) }
}

val readerViewModelModule = module {
    viewModel { ReaderViewModel(interActors = get()) }
}