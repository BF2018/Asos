package com.asos_codetest.spaceflightapp.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asos_codetest.spaceflightapp.databinding.FragmentFlightDetailsBinding
import com.asos_codetest.spaceflightapp.viewmodel.FlightDetailsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FlightDetailsFragment : Fragment() {
    val viewModel by viewModel<FlightDetailsViewModel>()

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentFlightDetailsBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        val characterProperty = FlightDetailsFragmentArgs.fromBundle(arguments!!).selectedFlight
        binding.viewModel = viewModel
        viewModel.setFlight(characterProperty)

        binding.youtube.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse( characterProperty.links.webcast))
            startActivity(intent)
        })

        binding.wiki.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse( characterProperty.links.wikipedia))
            startActivity(intent)
        })

        return binding.root

    }
}