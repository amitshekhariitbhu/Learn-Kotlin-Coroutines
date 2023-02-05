package me.amitshekhar.learn.kotlin.coroutines.ui.errorhandling.trycatch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.amitshekhar.learn.kotlin.coroutines.data.api.ApiHelper
import me.amitshekhar.learn.kotlin.coroutines.data.local.DatabaseHelper
import me.amitshekhar.learn.kotlin.coroutines.data.model.ApiUser
import me.amitshekhar.learn.kotlin.coroutines.ui.base.UiState

class TryCatchViewModel(
    private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<UiState<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getUsersWithError()
                users.postValue(UiState.Success(usersFromApi))
            } catch (e: Exception) {
                users.postValue(UiState.Error("Something Went Wrong"))
            }
        }
    }

    fun getUsers(): LiveData<UiState<List<ApiUser>>> {
        return users
    }

}