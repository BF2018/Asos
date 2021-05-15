package com.asos_codetest.spaceflightapp.model.flight

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

/**
 * Class is used to represent flight data
 */


@SuppressLint("ParcelCreator")
data class Flight(

	@SerializedName("fairings") val fairings: Fairings,
	@SerializedName("links") val links: Links,
	@SerializedName("static_fire_date_utc") val static_fire_date_utc: String,
	@SerializedName("static_fire_date_unix") val static_fire_date_unix: Int,
	@SerializedName("tbd") val tbd: Boolean,
	@SerializedName("net") val net: Boolean,
	@SerializedName("window") val window: Int,
	@SerializedName("rocket") val rocket: String,
	@SerializedName("success") val success: Boolean,
	@SerializedName("details") val details: String,
	@SerializedName("crew") val crew: List<String>,
	@SerializedName("ships") val ships: List<String>,
	@SerializedName("capsules") val capsules: List<String>,
	@SerializedName("payloads") val payloads: List<String>,
	@SerializedName("launchpad") val launchpad: String,
	@SerializedName("auto_update") val auto_update: Boolean,
	@SerializedName("failures") val failures: List<Failures>,
	@SerializedName("flight_number") val flight_number: Int,
	@SerializedName("name") val name: String,
	@SerializedName("date_utc") val date_utc: String,
	@SerializedName("date_unix") val date_unix: Int,
	@SerializedName("date_local") val date_local: String,
	@SerializedName("date_precision") val date_precision: String,
	@SerializedName("upcoming") val upcoming: Boolean,
	@SerializedName("cores") val cores: List<Cores>,
	@SerializedName("id") val id: String
):Parcelable{
	override fun describeContents(): Int {
		TODO("Not yet implemented")
	}

	override fun writeToParcel(p0: Parcel?, p1: Int) {
		TODO("Not yet implemented")
	}


	@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
	@SuppressLint("SimpleDateFormat")
	fun isSinceOrFrom(): String {
		val timeDateFormat = SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss")
		var daysBetween = 0
		try {
			val dateBefore= timeDateFormat.parse(date_utc)
			val currentTime = timeDateFormat.format(Date())
			val difference = timeDateFormat.parse(currentTime).time - dateBefore.time
			daysBetween = (difference / (1000 * 60 * 60 * 24)).toInt()
		} catch (e: Exception) {
			e.printStackTrace()
		}
		return if (timeDateFormat
				.parse(date_utc)
				.before(Date())){
			"Days Since $daysBetween"
		}else{
			"From $daysBetween days"
		}
	}
}

/**
 * Class is used to store Flight data to the local Room database
 */

@Entity
data class FlightResponse(
	@PrimaryKey val flightResponseId: Int = 1,
	val flightReponse: List<Flight>
)
