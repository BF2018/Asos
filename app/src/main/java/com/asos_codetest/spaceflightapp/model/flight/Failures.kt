package com.asos_codetest.spaceflightapp.model.flight

import com.google.gson.annotations.SerializedName

/**
 * Class is used to record reasons for flight failure
 */
data class Failures (
	@SerializedName("time") val time : Int,
	@SerializedName("altitude") val altitude : String,
	@SerializedName("reason") val reason : String
)
