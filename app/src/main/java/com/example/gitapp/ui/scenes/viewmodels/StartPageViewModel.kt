package com.example.gitapp.ui.scenes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.gitapp.domain.usecase.UsersUseCase
import com.example.gitapp.ui.scenes.model.User
import io.reactivex.rxjava3.disposables.CompositeDisposable

class StartPageViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val usersUseCase = UsersUseCase(compositeDisposable)

    val usersPagedList: LiveData<PagedList<User>> =
        usersUseCase.fetchLiveUserPageList()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}