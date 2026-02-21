package com.example.paging3.data.remote

import android.service.autofill.UserData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paging3.data.local.UserDatabase
import com.example.paging3.data.mapper.toEntity
import com.example.paging3.domain.model.UserEntity
import okhttp3.internal.userAgent

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val db : UserDatabase,
    private val api: UserApi
) : RemoteMediator<Int, UserEntity>(){

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {

        return try {

            val since = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> state.lastItemOrNull()?.id ?: return MediatorResult.Success(endOfPaginationReached = true)
            }

            val users = api.getUsers(since)
            val entities = users.map { it.toEntity() }

            db.withTransaction {
                if (loadType == LoadType.REFRESH){
                    db.userDao().clearAll()
                }
                db.userDao().insertUsers(entities)
            }

            MediatorResult.Success(endOfPaginationReached = users.isEmpty())

        }catch (e: Exception){
            MediatorResult.Error(e)
        }
    }
}