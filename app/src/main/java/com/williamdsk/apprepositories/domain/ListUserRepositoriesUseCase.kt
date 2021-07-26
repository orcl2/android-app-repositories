package com.williamdsk.apprepositories.domain

import com.williamdsk.apprepositories.core.UseCase
import com.williamdsk.apprepositories.data.model.Repo
import com.williamdsk.apprepositories.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(
    private val repository: RepoRepository
) : UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }

}