package com.example.clean.presentation.util

import android.content.Intent

fun openCreateDocument(): Intent {
    return Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "application/pdf"
    }
}

