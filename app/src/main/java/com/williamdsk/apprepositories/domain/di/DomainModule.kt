package com.williamdsk.apprepositories.domain.di

import com.williamdsk.apprepositories.domain.ListUserRepositoriesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    const val OK_HTTP = "okHttp"

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory {
                ListUserRepositoriesUseCase(get())
            }
        }
    }

}