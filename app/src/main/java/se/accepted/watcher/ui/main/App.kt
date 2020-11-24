package se.accepted.watcher.ui.main

import android.app.Application
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val api = createApi()
        UserActor(api).start()
        LogActor().start()
    }

    private fun createApi(): Api = ApiImpl()
}