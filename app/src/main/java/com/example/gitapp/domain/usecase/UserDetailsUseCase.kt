package com.example.gitapp.domain.usecase

import com.example.gitapp.data.api.ApiClient
import com.example.gitapp.data.model.UserDetailsResponse
import com.example.gitapp.data.repository.Repository
import com.example.gitapp.data.repository.UsersRepository
import io.reactivex.rxjava3.core.Single

class UserDetailsUseCase {
    private var repository: Repository = UsersRepository(ApiClient.getApiClient())

    fun fetchLiveUserDetailsData(login: String): Single<UserDetailsResponse> {
        return repository.fetchLiveUserDetailsData(login)
    }
}