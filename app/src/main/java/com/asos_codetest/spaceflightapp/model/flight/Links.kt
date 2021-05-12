package com.asos_codetest.spaceflightapp.model.flight

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * This class provides access to media related to the flight
 */

@Entity
data class Links (
	@SerializedName("patch") val badge : Badge,
	@SerializedName("presskit") val presskit : String,
	@SerializedName("webcast") val webcast : String,
	@SerializedName("youtube_id") val youtube_id : String,
	@SerializedName("article") val article : String,
	@SerializedName("wikipedia") val wikipedia : String
)
