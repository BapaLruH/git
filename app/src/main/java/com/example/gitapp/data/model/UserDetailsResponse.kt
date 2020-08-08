package com.example.gitapp.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
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
    val siteAdmin: Boolean,

    @SerializedName("name")
    val name: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("hireable")
    val hireable: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("twitter_username")
    val twitter_username: String,
    @SerializedName("public_repos")
    val public_repos: Int,
    @SerializedName("public_gists")
    val public_gists: Int,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String
)