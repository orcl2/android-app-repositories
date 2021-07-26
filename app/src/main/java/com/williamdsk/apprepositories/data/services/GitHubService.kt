package com.williamdsk.apprepositories.data.services

import com.williamdsk.apprepositories.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user")user: String) : List<Repo>
}