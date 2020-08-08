package com.example.gitapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.gitapp.data.api.GitHubApiInterface
import com.example.gitapp.data.api.USERS_PER_PAGE
import com.example.gitapp.data.datasource.UsersDataSourceFactory
import com.example.gitapp.data.model.UserDetailsResponse
import com.example.gitapp.data.model.UserResponse
import com.example.gitapp.ui.scenes.model.User
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

class UsersRepository(private val gitHubApiService: GitHubApiInterface) : Repository {

    private lateinit var usersPagedList: LiveData<PagedList<User>>
    private lateinit var usersDataSourceFactory: UsersDataSourceFactory

    override fun fetchLiveUserPageList(compositeDisposable: CompositeDisposable): LiveData<PagedList<User>> {
        usersDataSourceFactory = UsersDataSourceFactory(gitHubApiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(USERS_PER_PAGE)
            .build()

        usersPagedList = LivePagedListBuilder(usersDataSourceFactory, config).build()

        return usersPagedList
    }

    override fun fetchLiveUserDetailsData(login: String): Single<UserDetailsResponse> {
        return gitHubApiService.getUserById(login)
    }
}