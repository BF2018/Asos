package com.asos_codetest.spaceflightapp.model.companyinfo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Company(
    @PrimaryKey
    val id: Int = 1,
    @SerializedName("ceo") var ceo: String?,
    @SerializedName("coo") var coo: String?,
    @SerializedName("cto") var cto: String?,
    @SerializedName("cto_propulsion") var ctoPropulsion: String?,
    @SerializedName("employees") var employees: Int?,
    @SerializedName("founded") var founded: Int?,
    @SerializedName("founder") var founder: String?,
    @SerializedName("launch_sites") var launchSites: Int?,
    @SerializedName("name") var name: String?="",
    @SerializedName("summary") var summary: String?,
    @SerializedName("test_sites") var testSites: Int?,
    @SerializedName("valuation") var valuation: Long?,
    @SerializedName("vehicles") var vehicles: Int?
)

