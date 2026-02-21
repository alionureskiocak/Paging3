package com.example.paging3.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.domain.model.User
import com.example.paging3.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UserState>(UserState())
    val state = _state.asStateFlow()

    fun onEvent(event : UserEvent){
        when(event){
            is UserEvent.ClickUserDetail -> {}
            UserEvent.FetchUsers -> getUsers()
        }
    }

    init {
        onEvent(UserEvent.FetchUsers)
    }

    private fun getUsers(){
        val users = repository.getUsers()
            .cachedIn(viewModelScope)
        _state.value = _state.value.copy(
            users = users
        )
    }


}

data class UserState(
    val users : Flow<PagingData<User>> = emptyFlow()
)