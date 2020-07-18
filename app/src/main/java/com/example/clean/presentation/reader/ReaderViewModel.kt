package com.example.clean.presentation.reader

import android.app.Application
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.clean.framework.action.DataManager
import com.example.clean.framework.action.InterActors
import com.example.clean.framework.viewmodel.PDFViewModel
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
                        bookmark = it.page
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
            val page = it.index.plus(1)
            launch {
                val doc = document.value
                doc?.let {
                    val document = Document(uri = doc.uri, name = doc.name, size =  doc.size, page = page)
                    interActors.addDocument.invoke(document)
                }
            }
        }
    }

    fun previousPage() {
        pdfCurrentPage.value?.let {
            openPage(it.index.minus(1))
            val page = it.index.minus(1)
            launch {
                val doc = document.value
                doc?.let {
                    val document = Document(uri = doc.uri, name = doc.name, size =  doc.size, page = page)
                    interActors.addDocument.invoke(document)
                }
            }
        }
    }

    private fun openPage(page: Int) {
        pdfRenderer.value?.let {
            pdfCurrentPage.value = it.openPage(page)
        }
    }
}