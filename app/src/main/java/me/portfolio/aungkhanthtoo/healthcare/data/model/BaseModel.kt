package me.portfolio.aungkhanthtoo.healthcare.data.model

import android.app.Application
import com.google.gson.Gson
import me.portfolio.aungkhanthtoo.healthcare.network.HealthApi
import me.portfolio.aungkhanthtoo.healthcare.persistence.AppDatabase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel(app: Application) {

    protected var mAPI: HealthApi
        private set
    protected var mDB: AppDatabase

    init {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        mAPI = Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-5/mm-healthcare/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()
                .create(HealthApi::class.java)

        mDB = AppDatabase.getInstance(app.applicationContext)
    }

}