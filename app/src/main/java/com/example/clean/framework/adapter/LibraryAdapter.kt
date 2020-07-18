package com.example.clean.framework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import com.example.clean.R
import com.example.clean.databinding.ItemDocumentBinding
import com.example.clean.presentation.library.LibraryViewModel
import com.example.core.domain.Document

class LibraryAdapter(
    private val libraryViewModel: LibraryViewModel,
    private val documentClickListener: (Document) -> Unit,
    private val deleteClickListener: (Document) -> Unit
) : ListAdapter<Document, LibraryVH>(LibraryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryVH {
        val binding: ItemDocumentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_document,
            parent,
            false
        )
        return LibraryVH(binding, libraryViewModel, documentClickListener, deleteClickListener)
    }

    override fun onBindViewHolder(holder: LibraryVH, position: Int) {
        holder.onBind(getItem(position))
    }

}