package com.example.gitapp.ui.scenes.model

data class UserDetails(
    val id: Int,
    val login: String,
    val name: String?,
    val bio: String?,
    val avatar: String?,
    val htmlUrl: String?,
    val blog: String?,
    val email: String?,
    val twitter_username: String?,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    val createdAt: String,
    val updatedAt: String
)