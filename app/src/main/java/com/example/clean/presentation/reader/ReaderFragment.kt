package com.example.clean.presentation.reader

import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.drawable.BitmapDrawable
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.clean.R
import com.example.clean.databinding.FragmentReaderBinding
import kotlinx.android.synthetic.main.fragment_reader.*
import org.koin.android.ext.android.inject
import org.koin.core.logger.KOIN_TAG

class ReaderFragment : Fragment() {

    private val readerViewModel: ReaderViewModel by inject()
    private lateinit var binding: FragmentReaderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_reader, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        setObservers()
    }

    private fun initBinding() {
        binding.viewModel = readerViewModel
    }

    private fun setObservers() {
        readerViewModel.getDocument()

        readerViewModel.document.observe(viewLifecycleOwner, Observer {
            Log.d(KOIN_TAG, "Document name is: ${it.name}")
        })

        readerViewModel.pdfCurrentPage.observe(viewLifecycleOwner, Observer {
            showPage(it)
        })

        readerViewModel.hasNextPage.observe(viewLifecycleOwner, Observer {
            binding.tvPageNext.isEnabled = it
        })

        readerViewModel.hasPreviousPage.observe(viewLifecycleOwner, Observer {
            binding.tvPagePrevious.isEnabled = it
        })

        binding.tvPageNext.setOnClickListener {
            readerViewModel.nextPage()
        }

        binding.tvPagePrevious.setOnClickListener {
            readerViewModel.previousPage()
        }
    }

    private fun showPage(page: PdfRenderer.Page) {
        if (pv_page_reader.drawable != null)
            (pv_page_reader.drawable as BitmapDrawable).bitmap.recycle()

        val size = Point()
        activity?.windowManager?.defaultDisplay?.getSize(size)

        val width = size.x
        val height = page.height * width / page.width

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        pv_page_reader.setImageBitmap(bitmap)

        binding.tvReaderPageNumber.text = getString(
            R.string.txt_page, page.index + 1,
            readerViewModel.pdfRenderer.value?.pageCount
        )

        page.close()
    }
}