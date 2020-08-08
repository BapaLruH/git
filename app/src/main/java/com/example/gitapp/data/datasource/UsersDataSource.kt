package com.example.gitapp.data.datasource

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.example.gitapp.data.api.GitHubApiInterface
import com.example.gitapp.data.api.START_POSITION
import com.example.gitapp.data.api.USERS_PER_PAGE
import com.example.gitapp.domain.converters.convertUserResponseToUserUI
import com.example.gitapp.ui.scenes.model.User
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UsersDataSource(
    private val gitHubApiService: GitHubApiInterface,
    private val compositeDisposable: CompositeDisposable
) : ItemKeyedDataSource<Int, User>() {
    private val tag = UsersDataSource::class.java.simpleName
    private val startPosition = START_POSITION
    private val usersPerPage = USERS_PER_PAGE

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<User>
    ) {
        compositeDisposable.add(
            gitHubApiService.getUsersList(startPosition, usersPerPage)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.map { userResponse ->
                            convertUserResponseToUserUI(
                                userResponse
                            )
                        })
                    }, {
                        it?.localizedMessage?.let { error -> Log.e(tag, error) }
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<User>) {
        compositeDisposable.add(
            gitHubApiService.getUsersList(params.key, usersPerPage)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.map { userResponse ->
                            convertUserResponseToUserUI(
                                userResponse
                            )
                        })
                    }, {
                        it?.localizedMessage?.let { error -> Log.e(tag, error) }
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<User>) {

    }

    override fun getKey(item: User): Int {
        return item.id
    }
}
