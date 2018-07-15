package me.portfolio.aungkhanthtoo.healthcare.data.vos

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "health_info")
data class HealthcareInfo(

        @PrimaryKey
        @SerializedName("id")
        var id: Int = -1,

        @SerializedName("title")
        var title: String = "",

        @SerializedName("image")
        var image: String = "",

        @Embedded
        @SerializedName("author")
        var author: Author? = null,

        @SerializedName("short-description")
        var shortDescription: String = "",

        @SerializedName("published-date")
        var publishedDate: String = "",

        @SerializedName("complete-url")
        var completeUrl: String = "",

        @SerializedName("info-type")
        var infoType: String = ""
)