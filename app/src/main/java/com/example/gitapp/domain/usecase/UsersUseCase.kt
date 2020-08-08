package com.example.gitapp.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.gitapp.data.api.ApiClient
import com.example.gitapp.data.repository.Repository
import com.example.gitapp.data.repository.UsersRepository
import com.example.gitapp.ui.scenes.model.User
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UsersUseCase(
    private val compositeDisposable: CompositeDisposable
) {
    private var repository: Repository = UsersRepository(ApiClient.getApiClient())

    fun fetchLiveUserPageList(): LiveData<PagedList<User>> {
        return repository.fetchLiveUserPageList(compositeDisposable)
    }
}