package com.example.paging3.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val avatarUrl: String,
    val username: String,
)