package com.example.clean.presentation.library

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.clean.framework.action.InterActors
import com.example.clean.framework.viewmodel.PDFViewModel
import com.example.clean.framework.viewmodel.SingleLiveData
import com.example.core.domain.Document
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.logger.KOIN_TAG
import java.lang.Exception

class LibraryViewModel(private val interActors: InterActors) : PDFViewModel() {

    val mutableFabButton = SingleLiveData<Any>()
    val mutableDocumentList = MutableLiveData<List<Document>>()

    init {
        showDocumentList()
    }

    fun onAddDocumentClicked() {
        Logger.d(KOIN_TAG, "onAddDocumentClicked")
        mutableFabButton.call()
    }

    private fun showDocumentList() {
        Logger.d(KOIN_TAG, "showDocumentList")
        launch {
            val documentList = interActors.readAllDocuments
            withContext(Dispatchers.Main) {
                try {
                    mutableDocumentList.postValue(documentList.invoke())
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "showDocumentList: see error message - ${e.message}")
                }
            }
        }
    }

    fun addDocument(url: String, documentName: String?, documentSize: Int) {
        Logger.d(KOIN_TAG, "onAddDocument")
        launch {
            val document = Document(
                uri = url,
                name = documentName ?: "",
                size = documentSize
            )
            interActors.addDocument.invoke(document)
            withContext(Dispatchers.Main) {
                try {
                    Log.d(KOIN_TAG, "Document was added to data base - $document")
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "addDocument: see error message - ${e.message}")
                }
            }
        }

        showDocumentList()
    }

    fun onDeleteDocumentClicked(document: Document){
        Logger.d(KOIN_TAG, "onDeleteDocumentClicked")
        launch {
            interActors.removeDocument.invoke(document)
            withContext(Dispatchers.Main){
                try {
                    Log.d(KOIN_TAG, "Document was deleted from data base - $document")
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "onDeleteDocumentClicked: see error message - ${e.message}")
                }
            }
        }
        showDocumentList()
    }
}