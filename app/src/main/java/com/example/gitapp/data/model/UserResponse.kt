package com.example.gitapp.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatar: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean
)