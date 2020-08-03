package com.example.gitapp.ui.scenes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitapp.ui.scenes.model.UserUI

class StartPageViewModel : ViewModel() {

    private val _usersLiveData : MutableLiveData<List<UserUI>> = MutableLiveData<List<UserUI>>().apply { value = listOf(
        UserUI("login user first", 1, "https://avatars0.githubusercontent.com/u/1?v=4", "https://github.com/mojombo", "User"),
        UserUI("login user second", 2, "", "https://github.com/mojombo", "Admin"),
        UserUI("login user third", 3, "https://avatars0.githubusercontent.com/u/1?v=4", "https://github.com/mojombo", "User"),
        UserUI("login user fourth", 4, "https://avatars0.githubusercontent.com/u/1?v=4", "https://github.com/bapalruh", "Admin"),
        UserUI("login user fifth", 5, "https://avatars0.githubusercontent.com/u/1?v=4", "https://github.com/mojombo", "Admin"),
        UserUI("login user sixth", 6, "", "https://github.com/mojombo", "User")
    ) }
    val userLiveData: LiveData<List<UserUI>> = _usersLiveData

    override fun onCleared() {

        super.onCleared()
    }
}