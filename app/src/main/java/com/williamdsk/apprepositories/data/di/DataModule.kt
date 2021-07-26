package com.williamdsk.apprepositories.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.williamdsk.apprepositories.data.repositories.RepoRepository
import com.williamdsk.apprepositories.data.repositories.RepoRepositoryImpl
import com.williamdsk.apprepositories.data.services.GitHubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.experimental.builder.create
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {
    const val OK_HTTP = "okHttp"

    fun load() {
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module{
        return module {
            single {
                val interceptor = HttpLoggingInterceptor{
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = Level.BODY

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single {
                createService<GitHubService>(get(), get())
            }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<RepoRepository> {
                RepoRepositoryImpl(get())
            }
        }
    }
    private inline fun <reified T> createService(client: OkHttpClient, factory: GsonConverterFactory): T{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(factory)
            .build().create(T::class.java)
    }

}