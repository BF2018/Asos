package com.asos_codetest.spaceflightapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asos_codetest.spaceflightapp.databinding.RowFlightItemBinding
import com.asos_codetest.spaceflightapp.model.flight.Flight

/**
 * Class is used to provide flight data to the Adapter and display it in a RecyclerView
 */
class FlightAdapter(private val callback: FlightClick) : RecyclerView.Adapter<FlightViewHolder>(){

    var flightDataList = emptyList<Flight>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    /**
     * Function inflates the row_flight_item.xml layout and instantiates the ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val view = RowFlightItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return FlightViewHolder(view)
    }

    /**
     * Function data binds the data in the flightDataList to the inflated layout
     */
    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        holder.binding.also {
            it.flight = flightDataList[position]
            it.resultsCallback = callback
        }
    }

    /**
     * Function returns the size of flightDataList
     * @return size of flightDataList
     */
    override fun getItemCount(): Int {
       return flightDataList.size
    }
}

/**
 * Class is used to instantiate the ViewHolder required to populate the Adapter
 */
class FlightViewHolder(val binding: RowFlightItemBinding) : RecyclerView.ViewHolder(binding.root)

class FlightClick(val block: (Flight) -> Unit) {
    /**
     * Called when a character is clicked
     *
     * @param flight the character that was clicked
     */
    fun onClick(flight: Flight) = block(flight)
}
