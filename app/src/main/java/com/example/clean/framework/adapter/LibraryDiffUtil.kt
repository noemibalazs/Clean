package com.example.clean.framework.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.core.domain.Document

class LibraryDiffUtil : DiffUtil.ItemCallback<Document>() {

    override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
        return oldItem == newItem
    }
}