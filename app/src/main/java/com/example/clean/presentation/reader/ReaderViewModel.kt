package com.example.clean.presentation.reader

import android.app.Application
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.clean.framework.action.DataManager
import com.example.clean.framework.action.InterActors
import com.example.clean.framework.viewmodel.PDFViewModel
import com.example.core.domain.Bookmark
import com.example.core.domain.Document
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.logger.KOIN_TAG

class ReaderViewModel(
    private val interActors: InterActors, private val application: Application,
    private val dataManager: DataManager
) :
    PDFViewModel() {

    var document = MutableLiveData<Document>()

    val bookmarks = MediatorLiveData<List<Bookmark>>().apply {
        addSource(document) { document ->
            launch {
                postValue(interActors.readAllBookmarks.invoke(document))
            }
        }
    }

    fun getDocument() {
        launch {
            val myDocument = interActors.getDocument.invoke(dataManager.getDocumentUrl())
            withContext(Dispatchers.Main) {
                try {
                    document.value = myDocument
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "getDocument error message: ${e.message}")
                }
            }
        }
        loadPage()
    }

    val pdfCurrentPage = MediatorLiveData<PdfRenderer.Page>()

    val hasPreviousPage: LiveData<Boolean> = Transformations.map(pdfCurrentPage) {
        it.index > 0
    }

    val hasNextPage: LiveData<Boolean> = Transformations.map(pdfCurrentPage) {
        pdfRenderer.value?.let { renderer ->
            it.index < renderer.pageCount - 1
        }
    }

    val pdfRenderer = MediatorLiveData<PdfRenderer>().apply {
        addSource(document) { document ->
            try {
                val parcelDescriptor = openFileDescriptor(Uri.parse(document.uri))
                parcelDescriptor?.let {
                    value = PdfRenderer(it)
                }
            } catch (e: Exception) {
                Logger.e(KOIN_TAG, "renderer error: ${e.message}")
            }
        }
    }

    private fun openFileDescriptor(uri: Uri): ParcelFileDescriptor? {
        return application.contentResolver.openFileDescriptor(uri, "r")
    }

    private fun loadPage() {
        var bookmark = 0
        pdfCurrentPage.apply {
            addSource(pdfRenderer) { renderer ->
                launch {
                    val document = document.value
                    document?.let {
                        //bookmark = interActors.readAllBookmarks.invoke(it).lastOrNull()?.page ?: 0
                        val x = interActors.readAllBookmarks.invoke(it)
                            .lastOrNull { item -> item.page == pdfCurrentPage.value?.index }
                        bookmark = x?.page ?: 0
                    }
                    withContext(Dispatchers.Main) {
                        postValue(renderer.openPage(bookmark))
                    }
                }
            }
        }
    }

    fun nextPage() {
        pdfCurrentPage.value?.let {
            openPage(it.index.plus(1))
        }
        toggleBookmark()
        toggleDocument()
    }

    fun previousPage() {
        pdfCurrentPage.value?.let {
            openPage(it.index.minus(1))
        }
        toggleBookmark()
        toggleDocument()
    }

    private fun openPage(page: Int) {
        pdfRenderer.value?.let {
            pdfCurrentPage.value = it.openPage(page)
        }
    }

    fun toggleBookmark() {
        val currentPage = pdfCurrentPage.value?.index ?: return
        val document = document.value ?: return
        val bookmark = bookmarks.value?.firstOrNull { it.page == currentPage }

        Log.d(KOIN_TAG, "SEE CURRENT PAGE: $currentPage")

        launch {
            if (bookmark == null) {
                interActors.addBookmark.invoke(document, Bookmark(page = currentPage))
            } else {
                interActors.removeBookmark.invoke(document, bookmark)
            }

            bookmarks.postValue(interActors.readAllBookmarks.invoke(document))
        }
    }

    fun toggleDocument() {
        val myDocument = document.value ?: return

        launch {
            if (myDocument.uri != dataManager.getDocumentUrl()) {
                interActors.addDocument.invoke(myDocument)
            }
            document.postValue(myDocument)
        }
    }
}