package me.portfolio.aungkhanthtoo.healthcare.network

import io.reactivex.Single
import me.portfolio.aungkhanthtoo.healthcare.network.response.HealthResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HealthApi {

    @FormUrlEncoded
    @POST("GetHealthcareInfo.php")
    fun fetchHealthInfos(@Field("access_token") token: String): Single<HealthResponse>

}