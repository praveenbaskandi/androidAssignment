package com.praveen.myapplication.appUI

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.praveen.myapplication.R
import com.praveen.myapplication.appUtil.NetworkUtils
import com.praveen.myapplication.applicationUtil.BaseActivity
import com.praveen.myapplication.databinding.ActivityMainBinding
import com.praveen.myapplication.dbUtil.DatabaseClass

class MainActivity : BaseActivity() {
    private var mActivityMainBinding: ActivityMainBinding? = null
    private var mMainViewModel: MainViewModel? = null
    private var mDataList: MutableLiveData<List<DataModelClass.Data>>? = null
    private val mContext: Context = this@MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mMainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java
        )
        mActivityMainBinding?.lifecycleOwner = this
        mActivityMainBinding?.viewModel = mMainViewModel
        mDataList = mMainViewModel?.getDataListData()
        mActivityMainBinding!!.rvListVertical.layoutManager = LinearLayoutManager(mContext)
        mActivityMainBinding!!.rvListHorizontal.layoutManager = LinearLayoutManager(
            mContext,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        settingObserver()

        if (NetworkUtils.isConnected(mContext)) {
            mMainViewModel?.errorShow?.value = false
            callSampleApi()
        } else {
            if (DatabaseClass.getLocalList(mContext).isNotEmpty()) {
                mMainViewModel!!.setDataListData(DatabaseClass.getLocalList(mContext))
                mMainViewModel?.errorShow?.value = false
            } else {
                mMainViewModel?.errorShow?.value = true
            }
        }
    }

    private fun settingObserver() {
        mMainViewModel!!.dataListSuccess.observe(this) { apiObserverDataModel ->
            hideProgressDialog()
            val listData: DataModelClass =
                apiObserverDataModel.responseDataModel as DataModelClass
            mMainViewModel!!.setDataListData(listData.data)
            DatabaseClass.insertLocalData(listData.data, mContext)
        }
        mMainViewModel!!.getResponseError().observe(this) {
            hideProgressDialog()
            mMainViewModel?.errorShow?.value = true
            Toast.makeText(mContext, "Error", Toast.LENGTH_LONG).show()
        }

        mDataList!!.observe(this) { dataListModel ->
            val mDataAdapter = DataVerticalAdapter(dataListModel, mContext)
            val mHorizontalAdapter = DataHorizontalAdapter(dataListModel)
            mActivityMainBinding!!.rvListHorizontal.adapter = mHorizontalAdapter
            mActivityMainBinding!!.rvListVertical.adapter = mDataAdapter
        }
    }


    private fun callSampleApi() {
        showProgressDialog("")
        mMainViewModel?.getDataListApi()
    }
}