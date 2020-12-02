package com.shazdroid.shaz.viewmodels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.shazdroid.shaz.model.ResultData
import com.shazdroid.shaz.model.UserDetailResponse
import com.shazdroid.shaz.model.UserListResponse
import com.shazdroid.shaz.repository.Repository

class UserViewModel @ViewModelInject constructor(private val repository: Repository): ViewModel() {

    val currentVisiblePage = MutableLiveData<Int>()
    val totalPages = MutableLiveData<Int>()

    init {
        currentVisiblePage.postValue(1)
        totalPages.postValue(0)
    }

    fun getUserList(page: Int) : LiveData<ResultData<UserListResponse>>{
        return liveData {
            emit(ResultData.Loading())
            try {
                emit(repository.getUserList(page))
            }catch (e:Exception){
                emit(ResultData.Exception("Exception: ${e.localizedMessage}"))
            }
        }
    }

    fun setCurrentVisiblePage(page: Int){
        currentVisiblePage.postValue(page)
    }

    fun getUserDetail(userId: Int) : LiveData<ResultData<UserDetailResponse>>{
        return liveData {
            emit(ResultData.Loading())
            try {
                emit(repository.getUserData(userId.toString()))
            }catch (e:Exception){
                emit(ResultData.Exception("Exception: ${e.localizedMessage}"))
            }
        }

    }
}