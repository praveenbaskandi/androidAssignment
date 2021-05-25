package com.praveen.myapplication.appUI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.praveen.myapplication.R
import com.squareup.picasso.Picasso

class DataHorizontalAdapter(
    private val dataList: List<DataModelClass.Data>,
    ) : RecyclerView.Adapter<DataHorizontalAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hori_profile, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(dataList: DataModelClass.Data) {
            val ivImageProfile = itemView.findViewById(R.id.ivProfile) as ImageView

            Picasso.get()
                .load(dataList.avatar)
                .into(ivImageProfile)
        }
    }
}