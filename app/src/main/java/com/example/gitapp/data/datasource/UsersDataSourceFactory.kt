package com.example.gitapp.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.gitapp.data.api.GitHubApiInterface
import com.example.gitapp.ui.scenes.model.UserUI
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UsersDataSourceFactory(
    private val gitHubApiService: GitHubApiInterface,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, UserUI>() {

    private val usersLiveDataSource = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Int, UserUI> {
        val usersDataSource = UsersDataSource(
            gitHubApiService,
            compositeDisposable
        )
        usersLiveDataSource.postValue(usersDataSource)
        return usersDataSource
    }
}