package com.praveen.myapplication.networkUtil

import com.praveen.myapplication.appUI.DataModelClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("users")
    suspend fun getDataList(@Query("page") page: String?): Response<DataModelClass>

}