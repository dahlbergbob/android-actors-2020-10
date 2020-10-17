package se.accepted.watcher.ui.main

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.flow.collect
import se.accepted.watcher.AppStream
import se.accepted.watcher.AppStream.messages
import se.accepted.watcher.Message

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
abstract class Actor {

    private val scope = CoroutineScope(Dispatchers.Default + Job())

    fun start() = scope.launch {
        val actor = actor<Message>(scope.coroutineContext) {
            for (msg in channel) {
                act(msg)
            }
        }
        messages.collect(actor::send)
    }

    fun stop() {
        scope.cancel()
    }

    protected fun send(message: Message) = AppStream.send(message)

    protected open suspend fun act(message: Message) {}
}