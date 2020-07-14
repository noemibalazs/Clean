package com.example.clean.framework.viewmodel

import androidx.lifecycle.ViewModel
import com.example.clean.framework.action.InterActors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class PDFViewModel : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}