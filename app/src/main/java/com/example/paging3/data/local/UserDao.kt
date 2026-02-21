package com.example.paging3.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.paging3.domain.model.User
import com.example.paging3.domain.model.UserEntity

@Dao
interface UserDao {

    @Upsert
    suspend fun insertUsers(users : List<User>)

    @Query("SELECT * FROM userentity")
    fun pagingSource() : PagingSource<Int, UserEntity>

    @Query("DELETE FROM userentity")
    suspend fun clearAll()
}