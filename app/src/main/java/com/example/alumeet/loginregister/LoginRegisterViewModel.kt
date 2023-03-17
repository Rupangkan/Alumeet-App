package com.example.alumeet.loginregister

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alumeet.core.data.local.room.repositories.UsersRepository
import com.example.alumeet.core.data.model.local.User
import com.example.alumeet.core.data.remote.Resource
import com.example.alumeet.core.data.remote.repositories.users.UserRepository
import com.example.alumeet.core.di.dispatcher.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val dispatcherProvider: DispatcherProvider,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _acceptedRide: MutableLiveData<User> = MutableLiveData()
    val acceptedRide: LiveData<User> = _acceptedRide

    fun getUser(email: String, password: String, onSuccess: (List<User>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(dispatcherProvider.io) {
            val acceptedRides = usersRepository.getUser(email, password)
            launch(dispatcherProvider.main) {
                onSuccess(acceptedRides)
            }
            if (acceptedRides.isNotEmpty()) {
                _acceptedRide.postValue(acceptedRides.last())
            }
        }
    }

    init {
        getUsers({}, {})
    }

    fun getUsers(onSuccess: (Any) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch(dispatcherProvider.io) {
            userRepository.getUsers().collect {
                launch(dispatcherProvider.main) {
                    when (it.status) {
                        Resource.Status.SUCCESS -> {
                            Timber.d("Success: ${it.data}")
                            it.data?.let { onSuccess(it) }
                        }
                        Resource.Status.ERROR -> {
                            Timber.d("Failure: ${it.message}")
                            it.message?.let { onError(it) }
                        }
                        Resource.Status.LOADING -> Unit
                    }
                }
            }
        }
    }

    fun insertUser(user: User) {
        getUser(user.email, user.password, {
            if (!it.contains(user)) {
                viewModelScope.launch(dispatcherProvider.io) {
                    usersRepository.insertUser(user)
                }
            } else {
                Timber.d("User already there")
            }
        }, {
            Timber.d(it)
        })
    }
}