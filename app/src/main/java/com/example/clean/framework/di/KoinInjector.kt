package com.example.clean.framework.di

import org.koin.core.module.Module

class KoinInjector {

    companion object {

        fun getModules(): MutableList<Module> {
            fun getMapper() = listOf(mapperModule)
            fun getPDFModule() = listOf(pdfDataBaseModule)
            fun getDBDocumentDataSourceModule() = listOf(dbDocumentDataSourceModule)
            fun getDocumentRepositoryModule() = listOf(documentRepositoryModule)
            fun getActorsModule() = listOf(actorsModule)
            fun getInterActorsModule() = listOf(interActorsModule)
            fun getLibraryViewModelModule() = listOf(libraryViewModelModule)
            fun getReaderViewModelModule() = listOf(readerViewModelModule)
            fun getDataManagerModule() = listOf(dataManagerModule)

            return mutableListOf<Module>().apply {
                addAll(getMapper())
                addAll(getPDFModule())
                addAll(getDBDocumentDataSourceModule())
                addAll(getDocumentRepositoryModule())
                addAll(getActorsModule())
                addAll(getInterActorsModule())
                addAll(getLibraryViewModelModule())
                addAll(getReaderViewModelModule())
                addAll(getDataManagerModule())
            }
        }
    }
}