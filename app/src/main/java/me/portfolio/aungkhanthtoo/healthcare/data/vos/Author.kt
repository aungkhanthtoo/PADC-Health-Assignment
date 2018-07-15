package me.portfolio.aungkhanthtoo.healthcare.data.vos

import com.google.gson.annotations.SerializedName

data class Author(
        @SerializedName("author-id") val authorId: Int,
        @SerializedName("author-name") val authorName: String,
        @SerializedName("author-picture") val authorPicture: String
)