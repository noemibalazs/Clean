package com.example.clean.presentation.library

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import com.example.clean.R
import com.example.clean.databinding.FragmentLibraryBinding
import com.example.clean.framework.action.DataManager
import com.example.clean.framework.adapter.LibraryAdapter
import com.example.clean.presentation.util.REQUEST_CODE
import com.example.clean.presentation.util.openCreateDocument
import com.example.core.domain.Document
import com.orhanobut.logger.Logger
import org.koin.android.ext.android.inject
import org.koin.core.logger.KOIN_TAG
import java.io.File

class FragmentLibrary : Fragment() {

    private val libraryViewModel: LibraryViewModel by inject()
    private val dataManager: DataManager by inject()
    private lateinit var binding: FragmentLibraryBinding
    private lateinit var libraryAdapter: LibraryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_library, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        setObservers()
    }

    private fun initBinding() {
        binding.viewModel = libraryViewModel
        libraryAdapter = LibraryAdapter(
            libraryViewModel,
            this::onDocumentClicked,
            Glide.with(this),
            this::onDeleteDocumentClicked
        )
        binding.rvLibrary.adapter = libraryAdapter
    }

    private fun onDocumentClicked(document: Document) {
        findNavController().navigate(
            R.id.navigateFromLibraryToReaderFragment
        )
        dataManager.saveDocumentUrl(document.uri)
    }

    private fun onDeleteDocumentClicked(document: Document) {
        activity?.let {
            MaterialDialog(it).show {
                title(R.string.txt_dialog_title)
                positiveButton(R.string.txt_ok)
                negativeButton(R.string.txt_cancel)
                positiveButton {
                    libraryViewModel.onDeleteDocumentClicked(document)
                }
                negativeButton {
                    dismiss()
                }
            }
        }
    }

    private fun setObservers() {
        libraryViewModel.mutableFabButton.observe(viewLifecycleOwner, Observer {
            startActivityForResult()
        })

        libraryViewModel.mutableDocumentList.observe(viewLifecycleOwner, Observer {
            libraryAdapter.submitList(it)
        })
    }

    private fun startActivityForResult() {
        startActivityForResult(openCreateDocument(), REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                val uriString = it.data.toString()
                val file = File(uriString)
                var displayName: String? = null
                var displaySize = 0
                when {
                    uriString.startsWith("file://") -> {
                        displayName = file.name
                    }
                    uriString.startsWith("content://") -> {
                        var cursor: Cursor? = null
                        try {
                            cursor =
                                activity?.contentResolver?.query(it.data!!, null, null, null, null)
                            cursor?.let {
                                if (cursor.moveToFirst()) {
                                    displayName = cursor.getString(
                                        cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
                                    )
                                    displaySize =
                                        cursor.getInt(cursor.getColumnIndexOrThrow(OpenableColumns.SIZE))
                                }
                            }
                        } finally {
                            cursor?.close()
                        }
                    }
                    else -> displayName = null
                }

                libraryViewModel.addDocument(uriString, displayName, displaySize)
                Logger.d(KOIN_TAG, "DOC: name is - $displayName size is-  $displaySize")
            }
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }
}