package com.asos_codetest.spaceflightapp.view.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.asos_codetest.spaceflightapp.R
import com.asos_codetest.spaceflightapp.viewmodel.FlightViewModel
import kotlinx.android.synthetic.main.filter_dialog.*
import org.koin.android.viewmodel.ext.android.viewModel

class FilterDialog : BaseDialog() {

    companion object {
        @JvmStatic
        val instance = FilterDialog()
    }

    private val flightViewModel: FlightViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.filter_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        filterCloseBtn.setOnClickListener {
            this.dismiss()
        }

        // for flight search
        view.findViewById<SearchView>(R.id.searchView)
            .setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.apply {
                        flightViewModel.searchFlight(newText)
                    }
                   return false
                }

            })

    }

}