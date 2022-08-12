package com.bendingbytes.shoes.ui.fragments

import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.bendingbytes.shoes.R
import com.bendingbytes.shoes.domain.DataState
import com.bendingbytes.shoes.ui.ShoeAdapter
import com.bendingbytes.shoes.ui.ShoeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class ListFragments : Fragment() {
    private lateinit var progressDialog: ProgressDialog
    private val shoeViewModel: ShoeViewModel by activityViewModels()
    private var adapter: ShoeAdapter = ShoeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return (inflater.inflate(R.layout.fragment_list, container, false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage(getString(R.string.wait_while_loading))

        recyclerView.adapter = adapter
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        context?.let {
            val dividerDrawable = ContextCompat.getDrawable(it, R.drawable.divider_white)
            if (dividerDrawable != null) {
                itemDecorator.setDrawable(dividerDrawable)
            }
        }
        recyclerView.addItemDecoration(itemDecorator)
        
        subscribeToObservables()
        shoeViewModel.loadShoes()
    }

    private fun subscribeToObservables() {
        shoeViewModel.shoeLiveDataState.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Loading -> {
                    progressDialog.show()
                }
                is DataState.Error -> {
                    onDataStateError()
                }
                is DataState.Success -> {
                    adapter.submitList(it.data)
                    progressDialog.dismiss()
                }
            }
        }
    }

    private fun onDataStateError() {
        AlertDialog.Builder(context)
            .setMessage(getString(R.string.error))
            .setCancelable(false)
            .show()
    }

    companion object {
        fun newInstance() = ListFragments()
    }
}


