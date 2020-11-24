package se.accepted.watcher.ui.main

import com.github.michaelbull.result.getError
import com.github.michaelbull.result.onSuccess
import kotlinx.coroutines.ObsoleteCoroutinesApi
import se.accepted.watcher.Message
import se.accepted.watcher.State


data class LoginMessage(val username:String, val password: String) : Message
data class UserState(val user: User, val error: Throwable?) : State

data class User(val username: String, val token: String) {
    companion object {
        val EMPTY = User("NONE", "")
    }
}


@ObsoleteCoroutinesApi
class UserActor(private val api: Api) : Actor() {

    private var user: User = User.EMPTY

    override suspend fun act(message: Message) {
        when(message) {
            is LoginMessage -> login(message.username, message.password)
        }
    }

    private suspend fun login(username: String, password: String) {
        val result = api.login(username, password)
            .onSuccess { user = it }

        send(UserState(user, result.getError()))
    }
}