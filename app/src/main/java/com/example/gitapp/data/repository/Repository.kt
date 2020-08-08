package com.example.gitapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.gitapp.data.model.UserDetailsResponse
import com.example.gitapp.data.model.UserResponse
import com.example.gitapp.ui.scenes.model.User
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

interface Repository {
    fun fetchLiveUserPageList(compositeDisposable: CompositeDisposable): LiveData<PagedList<User>>
    fun fetchLiveUserDetailsData(login: String): Single<UserDetailsResponse>
}