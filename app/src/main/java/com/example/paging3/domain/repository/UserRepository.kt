package com.example.paging3.domain.repository

import androidx.paging.PagingData
import com.example.paging3.domain.model.User
import com.example.paging3.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers() : Flow<PagingData<User>>
}