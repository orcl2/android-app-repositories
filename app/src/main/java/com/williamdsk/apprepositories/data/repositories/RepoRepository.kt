package com.williamdsk.apprepositories.data.repositories

import com.williamdsk.apprepositories.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {
    suspend fun listRepositories(user: String) : Flow<List<Repo>>
}