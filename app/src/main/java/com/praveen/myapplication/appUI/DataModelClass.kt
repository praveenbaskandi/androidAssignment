package com.praveen.myapplication.appUI

import androidx.annotation.Keep
import com.praveen.myapplication.networkUtil.BaseApiResponseDataModel

@Keep
class DataModelClass : BaseApiResponseDataModel() {
    val data: List<Data> = listOf()
    val page: Int = 0
    val per_page: Int = 0
    val support: Support = Support()
    val total: Int = 0
    val total_pages: Int = 0

    @Keep
    data class Data(
        var avatar: String = "",
        var email: String = "",
        var first_name: String = "",
        var id: Int = 0,
        var last_name: String = ""
    )

    @Keep
    data class Support(
        val text: String = "",
        val url: String = ""
    )
}