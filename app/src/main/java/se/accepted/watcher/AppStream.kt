package se.accepted.watcher

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext


interface Message
interface State : Message

@ExperimentalCoroutinesApi
object AppStream {

    private val appScope: CoroutineScope = CoroutineScope(EmptyCoroutineContext + SupervisorJob())

    private val stream: BroadcastChannel<Message> = BroadcastChannel(100)

    fun send(event: Message) {
        appScope.launch {
            stream.send(event)
        }
    }

    val messages: Flow<Message>
        get() = flow { emitAll(stream.openSubscription()) }

    // Same as messages, only filtered on messages implementing the State interface.
    val states: Flow<State>
        get() = flow {
            emitAll(stream.openSubscription())
        }.filterIsInstance()

}