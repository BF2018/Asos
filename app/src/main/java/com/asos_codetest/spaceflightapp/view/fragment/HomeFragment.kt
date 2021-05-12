package com.asos_codetest.spaceflightapp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.asos_codetest.spaceflightapp.R
import com.asos_codetest.spaceflightapp.util.LoadingState
import com.asos_codetest.spaceflightapp.view.adapter.FlightAdapter
import com.asos_codetest.spaceflightapp.view.adapter.FlightClick
import com.asos_codetest.spaceflightapp.viewmodel.FlightViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Class is used to display flight data to the user
 */
class HomeFragment : Fragment(){

    private val flightViewModel: FlightViewModel by viewModel()
    private lateinit var flightAdapter: FlightAdapter
    private lateinit var companyInfoTv : TextView

    /**
     * When the class is initialised, inflate the fragment_home.xml layout
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /**
     * Once the class has been inflated, initialise the RecyclerView and ViewModel observers
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        companyInfoTv = view.findViewById(R.id.company_info_tv)
        initialiseRecyclerView(view)
        setupObservers()
    }

    /**
     * Function is used to intialise the RecyclerView and its Adapter
     * @param view
     */
    private fun initialiseRecyclerView(view: View){
        flightAdapter = FlightAdapter(FlightClick {
            flightViewModel.displayFlightDetails(it)
        })
        view.findViewById<RecyclerView>(R.id.flight_data_recyclerview).apply {
            adapter = flightAdapter
        }

        flightViewModel.navigateToSelectedFlight.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToFlightDetailsFragment(it)
                )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                flightViewModel.displayPropertyDetailsComplete()
            }
        })
    }

    /**
     * Function is used to listen for changes in state in the FlightViewModel class
     */
    private fun setupObservers(){
        setupCompanyObserver()

        flightViewModel.flightData.observe(viewLifecycleOwner, Observer {
            it?.flightReponse?.let { flights->
                flightAdapter.flightDataList = flights
            }

        })

        flightViewModel.loadingState.observe(viewLifecycleOwner, Observer {
            when(it.status){
                LoadingState.Status.RUNNING -> showSnackBar("Loading Flight Data")
                LoadingState.Status.SUCCESS -> showSnackBar("Data Loaded Successfully")
                LoadingState.Status.FAILED -> showSnackBar("Unable to Load Flight Data")
            }
        })

    }

    @SuppressLint("SetTextI18n")
    private fun setupCompanyObserver(){
        flightViewModel.companyInfo?.observe(viewLifecycleOwner, Observer { company->
            company?.let { companyInfo->
                val companyName = if (companyInfo.name == null){
                    ""
                }else companyInfo.name

                companyInfoTv.text =  "$companyName was founded by ${companyInfo.founder} in " +
                        "${companyInfo.founded}. It has now ${companyInfo.employees} employees, ${companyInfo.launchSites} launch sites" +
                        ", and is valued at USD ${companyInfo.valuation}"
            }

        })
    }

    /***
     * Function is used to display a message to the user
     * @param text
     */
    private fun showSnackBar(text: String){
        Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG).show()
    }

}