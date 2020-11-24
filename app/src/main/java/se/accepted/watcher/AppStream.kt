package se.accepted.watcher

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

interface Message
interface State : Message

object AppStream {

    private val appScope: CoroutineScope = CoroutineScope(EmptyCoroutineContext + SupervisorJob())

    private val stream: MutableSharedFlow<Message> = MutableSharedFlow(extraBufferCapacity = 100)

    fun send(event: Message) {
        appScope.launch {
            stream.emit(event)
        }
    }

    val messages: Flow<Message>
        get() = stream

    // Same as messages, only filtered on messages implementing the State interface.
    val states: Flow<State>
        get() = stream.filterIsInstance()

}