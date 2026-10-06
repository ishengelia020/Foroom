package com.example.foroom.utils

import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import kotlinx.coroutines.runBlocking
import org.junit.rules.ExternalResource
import org.koin.core.context.GlobalContext

class ClearSessionRule : ExternalResource() {
    override fun before() {
        runBlocking {
            GlobalContext.get().get<ForoomUserDataStore>().clearUserData()
        }
    }
}