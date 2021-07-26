package com.williamdsk.apprepositories.data.repositories

import com.williamdsk.apprepositories.core.RemoteException
import com.williamdsk.apprepositories.data.services.GitHubService
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RepoRepositoryImpl(private val service: GitHubService) : RepoRepository{

    override suspend fun listRepositories(user: String) = flow {
        try {
            val listRepositories = service.listRepos(user)
            emit(listRepositories)
        }catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possível fazer a busca no momento!")
        }
    }
}