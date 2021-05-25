package com.praveen.myapplication.appUI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.praveen.myapplication.R
import com.squareup.picasso.Picasso

class DataVerticalAdapter(private val dataList: List<DataModelClass.Data>, val mContext: Context?) :
    RecyclerView.Adapter<DataVerticalAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_verti_profile, parent, false)
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
            val ivProfile = itemView.findViewById(R.id.ivProfile) as ImageView
            val ivContent = itemView.findViewById(R.id.ivContent) as ImageView
            val tvFirstName = itemView.findViewById(R.id.tvFirstName) as TextView
            val tvLastName = itemView.findViewById(R.id.tvLastName) as TextView

            tvFirstName.text = dataList.first_name
            tvLastName.text = dataList.last_name

            Picasso.get()
                .load(dataList.avatar)
                .into(ivProfile)

            Picasso.get()
                .load(dataList.avatar)
                .into(ivContent)
        }
    }
}