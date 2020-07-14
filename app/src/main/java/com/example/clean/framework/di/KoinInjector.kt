package com.example.clean.framework.di

import org.koin.core.module.Module

class KoinInjector {

    companion object {

        fun getModules(): MutableList<Module> {
            fun getMapper() = listOf(mapperModule)
            fun getPDFModule() = listOf(pdfDataBaseModule)
            fun getOpenDocumentModule() = listOf(openDocumentDataSourceModule)
            fun getDBDocumentDataSourceModule() = listOf(dbDocumentDataSourceModule)
            fun getDBBookmarkDataSourceModule() = listOf(dbBookmarkDataSourceModule)
            fun getDocumentRepositoryModule() = listOf(documentRepositoryModule)
            fun getBookmarkRepositoryModule() = listOf(bookmarkRepositoryModule)
            fun getActorsModule() = listOf(actorsModule)
            fun getInterActorsModule() = listOf(interActorsModule)
            fun getLibraryViewModelModule() = listOf(libraryViewModelModule)
            fun getReaderViewModelModule() = listOf(readerViewModelModule)

            return mutableListOf<Module>().apply {
                addAll(getMapper())
                addAll(getPDFModule())
                addAll(getOpenDocumentModule())
                addAll(getDBDocumentDataSourceModule())
                addAll(getDBBookmarkDataSourceModule())
                addAll(getDocumentRepositoryModule())
                addAll(getBookmarkRepositoryModule())
                addAll(getActorsModule())
                addAll(getInterActorsModule())
                addAll(getLibraryViewModelModule())
                addAll(getReaderViewModelModule())
            }

        }
    }
}