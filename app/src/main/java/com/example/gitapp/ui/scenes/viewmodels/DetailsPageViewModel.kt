package com.example.gitapp.ui.scenes.viewmodels

import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitapp.domain.converters.convertUserResponseToUserDetails
import com.example.gitapp.domain.usecase.UserDetailsUseCase
import com.example.gitapp.ui.scenes.model.UserDetails
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DetailsPageViewModel : ViewModel() {

    private val tag = DetailsPageViewModel::class.java.simpleName
    private val compositeDisposable = CompositeDisposable()
    private val userDetailsUseCase = UserDetailsUseCase()

    private val _loadingLiveData = MutableLiveData<Boolean>().apply { value = true }
    val loadingLiveData = _loadingLiveData

    fun getUserDetailsLiveData(login: String): LiveData<UserDetails> {
        val result = MutableLiveData<UserDetails>()

        compositeDisposable.add(
            userDetailsUseCase.fetchLiveUserDetailsData(login)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { userResponse ->
                        _loadingLiveData.postValue(false)
                        userResponse?.let {
                            result.postValue(
                                convertUserResponseToUserDetails(
                                    it,
                                    convertDate(it.createdAt),
                                    convertDate(it.updatedAt)
                                )
                            )
                        }
                    },
                    {
                        it.message?.let { error -> Log.e(tag, error) }
                    }
                )
        )
        return result
    }

    private fun convertDate(date: String): String =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localDate =
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
            localDate.toString()
        } else {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(parser.parse(date))
        }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}