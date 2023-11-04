package com.example.wesecure.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wesecure.R
import com.example.wesecure.model.VisitorItem

class VisitorAdapter(private val vList:ArrayList<VisitorItem>): RecyclerView.Adapter<VisitorAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.vis_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem=vList[position]
        holder.vis_number.text=currentitem.vis_number
        holder.vis_name.text=currentitem.vis_name
        holder.vis_phone.text=currentitem.vis_phone
        holder.vis_purpose.text=currentitem.vis_purpose

    }

    override fun getItemCount(): Int {
        return vList.size
    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val vis_number=itemView.findViewById<TextView>(R.id.visnum)
        val vis_name=itemView.findViewById<TextView>(R.id.vis_name)
        val vis_phone=itemView.findViewById<TextView>(R.id.v_phone)
        val vis_purpose=itemView.findViewById<TextView>(R.id.v_purpose)
    }
}
