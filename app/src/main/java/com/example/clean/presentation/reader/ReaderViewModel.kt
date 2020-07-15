package com.example.clean.presentation.reader

import android.app.Application
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.clean.framework.action.InterActors
import com.example.clean.framework.viewmodel.PDFViewModel
import com.example.core.domain.Document
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.logger.KOIN_TAG

class ReaderViewModel(private val interActors: InterActors, private val application: Application) :
    PDFViewModel() {

    var document = MutableLiveData<Document>()

    fun setMyDocument(document: Document) {
        this.document.value = document
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
        addSource(document) {
            try {
                val parcelDescriptor = openFileDescriptor(Uri.parse(document.value?.uri))
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
                        bookmark = interActors.readAllBookmarks.invoke(it).lastOrNull()?.page ?: 0
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
    }

    fun previousPage() {
        pdfCurrentPage.value?.let {
            openPage(it.index.minus(1))
        }
    }

    private fun openPage(page: Int) {
        pdfRenderer.value?.let {
            pdfCurrentPage.value = it.openPage(page)
        }
    }
}