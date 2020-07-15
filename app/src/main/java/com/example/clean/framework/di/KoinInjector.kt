package com.example.clean.framework.di

import org.koin.core.module.Module

class KoinInjector {

    companion object {

        fun getModules(): MutableList<Module> {
            fun getMapper() = listOf(mapperModule)
            fun getPDFModule() = listOf(pdfDataBaseModule)
            fun getDBDocumentDataSourceModule() = listOf(dbDocumentDataSourceModule)
            fun getDBBookmarkDataSourceModule() = listOf(dbBookmarkDataSourceModule)
            fun getDocumentRepositoryModule() = listOf(documentRepositoryModule)
            fun getBookmarkRepositoryModule() = listOf(bookmarkRepositoryModule)
            fun getActorsModule() = listOf(actorsModule)
            fun getInterActorsModule() = listOf(interActorsModule)
            fun getLibraryViewModelModule() = listOf(libraryViewModelModule)
            fun getReaderViewModelModule() = listOf(readerViewModelModule)
            fun getDataManagerModule() = listOf(dataManagerModule)

            return mutableListOf<Module>().apply {
                addAll(getMapper())
                addAll(getPDFModule())
                addAll(getDBDocumentDataSourceModule())
                addAll(getDBBookmarkDataSourceModule())
                addAll(getDocumentRepositoryModule())
                addAll(getBookmarkRepositoryModule())
                addAll(getActorsModule())
                addAll(getInterActorsModule())
                addAll(getLibraryViewModelModule())
                addAll(getReaderViewModelModule())
                addAll(getDataManagerModule())
            }
        }
    }
}