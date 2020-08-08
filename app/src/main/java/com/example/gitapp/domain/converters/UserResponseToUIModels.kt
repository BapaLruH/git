package com.example.gitapp.domain.converters

import com.example.gitapp.data.model.UserDetailsResponse
import com.example.gitapp.data.model.UserResponse
import com.example.gitapp.ui.scenes.model.User
import com.example.gitapp.ui.scenes.model.UserDetails

fun convertUserResponseToUserDetails(
    userDetailsResponse: UserDetailsResponse,
    createdAt: String,
    updatedAt: String
) = UserDetails(
    userDetailsResponse.id,
    userDetailsResponse.login,
    userDetailsResponse.name,
    userDetailsResponse.bio,
    userDetailsResponse.avatar,
    userDetailsResponse.htmlUrl,
    userDetailsResponse.blog,
    userDetailsResponse.email,
    userDetailsResponse.twitter_username,
    userDetailsResponse.public_repos,
    userDetailsResponse.public_gists,
    userDetailsResponse.followers,
    userDetailsResponse.following,
    createdAt,
    updatedAt
)

fun convertUserResponseToUserUI(userResponse: UserResponse) = User(
    userResponse.login,
    userResponse.id,
    userResponse.avatar,
    userResponse.htmlUrl
)