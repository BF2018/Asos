package com.asos_codetest.spaceflightapp.model.flight

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * Class is used to provide images of the flight organization's logo
 */
@Entity
data class Badge (
	@SerializedName("small") val small : String?,
	@SerializedName("large") val large : String?
)
