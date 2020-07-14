package com.example.clean.framework.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.clean.databinding.ItemDocumentBinding
import com.example.clean.presentation.library.LibraryViewModel
import com.example.core.domain.Document

class LibraryVH(
    private val binding: ItemDocumentBinding,
    private val libraryViewModel: LibraryViewModel,
    private val documentClickListener: (Document) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(document: Document) {
        binding.viewModel = libraryViewModel
        binding.apply {
            tvDocumentName.text = document.name
            clDocumentContainer.setOnClickListener {
                documentClickListener.invoke(document)
            }
        }
    }
}