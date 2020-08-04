package com.example.gitapp.ui.scenes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.gitapp.data.api.ApiClient
import com.example.gitapp.data.repository.UsersPagedListRepository
import com.example.gitapp.ui.scenes.model.UserUI
import io.reactivex.rxjava3.disposables.CompositeDisposable

class StartPageViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val repository = UsersPagedListRepository(ApiClient.getApiClient())

    val usersPagedList: LiveData<PagedList<UserUI>> =
        repository.fetchLiveUserPageList(compositeDisposable)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}