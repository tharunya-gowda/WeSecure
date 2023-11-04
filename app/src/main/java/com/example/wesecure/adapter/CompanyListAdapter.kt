package com.example.wesecure.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.model.CompanyItem

class CompanyListAdapter(private val cList: ArrayList<CompanyItem>) :
    RecyclerView.Adapter<CompanyListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.comp_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val compItem = cList[position]
        holder.compName.text = compItem.comp_name
        holder.compPhone.text = compItem.comp_phone
        holder.compAddress.text = compItem.comp_address

    }

    override fun getItemCount(): Int {
        return cList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val compName: TextView = itemView.findViewById(R.id.compname)
        val compPhone: TextView = itemView.findViewById(R.id.compphone)
        val compAddress: TextView = itemView.findViewById(R.id.compaddress)
    }
}
