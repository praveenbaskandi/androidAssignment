package com.praveen.myapplication.networkUtil


object APIRepository {
    private var mApiInterface: APIInterface? = null

    init {
        mApiInterface =
            RetrofitClientInstance.apiService
    }

    suspend fun getDataList(
        page: String?,
    ) = mApiInterface?.getDataList(page)
}