package me.portfolio.aungkhanthtoo.healthcare.network.response

import com.google.gson.annotations.SerializedName
import me.portfolio.aungkhanthtoo.healthcare.data.vos.HealthcareInfo

data class HealthResponse(
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String,
        @SerializedName("healthcare-info") val healthcareInfoList: List<HealthcareInfo>
)