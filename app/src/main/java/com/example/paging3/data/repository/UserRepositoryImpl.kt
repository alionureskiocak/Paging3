package com.example.paging3.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.paging3.data.local.UserDao
import com.example.paging3.data.local.UserDatabase
import com.example.paging3.data.mapper.toDomain
import com.example.paging3.data.remote.UserApi
import com.example.paging3.data.remote.UserRemoteMediator
import com.example.paging3.domain.model.User
import com.example.paging3.domain.model.UserEntity
import com.example.paging3.domain.repository.UserRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class UserRepositoryImpl @Inject constructor(
    private val api : UserApi,
    private val db: UserDatabase
) : UserRepository{

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = UserRemoteMediator(db,api),
            pagingSourceFactory = { db.userDao().pagingSource() }
        ).flow
            .map { pagingData ->
                pagingData.map {
                    it.toDomain()
                }
            }
    }
}