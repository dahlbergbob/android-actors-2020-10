package se.accepted.watcher.ui.main

import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import se.accepted.watcher.Message
import se.accepted.watcher.State

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class LogActor : Actor() {

    override suspend fun act(message: Message) {
        val tag = if (message is State) "<<" else ">>"
        Log.i(tag, message.toString())
    }
}