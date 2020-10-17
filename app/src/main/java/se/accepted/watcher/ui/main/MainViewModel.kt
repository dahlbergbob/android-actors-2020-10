package se.accepted.watcher.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import se.accepted.watcher.AppStream.send
import se.accepted.watcher.AppStream.states

@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {


    private val userFlow: Flow<UserState> = flow {
        emitAll(states.filterIsInstance())
    }

    val loggedIn = userFlow
        .map { it.user != User.EMPTY && it.error == null }
        .onStart { false }
        .onEach { _loading.postValue(false) }
        .asLiveData()

    val errorMessage = userFlow
        .map { it.error?.message ?: "" }
        .onStart { "" }
        .asLiveData()

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    fun login(username:String, password:String) {
        _loading.postValue(true)
        send(LoginMessage(username, password))
    }
}