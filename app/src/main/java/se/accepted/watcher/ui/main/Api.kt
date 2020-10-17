package se.accepted.watcher.ui.main

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.coroutines.delay
import kotlin.random.Random

interface Api {
    suspend fun login(username: String, password: String): Result<User, Throwable>
}

/**
 * Fakes the implementation of an API call
 */
class ApiImpl : Api {
    override suspend fun login(username: String, password: String): Result<User, Throwable> {
        delay(Random.nextLong(500, 2000))
        return when(Random.nextBoolean()) {
            true -> Ok(User("Bob", "secret"))
            false -> Err(Error("We got error"))
        }
    }
}