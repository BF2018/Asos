package com.asos_codetest.spaceflightapp.model.flight

import com.google.gson.annotations.SerializedName

/**
 * This class describes the status of the outer shell of the craft recovery during launch
 */
data class Fairings (
	@SerializedName("reused") val reused : Boolean,
	@SerializedName("recovery_attempt") val recovery_attempt : Boolean,
	@SerializedName("recovered") val recovered : Boolean,
	@SerializedName("ships") val ships : List<String>
)
