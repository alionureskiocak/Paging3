package com.example.paging3.data.mapper

import com.example.paging3.data.dto.UserDto
import com.example.paging3.domain.model.User
import com.example.paging3.domain.model.UserEntity

fun UserDto.toDomain() : User{
    return User(
        id = id,
        username = username,
        avatarUrl = avatar_url
    )
}

fun UserDto.toEntity() : UserEntity{
    return UserEntity(
        id = id,
        username = username,
        avatarUrl = avatar_url
    )
}