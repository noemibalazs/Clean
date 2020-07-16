package com.example.clean.framework.action

import android.content.Context
import com.example.clean.presentation.util.DOC_PAGE_KEY
import com.example.clean.presentation.util.DOC_URL_KEY
import com.example.clean.presentation.util.MY_PREFERENCES

class DataManager(private val context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE)

    fun saveDocumentUrl(url: String) {
        sharedPreferences.edit().putString(DOC_URL_KEY, url).apply()
    }

    fun getDocumentUrl(): String {
        return sharedPreferences.getString(DOC_URL_KEY, "") ?: ""
    }

    fun saveBookmark(currentPage: Int) {
        sharedPreferences.edit().putInt(DOC_PAGE_KEY, currentPage).apply()
    }

    fun getBookmark(): Int {
        return sharedPreferences.getInt(DOC_PAGE_KEY, 0)
    }
}