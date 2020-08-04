package com.example.gitapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.gitapp.ui.scenes.model.UserUI
import io.reactivex.rxjava3.disposables.CompositeDisposable

interface Repository {
    fun fetchLiveUserPageList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<UserUI>>
}