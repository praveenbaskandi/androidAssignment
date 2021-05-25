package com.praveen.myapplication.appUI

import androidx.lifecycle.MutableLiveData
import com.praveen.myapplication.applicationUtil.BaseViewModel
import com.praveen.myapplication.networkUtil.APIConstants
import com.praveen.myapplication.networkUtil.APIRepository
import com.praveen.myapplication.networkUtil.APIUtil
import com.praveen.myapplication.networkUtil.ApiObserverDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : BaseViewModel() {
    val errorShow = MutableLiveData<Boolean>()
    private var dataList: MutableLiveData<List<DataModelClass.Data>>? = null
    val dataListSuccess = MutableLiveData<ApiObserverDataModel<*>>()

    init {
        errorShow.value = false
    }

    fun getDataListData(): MutableLiveData<List<DataModelClass.Data>> {
        if (dataList == null) {
            dataList = MutableLiveData<List<DataModelClass.Data>>()
        }
        return dataList as MutableLiveData<List<DataModelClass.Data>>
    }

    fun setDataListData(listData: List<DataModelClass.Data>) {
        dataList?.postValue(listData)
    }


    /**
     * calling data api to get the list
     * */
    fun getDataListApi() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = APIRepository.getDataList("1")
            withContext(Dispatchers.Default) {
                try {
                    if (response!!.isSuccessful) {
                        val dataItems: DataModelClass = response.body() as DataModelClass
                        dataListSuccess.postValue(
                            ApiObserverDataModel<DataModelClass>(
                                dataItems,
                                ""
                            )
                        )
                    } else {
                        val errorMessage = APIUtil.parseError(response)
                        if (errorMessage != null) {
                            setResponseError(errorMessage)
                        }
                    }
                } catch (e: Throwable) {
                    setResponseError(APIConstants.SERVER_ERROR)
                }
            }
        }
    }
}

