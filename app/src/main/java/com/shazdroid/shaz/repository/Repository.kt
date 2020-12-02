package com.shazdroid.shaz.repository


import com.shazdroid.shaz.api.ApiInterface
import com.shazdroid.shaz.model.ResultData
import com.shazdroid.shaz.model.UserDetailResponse
import com.shazdroid.shaz.model.UserListResponse
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface){

    suspend fun getUserList(page:Int) : ResultData<UserListResponse> {
        val result = apiInterface.getUserList(page)
        return if (result != null){
            ResultData.Success(result)
        }else{
            ResultData.Failed("Something went wrong")
        }
    }

    suspend fun getUserData(userId: String) : ResultData<UserDetailResponse>{
        val result = apiInterface.getUserPostById(userId)
        return if (result != null){
            ResultData.Success(result)
        }else{
            ResultData.Failed("Oops failed")
        }
    }
}