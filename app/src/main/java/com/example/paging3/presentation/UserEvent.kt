package com.example.paging3.presentation

sealed interface UserEvent {

    data object FetchUsers : UserEvent
    data class ClickUserDetail(val id : Int) : UserEvent
}